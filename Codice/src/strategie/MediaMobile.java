package strategie;

import datafeed.Dato;
import java.util.Vector;

public class MediaMobile extends Strategia
{
	private int lunga;
	private int corta;
	
	public MediaMobile(int corta, int lunga, Vector<Dato> dati) throws StrategiaException
	{
		if(corta >= lunga) throw new StrategiaException("Valore delle medie non ammissibile. Inserire un periodo corto minore del lungo");
		if(lunga > dati.size()) throw new StrategiaException("Dati non sufficienti per i periodi inseriti");
		
		this.dati = dati;
		this.corta = corta;
		this.lunga = lunga;
	}
	
	private double[] gestisciGradiDiLiberta(double[] mediaCorta)
	{
		double nuovaMedia[] = new double[mediaCorta.length + corta - lunga];
		for (int i = lunga - corta; i < mediaCorta.length; i++)
			nuovaMedia[i + corta - lunga] = mediaCorta[i];
		
		return nuovaMedia;
	}
	
	public int[] calcolaStrategia()
	{
		double serie[] = new double[dati.size()];
		for (int i = 0; i < dati.size(); i++)
			serie[i] = dati.get(i).getClose();
		
		double[] mediaCorta = calcolaMedia(corta, serie);
		double[] mediaLunga = calcolaMedia(lunga, serie);
		
		mediaCorta = gestisciGradiDiLiberta(mediaCorta);
		int[] vettore = new int[mediaCorta.length];
		
		for (int i = 0; i < mediaCorta.length; i++)
		{
			if(mediaCorta[i] > mediaLunga[i])
				vettore[i] = 1;
			else
				vettore[i] = 0;
		}
		
		return vettore;
	}

}
	

