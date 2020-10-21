package test.java.Junit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import main.java.Package.ConnectionUtil;
import main.java.Package.SQL;
import main.java.model.Stock;

public class SQLTest {

	@Test
	public void getStockTest() {
		SQL sql = new SQL();
		List<Stock> stocks1 = new ArrayList<Stock>();
		List<Stock> stocks2 = new ArrayList<Stock>();
		
		//Control case
		Stock s1 = new Stock("TST", 101.01, 3);
		Stock s2 = new Stock("TSTT", 102.02, 5);
		stocks1.add(s1);
		stocks1.add(s2);
		
		Stock s3 = new Stock("TST");
		Stock s4 = new Stock("TSTT");
		stocks2.add(s3);
		stocks2.add(s4);
		sql.getStock(stocks2);
		
		
		assertEquals(stocks1.get(0).getTicker(), stocks2.get(0).getTicker());
		assertEquals(stocks1.get(0).getPrice(), stocks2.get(0).getPrice(), 0.00001);
		assertEquals(stocks1.get(0).getShares(), stocks2.get(0).getShares());
		
		assertEquals(stocks1.get(1).getTicker(), stocks2.get(1).getTicker());
		assertEquals(stocks1.get(1).getPrice(), stocks2.get(1).getPrice(), 0.00001);
		assertEquals(stocks1.get(1).getShares(), stocks2.get(1).getShares());
	}

	@Test
	public void enterStockTest() {
		SQL dao = new SQL();
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		Stock stock1 = new Stock("TESTER", 101.01, 50);
		//Stock stock2 = new Stock("TEST", 102.01, 30);
		stocks.add(stock1);
		//stocks.add(stock2);
		String ticker = "TESTER";
		double price = 101.01;
		int shares = 50;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM portfolio WHERE ticker = ? AND share_price = ? AND shares = ?;")){
				connection.setAutoCommit(false);
				
				dao.enterStock(stocks);
				// Check the Portfolio table contains one row with the expected values:

                try {              
                	stmt.setString(1, stocks.get(0).getTicker());
                	stmt.setDouble(2, stocks.get(0).getPrice());
                	stmt.setInt(3, stocks.get(0).getShares());

    				ResultSet rs = stmt.executeQuery();

                			
                			
 
                    assertTrue(rs.next());
                    assertTrue(ticker.contentEquals(rs.getString("ticker")));
                    assertEquals(price, rs.getDouble("share_price"), 0.00001);
                    assertEquals(shares, rs.getInt("shares"));                   
                    assertFalse(rs.next());
                } catch (SQLException e) {
                	fail(e.toString());
                } finally {
                	stmt.close();
                }
				
			} finally {
    			connection.rollback();
    			connection.close();
    		}

		} catch (SQLException e)
	        {
	            fail(e.toString());
	        }
		} 
}	