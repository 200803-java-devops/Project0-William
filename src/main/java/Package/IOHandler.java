package Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//It is this class' job to read and write to external files as well as handle console input/output


public class IOHandler {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	public FileWriter writer;
	BufferedReader br;
	

	
	public void writeOutputConsole(List<Stock> stocks) {
		for (int i = 0; i < stocks.size(); i++) {
			System.out.println("Row " + i + ". " + "ID #: " + stocks.get(i).getId() + " Ticker: " + stocks.get(i).getTicker() + " Share price: " + stocks.get(i).getPrice() + " # of shares: " + stocks.get(i).getShares() + " purchase date: " + stocks.get(i).getDate());
		}		
	}
	
	public List<Stock> getStockConsole() {
		log.debug("console input was run");
		List<Stock> stock = new ArrayList<Stock>();
		Stock s = new Stock();
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter the stock ticker:");
			s.setTicker(sc.nextLine());
			System.out.println("Enter the stock share price:");
			s.setPrice(sc.nextDouble());
			System.out.println("Enter number of shares:");
			s.setShares(sc.nextInt());
			System.out.println("Inserting data into portfolio...");
			stock.add(s);
			return stock;
		} catch (InputMismatchException e) {
			e.printStackTrace();
			log.error("InputMismatchException occurred!");
			return stock;
		}
	}
	
	public List<Stock> getTickerConsole() {
		Scanner sc = new Scanner(System.in);
		List<Stock> text = new ArrayList<Stock>();
		String ticker = sc.nextLine();
		text.add(new Stock(ticker));
		return text;
		
	}
	
	
	public List<Stock> getTickersFileInput(String file) throws IOException {
		System.out.println("Reading your input...");
		List<Stock> text = new ArrayList<Stock>();
		try {
			File csv = new File(file);
			br = new BufferedReader(new FileReader(csv));
			br.readLine();
			while (br.ready()) {
				text.add(new Stock(br.readLine().split(",")[0])); 
			}
		} catch (FileNotFoundException e) {
			System.err.println("There is no detectable input file in the root of the project!");
			e.printStackTrace();
		} finally {
			br.close();
		}
		
		return text;
		
	}
	
	public List<Stock> getStocksFileInput(String file) throws IOException {
		System.out.println("Reading your input...");
		List<Stock> text = new ArrayList<Stock>();
		try {
			File csv = new File(file);
			br = new BufferedReader(new FileReader(csv));
			br.readLine();
			String[] array;
			while (br.ready()) {
				Stock stock = new Stock();
				array = br.readLine().split(",");
				stock.setTicker(array[0]);
				stock.setPrice(Double.parseDouble(array[1]));
				stock.setShares(Integer.valueOf(array[2]));
				text.add(stock);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("There is no detectable input file in the root of the project!");
			e.printStackTrace();
		} finally {
			br.close();
		}
		
		return text;
		
	}
	
	
	
	public void writeOutputFile(List<Stock> stocks, String file) {
		System.out.println("Writing your output data to external file...");
		try {
			writer = new FileWriter(file, false);
			//writer.write("");
			writer.write("Tickers,");
			writer.write("Share price,");
			writer.write("Shares,");
			writer.write("Date,");
			writer.write("\r\n");
			for (int i = 0; i < stocks.size(); i++) {
				writer.write(stocks.get(i).getTicker() + ",");
				writer.write(Double.toString(stocks.get(i).getPrice()) + ",");
				writer.write(Integer.toString(stocks.get(i).getShares()) + ",");
				writer.write(stocks.get(i).getDate() + ",");
				writer.write("\r\n");
			}
		} catch (IOException e) {
			System.err.println("Problem with writing the output!");
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
		
}
