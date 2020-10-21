package main.java.model;

public class Stock {
	private String ticker;
	private double price;
	private int shares;
	private String date;
	private int id;
	//other variables go here, maybe RSI, moving averages, 52wk h and l
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Stock (String ticker) {
		this.ticker = ticker;
	}
	
	public Stock () {
		this.ticker = null;
		this.price = 0;
		this.shares = 0;
		this.setDate(null);
	}
	
	public Stock (String ticker, double price, int shares, String date) {
		this.ticker = ticker;
		this.price = price;
		this.shares = shares;
		this.setDate(date);
	}

	
	public Stock (String ticker, double price, int shares) {
		this.ticker = ticker;
		this.price = price;
		this.shares = shares;
		
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

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

}
