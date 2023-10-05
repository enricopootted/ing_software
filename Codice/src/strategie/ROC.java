package strategie;
import datafeed.Dato;
import java.util.Vector;

public class ROC extends Strategia
{
	private int periodoMedia;
	private int periodoROC;
	
	public ROC(int periodoMedia, int periodoROC, Vector<Dato> dati) throws StrategiaException
	{
		if ((periodoROC + periodoMedia) > dati.size()) throw new StrategiaException("Numero di dati non sufficiente per i periodi inseriti");
		
		this.periodoMedia = periodoMedia;
		this.periodoROC = periodoROC;
		super.dati = dati;
	}
	
	public int[] calcolaStrategia()
	{
		double roc[] = calcolaROC();
		roc = calcolaMedia(periodoMedia, roc);
		int stato[] = new int[roc.length];
		 
		for (int i = 0; i < stato.length; i++)
		{
			if (roc[i] < 0) stato[i] = 0;
			else stato[i] = 1;
		}

		return stato;
	}
	
	private double[] calcolaROC()
	{
		double roc[] = new double[dati.size() - periodoROC];
		
		for (int i = 0; i < roc.length; i++)
			roc[i] = 100*(dati.get(i+periodoROC).getClose()-dati.get(i).getClose())/dati.get(i).getClose();
		
		return roc;
	}
	
}
