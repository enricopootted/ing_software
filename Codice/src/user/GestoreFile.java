package user;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.StringTokenizer;
import java.util.Vector;

import backtest.Trade;
import prog.io.FileInputManager;
import prog.io.FileOutputManager;
import java.time.LocalDate;

public class GestoreFile 
{
	public static Vector<Trade> caricaDaFile(String nome)
	{
		Vector<Trade> trades = new Vector<Trade>();
		
		FileInputManager in = new FileInputManager(nome);
		
		String line;
		
		while((line = in.readLine()) != null)
		{
			StringTokenizer st = new StringTokenizer(line, "_");
			double pI = Double.parseDouble(st.nextToken());
			double pF = Double.parseDouble(st.nextToken());
			LocalDateTime inizio = LocalDateTime.ofEpochSecond(Long.parseLong(st.nextToken()), 0, ZoneOffset.UTC);
			LocalDateTime fine = LocalDateTime.ofEpochSecond(Long.parseLong(st.nextToken()), 0, ZoneOffset.UTC);
			Trade trade = new Trade(inizio, pI);
			trade.setFine(fine, pF);
			trades.add(trade);
		}
		
		return trades;
	}
	
	public static void salvaSuFile(String nome, Vector<Trade> trades)
	{
		FileOutputManager out = new FileOutputManager(nome);
		
		String linea;
		
		for(Trade trade: trades)
		{
			linea = Double.toString(trade.getBuyPrice()) + "_";
			linea += Double.toString(trade.getSellPrice()) + "_";
			linea += Long.toString(trade.getInizio()) + "_";
			linea += Long.toString(trade.getFine());
			
			out.println(linea);
		}
	}
}
