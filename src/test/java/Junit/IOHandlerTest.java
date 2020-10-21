package test.java.Junit;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


import main.java.Package.IOHandler;
import main.java.model.Stock;

public class IOHandlerTest {

	@Test
	public void getTickersFileInputTest() {
		//You must set up the testIO2.csv file manually to ensure this works
		IOHandler io = new IOHandler();
		List<Stock> stocks1 = new ArrayList<Stock>();
		List<Stock> stocks2 = new ArrayList<Stock>();
		Stock s1 = new Stock("TST");
		Stock s2 = new Stock("MSFT");
		stocks1.add(s1);
		stocks1.add(s2);
		
		try {
			stocks2 = io.getTickersFileInput("testIO2.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(stocks1.get(0).getTicker(), stocks2.get(0).getTicker());
		assertEquals(stocks1.get(1).getTicker(), stocks2.get(1).getTicker());
	}
	
	@Test
	public void writeOutputFileTest() throws FileNotFoundException {
	//Requires manual setup of testIO1.csv with the parameters set for the stocks in the code below.
		IOHandler io = new IOHandler();
		List<Stock> stocks = new ArrayList<Stock>();
		Stock s1 = new Stock("TST", 101.01, 3);
		Stock s2 = new Stock("TSTT", 102.02, 5);
		s1.setDate("8/16/2020 21:21");
		s2.setDate("8/16/2020 21:21");
		stocks.add(s1);
		stocks.add(s2);
		
		
		//Control case, this is the one we set up manually and EXPECT the other one to come back as
		Scanner scanner = new Scanner (new File("testIO1.csv"));
		String io1Output = scanner.useDelimiter("\\A").next();
		scanner.close();
		
		io.writeOutputFile(stocks, "testIO3.csv");
		Scanner scanner2 = new Scanner (new File("testIO3.csv"));
		String io2Output = scanner2.useDelimiter("\\A").next();
		scanner2.close();
		
		assertEquals(io1Output, io2Output);
	}

}
