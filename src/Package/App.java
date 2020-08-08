package Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Package.SQL;

public class App {
//REMEMBER BAREBONES FIRST!  AIM FOR SOME KIND OF TXT FILE INPUT/OUTPUT
	
	public static void main(String[] args) throws IOException {
		String controller = "Yes";
		Scanner sc = new Scanner(System.in);
		
		Scanner scanner = new Scanner( new File("input.txt") );
		String text = scanner.useDelimiter("\\A").next();
		FileWriter writer = new FileWriter("output.txt", true);

		
		try { 
//Adding a switch statement in place of the while could add some functionality.
		while(controller.equals("Yes")) {
			
			 
		//**Code for console ticker input
			  //System.out.println("Please type a stock ticker symbol and I will return its share price");
			  //String ticker = sc.nextLine();
			
		//**Process of confirming in console what ticker was found and also returning share price for confirmation
			  System.out.println(text);
			  System.out.println(SQL.getStock(text).getPrice());
			  
		//**Writing to output.txt	 
			  writer.write("Ticker: ");
			  writer.write(text);
			  writer.write("\r\n");
			  writer.write("Share price: ");
			  writer.write(Double.toString(SQL.getStock(text).getPrice()));
		
		
		//**Confirmation of exit
			System.out.println("Do you want to search for another ticker?  Type Yes or No");
			controller = sc.nextLine();	
		}
		}
		finally { 
			sc.close();
			writer.close();
			scanner.close();
			//scanner.close(); // Put this call in a finally block
		}
		System.out.println("Goodbye");
}
}
