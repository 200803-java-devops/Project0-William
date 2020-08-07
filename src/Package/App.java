package Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Package.SQL;

public class App {
//REMEMBER BAREBONES FIRST!  AIM FOR SOME KIND OF TXT FILE INPUT/OUTPUT
	
	public static void main(String[] args) throws IOException {
		String controller = "Yes";
		Scanner sc = new Scanner(System.in);
		
		try { 
			Scanner scanner = new Scanner( new File("input.txt") );
			String text = scanner.useDelimiter("\\A").next();
//Adding a switch statement in place of the while could add some functionality.
		while(controller.equals("Yes")) {
			
			 
			 
			  //System.out.println("Please type a stock ticker symbol and I will return its share price");
			  //String ticker = sc.nextLine();
			  System.out.println(text);
			  System.out.println(SQL.getStock(text).getPrice());
			  
			 			
			System.out.println("Do you want to search for another ticker?  Type Yes or No");
			//Commenting out in order to test File input
			controller = sc.nextLine();	
		}
		}
		finally { 
			sc.close();
			//scanner.close(); // Put this call in a finally block
		}
		System.out.println("Goodbye");
}
}
