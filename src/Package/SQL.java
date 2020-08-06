package Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Package.DBconnect;

import model.Stock;

public class SQL {

	static Connection connection = null;
	static PreparedStatement stmt = null;
	

	public static Stock getStock(String ticker) {
		Stock stock = new Stock(ticker);
		try {
		connection = DBconnect.getConnection();

		String query = "SELECT * FROM TestTable WHERE Ticker = 'MSFT';";
		stmt = connection.prepareStatement(query);
		//stmt.setString(1, ticker);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
		stock.setPrice(rs.getDouble("Share_Price"));
		
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stock;
	}
}
