package Package;

import java.util.Scanner;
import Package.SQL;

public class App {

	public static void main(String[] args) {
		String controller = "Yes";
		Scanner sc = new Scanner(System.in);
		while(controller.equals("Yes")) {
			System.out.println("Please type a stock ticker symbol and I will return its share price");
			String ticker = sc.nextLine();
			System.out.println(SQL.getStock(ticker).getPrice());
			
			System.out.println("Do you want to search for another ticker?  Type Yes or No");
			//There is some kind of bug here.  THis isn't working properly.^^
			controller = sc.nextLine();	
		}
		sc.close();
		System.out.println("Goodbye");
}
}
