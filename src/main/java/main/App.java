package main.java.main;

import java.io.IOException;

import main.java.Package.IOHandler;
import main.java.Package.Manager;

public class App {

	public static void main(String[] args) {
		
		/*
		 * Manager manager = new Manager(); manager.interact();
		 */		 
		
		
		  IOHandler io = new IOHandler(); 
		  try {
			io.getStocksFileInput("input.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		
		
}
}
