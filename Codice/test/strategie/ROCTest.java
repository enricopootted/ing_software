package strategie;

import static org.junit.Assert.*;
import java.util.Vector;
import org.junit.Test;
import datafeed.Dato;
import strategie.ROC;
import strategie.StrategiaException;

public class ROCTest {
	Dato test1 = new Dato(10, 15, 25, 5, null);
	Dato test2 = new Dato(12, 112, 255, 50, null);
	Dato test3 = new Dato(14, 40, 36, 51, null);
	Dato test4 = new Dato(16, 55, 40, 101, null);
	Dato test5 = new Dato(18, 25, 30, 22, null);
	Vector<Dato>datiTest= new Vector<>();
	int testPeriodoROC =1;

@Test
	public void testCalcolaStrategia() {
		datiTest.add(test5);
		datiTest.add(test2);
		datiTest.add(test3);
		datiTest.add(test4);
		datiTest.add(test1);
		int periodo1=1;
		int periodo2=2;
		ROC testRoc = new ROC(periodo1,periodo2,datiTest);
		int[] vettore= {0,1,0};
		assertArrayEquals(vettore, testRoc.calcolaStrategia());
	}
	 
	

@Test(expected=StrategiaException.class)
	public void testROC() {
		datiTest.add(test1);
		datiTest.add(test2);
		new ROC(2,4,datiTest);
	}
	
}