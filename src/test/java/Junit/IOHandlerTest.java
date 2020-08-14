package test.java.Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import main.java.Package.IOHandler;

class IOHandlerTest {

	@Test
	void getFileInputTest() {
		IOHandler io1 = new IOHandler();
		IOHandler io2 = new IOHandler();
		io1.text = "MSFT";
		io2.getFileInput("input.txt");
		assertEquals(io1.text, io2.text);
	}
	
	@Test
	void writeOutputTest() throws FileNotFoundException {
	//stock TST with a price of 101.01 is in the DB for testing purposes
	//this test depends on testIO1.txt and testIO2.txt being inside the root folder
	//At present, this also requires the testIO1.txt to already be setup based on the state of this project.  Do this manually.
	//There are all sorts of ways to break this test.  It's not durable.
		IOHandler io = new IOHandler();
		io.text = "TST";
		
		//This bit here just resets testIO2.txt to a clean slate, otherwise it will succeed upon the first test and
		//then from there on out fail until you clean out the text file manually.
		PrintWriter writer = new PrintWriter("testIO2.txt");
		writer.print("");
		writer.close();
		
		Scanner scanner = new Scanner (new File("testIO1.txt"));
		String io1Output = scanner.useDelimiter("\\A").next();
		scanner.close();
		
		io.writeOutput("testIO2.txt");
		Scanner scanner2 = new Scanner (new File("testIO2.txt"));
		String io2Output = scanner2.useDelimiter("\\A").next();
		scanner2.close();
		
		assertEquals(io1Output, io2Output);
	}

}
