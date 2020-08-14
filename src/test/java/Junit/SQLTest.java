package test.java.Junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.Package.SQL;
import main.java.model.Stock;

class SQLTest {

	@Test
	void getStockTest() {
		//String testticker = "MSFT"
		Stock stock1 = new Stock("MSFT");
		Stock stock2 = SQL.getStock("MSFT");
		stock1.setPrice(230);
		System.out.println(stock1.getPrice());
		System.out.println(stock2.getPrice());
		assertEquals(stock1.getPrice(), stock2.getPrice());
		
		
	}

}