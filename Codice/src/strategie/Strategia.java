package strategie;

import java.util.Vector;
import datafeed.Dato;

public abstract class Strategia 
{
	public abstract int[] calcolaStrategia();
	protected Vector<Dato> dati;
	
	protected double[] calcolaMedia(int periodo, double serie[])
	{
		double media[] = new double[serie.length - periodo + 1];
		
		for (int i = 0; i < media.length; i++)
		{
			int somma = 0;
			
			for (int j = 0; j < periodo; j++)
				somma += serie[i];
			
			media[i] = somma/periodo;
		}
		
		return media;
	}
}
