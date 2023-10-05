package strategie;

import static org.junit.Assert.*;
import java.util.Vector;

import org.junit.Test;

import datafeed.Dato;
import strategie.MediaMobile;
import strategie.StrategiaException;

public class MediaMobileTest {
	Dato test1 = new Dato(150, 145, 150, 100, null);
	Dato test2 = new Dato(221, 250, 255, 200, null);
	Dato test3 = new Dato(1350, 1240, 1350, 1200, null);
	Dato test4 = new Dato(1610, 1800, 1850, 1600, null);
	Dato test5 = new Dato(180, 200, 220, 175, null);
	Dato test6 = new Dato(20, 15, 20, 10, null);
	Vector<Dato>datiTest= new Vector<>();
	
	public MediaMobileTest() {
		datiTest.add(test1);
		datiTest.add(test2);
		datiTest.add(test3);
		datiTest.add(test4);
		datiTest.add(test5);
		datiTest.add(test6);
	}

	@Test(expected = StrategiaException.class )//eccezzione (corta >= lunga)
	public void TestMediaMobileException0() 
	{
		
		MediaMobile a= new MediaMobile(11,2,datiTest);
		}
	
	
	
	@Test(expected = StrategiaException.class )	//eccezzione (lunga > dati.size())
	public void TestMediaMobileExcpetion1()
	{
		
		MediaMobile a =	new MediaMobile(3,9,datiTest);
		
			
		
	}
	
	
	@Test
	public void testCalcolaStrategia() {
		
		MediaMobile testMediaMobile = new MediaMobile(1,3,datiTest);
		int[] vettore = {1,1,0,0};
		int[]vettoretest=testMediaMobile.calcolaStrategia();
		assertArrayEquals(vettoretest, vettore);
	}

	
}