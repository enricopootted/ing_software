package user;

import static org.junit.Assert.*;
import java.util.Vector;
import org.junit.Test;

import backtest.Trade;
import user.GestoreFile;

public class GestoreFileTest {
	

	@Test
	public void testCaricaDaFile() {
		String testo="fileTest";
		Vector<Trade>testTrades = GestoreFile.caricaDaFile(testo);
		assertEquals(testTrades.get(3).getBuyPrice(), 3.61,0);
		assertEquals(testTrades.get(42).getBuyPrice(), 33.119999,0);
		assertEquals(testTrades.get(0).getSellPrice(), 5.85,0);
		}
}


