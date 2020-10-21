package Package;

import java.io.IOException;
import java.util.Scanner;
import Package.SQL;
import thread.myThread;
import Package.IOHandler;
import model.Stock;

//It is this class' job to manage the user prompts and appropriately call methods based on them.  It also contains the logic for the UI.

public class Manager {
	private String controller = "yes";
	private String controller2;
	Scanner sc = new Scanner(System.in);
	private String InputFile = "input.csv";
	private String OutputFile = "output.csv";
	
	
	
	
	public void interact() {
		System.out.println("Welcome.  Please follow the instructions to interact with the portfolio database." + "\n" + "You will receive the latest S&P 500 data every 60 seconds and it will also be logged.");
		myThread thread = new myThread();
		thread.start();
		IOHandler io = new IOHandler();
		SQL sql = new SQL();


		while(controller.equals("yes")) {
			System.out.println("Please select an option by typing in the number:" + "\n" + "1. Get stock data from portfolio" + "\n" + "2. Enter new stock data into portfolio" + "\n" + "3. Delete stock data from portfolio" + "\n" + "4. Print the entire portfolio here.");
			switch (controller2 = sc.nextLine()) {
				case "1": //THEY WANT TO RETRIEVE DATA
					System.out.println("Would you like to input the tickers you want from input.csv and output to output.csv?  Or enter a ticker and return the data here?  Select an option by typing in a number:" + "\n" + "1. From input.csv" + "\n" + "2. From the command line");
					switch (controller2 = sc.nextLine()) {
						case "1": //THEY WANT INPUT.CSV AND OUTPUT.CSV
							//sql.enterStock(io.getTickersFileInput(InputFile));
							try {
								io.writeOutputFile(sql.getStock(io.getTickersFileInput(InputFile)), OutputFile);
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "2": //THEY WANT TO ENTER A TICKER FROM THE COMMAND LINE
							System.out.println("Please type a ticker to retrieve the data");
							io.writeOutputConsole(sql.getStock(io.getTickerConsole()));
							break;
							
						default:
							System.out.println("Unknown command.  Try again.");
							break;
					}
				break;
					
				case "2": //THEY WANT TO ENTER DATA TO THE DB
					System.out.println("Would you like to upload the tickers and data from input.csv?  Or enter it here?  Select an option by typing in a number:" + "\n" + "1. From input.csv" + "\n" + "2. From the command line");
					switch (controller2 = sc.nextLine()) {
						case "1": //THEY WANT INPUT.CSV
							
							try {
								sql.enterStock(io.getStocksFileInput(InputFile));
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "2": //THEY WANT TO ENTER DATA FROM THE COMMAND LINE
							sql.enterStock(io.getStockConsole());
							break;							
						default:
							System.out.println("Unknown command.  Try again.");
							break;
					}
					break;
					
				case "3": //THEY WANT TO DELETE DATA
					System.out.println("First, enter the ticker you want to delete from the database:");
					sql.deleteStock(io.getTickerConsole());
					break;
					
				case "4": //THEY WANT TO VIEW EVERYTHING IN CONSOLE
					sql.viewAll();
					break;
				default:
					System.out.println("Unknown command.  Try again.");
					break;
			}
			
			//THIS WILL RUN AFTER THE SWITCH STATEMENTS ARE DONE
			System.out.println("Do you want to perform another task?  Type 'yes' to continue.");
			controller = sc.nextLine().toLowerCase();
						
		}
		System.out.println("shutting down. Goodbye.");
		thread.stop();
		sql.closeConnection();
		sc.close();
		
		 
		

		
	

	}

}
