package test.java.Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.Package.ConnectionUtil;
import main.java.Package.SQL;
import main.java.model.Stock;

class SQLTest {

	@Test
	void getStockTest() {
		SQL dao = new SQL();
		Stock stock1 = new Stock("TST");
		stock1.setPrice(101.01);
		stock1.setShares(3);
		Stock stock2 = dao.getStock("TST");
		System.out.println(stock1.getPrice());
		System.out.println(stock2.getPrice());
		assertEquals(stock1.getPrice(), stock2.getPrice());
		assertEquals(stock1.getShares(), stock2.getShares());
	}

	@Test
	void enterStockTest() {
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
			try (Statement stmt = connection.createStatement()){
				connection.setAutoCommit(false);
				
				dao.enterStock(stocks);
				// Check the Portfolio table contains one row with the expected values:
				//FAILING ON LINE 52
                try(ResultSet rs=stmt.executeQuery("SELECT * FROM portfolio WHERE ticker = " + stocks.get(0).getTicker() + " AND share_price = " + stocks.get(0).getPrice() + " AND shares = " + stocks.get(0).getShares() + ";"))
                {
                    assertTrue(rs.next());
                    assertTrue(ticker.contentEquals(rs.getString("ticker")));
                    assertEquals(price, rs.getDouble("share_price"));
                    assertEquals(shares, rs.getInt("shares"));                   
                    assertFalse(rs.next());
                } 
				
			} finally {
    			connection.rollback();
    		}

		} catch (SQLException e)
	        {
	            fail(e.toString());
	        }
		} 
		
	
	
	/*
	 * @Test void deleteStockTest() { //This raises a lot of questions. How would I
	 * distinguish which set to delete? }
	 * 
	 * @Test void modifyStockTest() { //^^ that leads me to think I need the ability
	 * to adjust the data, like if I sold off some shares. Needing a reference ID. }
	 */}