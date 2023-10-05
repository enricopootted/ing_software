
package datafeed;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import datafeed.Dato;

public class DatoTest {
	
		Dato test = new Dato(10, 15, 25, 5, null);
	
	

	@Test
	public void testGetClose() {
		double x = test.getClose();
		assertEquals(x,10,0);
	}

	@Test
	public void testGetOpen() {
		double x =test.getOpen();
		assertEquals(x,15,0);
	}

	@Test
	public void testGetMax() {
		double x = test.getMax();
		assertEquals(x, 25, 0 );
	}

	@Test
	public void testGetMin() {
		double x = test.getMin();
		assertEquals(x, 5, 0 );	}

	@Test
	public void testGetTime() {
		LocalDateTime x = test.getTime();
		assertEquals(x, null);
	}
	

}
