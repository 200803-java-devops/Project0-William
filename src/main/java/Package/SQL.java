package main.java.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.Package.ConnectionUtil;

import main.java.model.Stock;

//It is this class' job to query the database and return data

public class SQL {

	Connection connection = null;
	PreparedStatement stmt = null;
	
	public void viewAll() {
		try {
			connection = ConnectionUtil.getConnection();
			stmt = connection.prepareStatement("SELECT * FROM portfolio ORDER BY ticker;");
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNums = rsmd.getColumnCount();
			while (rs.next()) {
			    for (int i = 1; i <= colNums; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
			    }
			    System.out.println("");
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	
	public boolean enterStock(List<Stock> stocks) {
		try {
			connection = ConnectionUtil.getConnection();
			List<Integer> tester = new ArrayList<Integer>();
			for (int i = 0; i < stocks.size(); i++) {
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
		}
		
	}

		
	public List<Stock> getStock(List<Stock> tickers) {
		List<Stock> stocks1 = new ArrayList<Stock>();
		List<Stock> stocks2 = new ArrayList<Stock>();
		stocks1 = tickers;
		int counter = 0;
		try {
			connection = ConnectionUtil.getConnection();
			for (int i = 0; i < stocks1.size(); i++) { 
				String query = "SELECT * FROM portfolio WHERE Ticker = ?;";
				stmt = connection.prepareStatement(query);
				stmt.setString(1, stocks1.get(i).getTicker());
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					
				stocks2.add(new Stock(stocks1.get(i).getTicker()));
				stocks1.get(i).setPrice(rs.getDouble("share_price"));
				stocks1.get(i).setShares(rs.getInt("shares"));
				stocks1.get(i).setDate(rs.getString("date"));
				stocks1.get(i).setId(rs.getInt("id"));
				
				//have to dump these into a NEW list in case we get several rows for a single ticker
				stocks2.get(counter).setPrice(stocks1.get(i).getPrice());
				stocks2.get(counter).setShares(stocks1.get(i).getShares());
				stocks2.get(counter).setDate(stocks1.get(i).getDate());
				stocks2.get(counter).setId(stocks1.get(i).getId());
				counter++;
			}
		
		
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stocks2;
		
	}
	
	
//have to engineer this so it collects row IDs.  Only way to differentiate.
	public boolean deleteStock(List<Stock> tickers) {
		IOHandler io = new IOHandler();
		List<Stock> stocks = new ArrayList<Stock>();
		stocks = getStock(tickers);
		io.writeOutputConsole(stocks);
		System.out.println("Please enter the ID number for the stocks you want to delete:");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		
		try {
			String query = "DELETE FROM portfolio WHERE \"Id\" = ?;";
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			
			if (stmt.executeUpdate() != 0) {
				System.out.println("Delete successful");
				return true;
			} else {
				System.out.println("Delete unsuccessful");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	

		
		
	protected void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}


	}
	

}
