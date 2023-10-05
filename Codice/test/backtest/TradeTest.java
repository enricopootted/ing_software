package backtest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import backtest.Trade;

public class TradeTest {
	
	 LocalDateTime testInizio;
	 LocalDateTime testFine;
	 double testBuyPrice=10;
	 double testSellPrice=200;
	  Trade tradeTest = new Trade(testInizio,testBuyPrice);
	 
	 
	@Test
	public void testGetProfit() {
		tradeTest.setFine(testFine, testSellPrice);
		assertEquals(tradeTest.getProfit(), 19,0);
	}

	

	@Test
	public void testGetBuyPrice() {
		
		assertEquals(tradeTest.getBuyPrice(),10,0);
	}

	@Test
	public void testGetSellPrice() {
		tradeTest.setFine(testFine, testSellPrice);
		assertEquals(tradeTest.getSellPrice(), 200,0);
	}
}
