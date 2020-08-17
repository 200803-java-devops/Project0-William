package main.java.Package;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import main.java.Package.SQL;
import main.java.Package.IOHandler;

//It is this class' job to manage the user prompts and appropriately call methods based on them

public class Manager {
	private String controller = "Yes";
	private String controller2;
	Scanner sc;
	private String ticker; 
	private String InputFile = "input.csv";
	private String OutputFile = "output.csv";
	
	//Change from John: new constructor for Manager class to take input stream as argument
	public Manager(InputStream input){
		sc = new Scanner(input);
	}
	
	
	
	public void interact() {
		System.out.println("Welcome.  Give me a stock ticker and I will return its share price and write it to output.txt");

//Adding a switch statement in place of the while could add some functionality.
		while(controller.equals("Yes")) {
			//Another while loop?  A method?
			System.out.println("Would you like to type a ticker or collect it from input.txt?  Type 'console' to enter one yourself and 'file' to collect one from input.txt");
			switch (controller2 = sc.nextLine()) {
				case "console":
				//**Code for console ticker input...who do I hand this off to?
					System.out.println("Please type a stock ticker symbol and I will return its share price from the SQL database");
					ticker = sc.nextLine();
					System.out.println(SQL.getStock(ticker).getPrice());
					//Should I be breaking this ^^^ up more?
					controller = "No";
					break;
				case "file":
				//**create new IOhandler which will get input and pass onto SQL class for query and then do output
					IOHandler io = new IOHandler();
					try {
						io.getFileInput(InputFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
						io.writeOutput(OutputFile);
					//Notice my difference here in how I handle this case vs the other case.  Which one is better?
					controller = "No";
					break;
				default:
					System.out.println("That is an unsupported keyword.  Try again.");
					break;
			}
					
			System.out.println("Tasks are finished.  Do you want to search for another ticker?  Type Yes or No");
			controller = sc.nextLine();
						
		}
		System.out.println("shutting down. Goodbye.");
		sc.close();
		
		 
		
	

	}

}
