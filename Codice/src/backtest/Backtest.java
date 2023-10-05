package backtest;

import datafeed.Dato;
import prog.io.ConsoleOutputManager;
import javax.swing.JPanel;

import java.util.Vector;

public class Backtest
{
	private int stato[];
	private Vector<Dato> dati;
	
	public Backtest(int vettore[], Vector<Dato> dati)
	{
		this.stato = vettore;
		this.dati = new Vector<Dato>();
		
		for (int i = dati.size() - vettore.length; i < dati.size(); i++)
			this.dati.add(dati.get(i));
	}
	
	public Vector<Trade> backTest()
	{
		Trade trade = null;
		Vector<Trade> trades = new Vector<Trade>();

		
		for (int i = 1; i < stato.length; i++)
		{
			if(stato[i] == 1 && stato[i-1] == 0)
				trade = new Trade(dati.get(i).getTime(), dati.get(i).getClose());
			
			if(stato[i] == 0 && stato[i-1] == 1)
			try
			{
				trade.setFine(dati.get(i).getTime(), dati.get(i).getClose());
				trades.add(trade);
			}
			catch(NullPointerException e) {}
		}
		
		return trades;
	}
	
}

