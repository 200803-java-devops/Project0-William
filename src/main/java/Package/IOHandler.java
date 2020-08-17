package main.java.Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.java.Package.SQL;
import main.java.model.Stock;

//Try to make this class handle all the file reading and writing...Not sure if it needs a while loop.
//It is this class' job to read and write to external files

//NOTE SOME LINES ARE COMMENTED OUT FOR NOW

public class IOHandler {

	public FileWriter writer;
	BufferedReader br;
	

	
	
	public List<String> getTickersFileInput(String file) throws IOException {
		System.out.println("Reading your input...");
		ArrayList<String> text = new ArrayList<String>();
		try {
			File csv = new File(file);
			br = new BufferedReader(new FileReader(csv));
			br.readLine();
			while (br.ready()) {
				text.add(br.readLine().split(",")[0]); 
			}
		System.out.println("Input found: " + text);
			
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
	
	
	
	//experiment with the commas before going to a new line.  not sure how that works.
	//ADJUST SO IT TAKES IN AN ARRAY
	public void writeOutput(List<Stock> stocks, String file) {
		System.out.println("Writing your output data to external file...");
		try {
			writer = new FileWriter(file, false);
			writer.write("");
			writer.write("Tickers,");
			writer.write("Share price,");
			writer.write("\r\n");
			for (int i = 0; i < text.size(); i++) {
				writer.write(text.get(i) + ",");
				writer.write(Double.toString(SQL.getStock(text.get(i)).getPrice()) + ",");
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
	
	
	
	/*  old code here before I made too many changes
	 * public void writeOutput(String file) {
	 * System.out.println("Writing your output data to external file..."); try {
	 * //Insert a loop here once support for multiple IO is added writer = new
	 * FileWriter(file, true); writer.write("Tickers,");
	 * writer.write("Share price,"); for (int i = 0; i < text.size(); i++) {
	 * writer.write(text.get(i) + ","); writer.write("\r\n");
	 * writer.write(Double.toString(SQL.getStock(text.get(i)).getPrice()));
	 * writer.write("\r\n"); writer.write("\r\n"); } } catch (IOException e) {
	 * System.err.println("Problem with writing the output!"); e.printStackTrace();
	 * } finally { try { writer.flush(); writer.close(); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * }
	 */
	
}
