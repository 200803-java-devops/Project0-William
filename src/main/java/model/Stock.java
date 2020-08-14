package main.java.model;

public class Stock {
	private String ticker;
	private double price;
	//other variables go here, maybe RSI, moving averages, 52wk h and l
	
	public Stock (String ticker) {
		this.ticker = ticker;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

}
