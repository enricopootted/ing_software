package datafeed;

import prog.io.ConsoleInputManager;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import yahoofinance.Stock;
import yahoofinance.histquotes.Interval;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.*;


public class Yahoofin  extends Platform
{

	private Calendar from;
	private Calendar to;
	private Interval tf;
	private String simbolo;
	
	public Yahoofin(String inizio, String fine, String tf, String simbolo) throws PlatformException
	{
		from = Calendar.getInstance();
		to = Calendar.getInstance();
		
		StringTokenizer st = new StringTokenizer(inizio, "-");
		String giorno = st.nextToken();
		String mese = st.nextToken();
		String anno = st.nextToken();
		from.set(Integer.parseInt(anno), Integer.parseInt(mese), Integer.parseInt(giorno));
		
		st = new StringTokenizer(fine, "-");
		giorno = st.nextToken();
		mese = st.nextToken();
		anno = st.nextToken();
		to.set(Integer.parseInt(anno), Integer.parseInt(mese), Integer.parseInt(giorno));
		
		this.simbolo = simbolo.toUpperCase();
		
		switch(tf)
		{
			case "1d": this.tf = Interval.DAILY; break;
			case "1w": this.tf = Interval.WEEKLY; break;
			case "1M": this.tf = Interval.MONTHLY; break;
			default: throw new PlatformException("inserisci un timeframe valido");
		}

	}
	
	public Vector<Dato> loadData() throws IOException
	{
		Vector<Dato> dati = new Vector<Dato>();	
		List<HistoricalQuote> googleHistQuotes = null;
	
	
		Stock google = YahooFinance.get(simbolo);		//quote da scaricare"simbolo" corregge minuscolo
		googleHistQuotes = google.getHistory(from, to, tf);				//forzo timeframe a giornaliero
	
	
		for (HistoricalQuote quote : googleHistQuotes) 
		{
			Dato trasferisci = new Dato(quote.getClose().doubleValue(),quote.getOpen().doubleValue(),
									quote.getHigh().doubleValue(),quote.getLow().doubleValue(),
									LocalDateTime.ofInstant(quote.getDate().toInstant(),quote.getDate().getTimeZone().toZoneId()));
			dati.add(trasferisci);
		}
		return dati;
	}
}

