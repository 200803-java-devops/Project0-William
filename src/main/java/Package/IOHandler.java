package main.java.Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import main.java.Package.SQL;

//Try to make this class handle all the file reading and writing...Not sure if it needs a while loop.
//It is this class' job to read and write to external files

public class IOHandler {
	public String text;
	Scanner scanner;
	public FileWriter writer;

	
	
	public void getFileInput(String file) {
		System.out.println("Reading your input...");
		try {
			scanner = new Scanner( new File(file) );
			text = scanner.useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			System.err.println("There is no input.txt in the root of the project!");
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
		
	}
	
	
	
	public void writeOutput(String file) {
		System.out.println("Writing your output data to external file...");
		try {
			//Insert a loop here once support for multiple IO is added
			writer = new FileWriter(file, true);
			writer.write("Ticker: ");
			writer.write(text);
			writer.write("\r\n");
			writer.write("Share price: ");
			writer.write(Double.toString(SQL.getStock(text).getPrice()));
		} catch (IOException e) {
			System.err.println("Problem with writing the output!");
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// WHY DO I HAVE TO WRAP THIS IN A TRY CATCH?  I DON'T UNDERSTAND
				e.printStackTrace();
			}
		}

	}
	
	
	
//CURRENTLY NOT USED VVV
	public void closeResources() {
		scanner.close();
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
