package main.java.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.Package.ConnectionUtil;

import main.java.model.Stock;

//It is this class' job to query the database and return data
//THIS SHOULD PROBABLY BE MADE NON-STATIC

public class SQL {

	Connection connection = null;
	PreparedStatement stmt = null;
	

	public boolean enterStock(List<Stock> stocks) {
		//take input from either console or file...Will have to modify some things for that.
		try {
			List<Integer> tester = new ArrayList<Integer>();
			for (int i = 0; i < stocks.size(); i++) {
				connection = ConnectionUtil.getConnection();
				String query = "INSERT INTO portfolio (ticker, share_price, shares) VALUES (?, ?, ?);";
				stmt = connection.prepareStatement(query);
				stmt.setString(1, stocks.get(i).getTicker());
				stmt.setDouble(2, stocks.get(i).getPrice());
				stmt.setInt(3, stocks.get(i).getShares());
				tester.add(stmt.executeUpdate());
			}
			if (tester.contains(0)) {
				System.out.println("There was an issue with the query.  Check your input again or check the code.");
				return false;
				//ROLLBACK?
			} else {
				System.out.println("Query successful");
				return true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
		
	}

		
	public List<Stock> getStock(List<Stock> tickers) {
		List<Stock> stocks1 = new ArrayList<Stock>();
		List<Stock> stocks2 = new ArrayList<Stock>();
		stocks1 = tickers;
		int counter = 0;
		try {
			connection = ConnectionUtil.getConnection();
			String query = "SELECT * FROM portfolio WHERE Ticker = ?;";
			for (int i = 0; i < stocks1.size(); i++) { 
				stmt = connection.prepareStatement(query);
				stmt.setString(1, stocks1.get(i).getTicker());
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
				
		//Think through this.  is it going to work properly?  Not sure if the index numbers are always going to work
					
				stocks2.add(new Stock(stocks1.get(i).getTicker()));
				stocks1.get(i).setPrice(rs.getDouble("share_price"));
				stocks1.get(i).setShares(rs.getInt("shares"));
				stocks1.get(i).setDate(rs.getString("date"));
				
				//have to dump these into a NEW list in case we get several rows for a single ticker
				stocks2.get(counter).setPrice(stocks1.get(i).getPrice());
				stocks2.get(counter).setShares(stocks1.get(i).getShares());
				stocks2.get(counter).setDate(stocks1.get(i).getDate());
				counter++;
			}
		
		
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		return stocks2;
		
	}
	
	
	
	public boolean deleteStock(List<Stock> stocks) {

	
	
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
