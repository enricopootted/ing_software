package backtest;

import java.util.Vector;
import org.math.plot.*;
import javax.swing.JFrame;

import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

public class Risultati 
{
	private double profit; 						//profitto in percentuale
	private int nWin;      						//numero trade in profitto
	private int nTrade;    						//totale numero trade
	private double maxDrowdown; 				//Drowdown massimo assoluto
	private double[] equityCurve;
	private Vector<Trade> trades;
	
	public Risultati()
	{
		profit = 0;
		nWin = 0;
		nTrade = 0;
		maxDrowdown = 0;
	}
	
	public Risultati(Vector<Trade> trades)
	{
		this();
		this.trades = trades;
	}
		
	private void calcolaRisultati()
	{
		equityCurve = new double[trades.size() + 1];
		equityCurve[0] = 1;
		
		double massimo = 1;
		double minimo = 1;
		double drowdown = 0;
		
		for(Trade trade: trades)
		{
			nTrade++;
			equityCurve[nTrade] = equityCurve[nTrade - 1] + trade.getProfit();
			profit += trade.getProfit();
			if(trade.getProfit() > 0) nWin++;
			if(equityCurve[nTrade] > massimo)
			{
				if ((massimo - minimo) > drowdown) drowdown = massimo - minimo;
				
				massimo = equityCurve[nTrade];
				minimo = massimo;
			}
			else if(equityCurve[nTrade] < minimo) minimo = equityCurve[nTrade];
		}
		
		if ((massimo - minimo) > drowdown) drowdown = massimo - minimo;
		maxDrowdown = drowdown;
		
	}
	
	public void stampaRisultati() throws NullPointerException
	{
		calcolaRisultati();
		ConsoleOutputManager out = new ConsoleOutputManager();
		out.println("REPORT ANALISI");
		out.println("Profitto/Perdita totale: " + profit*100);
		double percentuale = 100*nWin/nTrade;
		out.println("Numero di trade: " + nTrade + "\tNumero trade in profitto: " + nWin + "\tPercentuale: " + percentuale);
		out.println("Max drowdown: " + 100*maxDrowdown);
		System.out.println();
		
		for(Trade trade : trades)
			trade.stampaTrade();
		
		plotEquityCurve();
	}
	
	private void plotEquityCurve()
	{
		double[] x = new double[equityCurve.length];
		for (int i=0; i < equityCurve.length; i++)
			x[i] = i;
		
		  Plot2DPanel plot = new Plot2DPanel();
		 
		  plot.addLinePlot("Equity curve", x, equityCurve);
		  plot.setAxisLabel(0, "N. Trade");
		  plot.setAxisLabel(1, "Capitale");
		 
		  JFrame frame = new JFrame("Equity curve");
		  frame.setTitle("Equity curve");
		  frame.setContentPane(plot);
		  frame.setVisible(true);
	}
	
}
