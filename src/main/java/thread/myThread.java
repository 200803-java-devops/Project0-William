package main.java.thread;

import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

//This thread's job is to return the latest S&P 500 score throughout the application runtime.
//Note this will stop its function after 30 updates and it is printing a dummy number.
//These are both features that should be improved upon.

public class myThread extends Thread {
	java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
	
	public myThread() {
	try {
		FileHandler fileHandler = new FileHandler("status.log");
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
		
	} catch (Exception e) {
		logger.fine("Unable to build logger file");
	}
	}

	
	
	public void run() {
		int i = 0;
		while (i < 30) {
			
		System.out.println("\n" + "S&P 500 currently at 3350.23");
		i++;
		logger.info("S&P500 3350.23 at " + java.time.LocalTime.now());
		
		
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	}
	

}
