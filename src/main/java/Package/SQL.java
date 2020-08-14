package main.java.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.Package.ConnectionUtil;

import main.java.model.Stock;

//It is this class' job to query the database and return data
//THIS SHOULD PROBABLY BE MADE NON-STATIC

public class SQL {

	static Connection connection = null;
	static PreparedStatement stmt = null;
	

	public static Stock getStock(String ticker) {
		Stock stock = new Stock(ticker);
		try {
			connection = ConnectionUtil.getConnection();
			String query = "SELECT * FROM TestTable WHERE Ticker = ?;";
			stmt = connection.prepareStatement(query);
			stmt.setString(1, ticker);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
			stock.setPrice(rs.getDouble("share_price"));
		
		//Stock has attributes price and ticker
		
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
		
	}
}
