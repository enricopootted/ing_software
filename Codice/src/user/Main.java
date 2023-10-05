package user;

import prog.io.ConsoleInputManager;
import prog.io.FileNonPresenteException;

import org.json.JSONException;

import backtest.*;
import datafeed.*;
import strategie.*;

import java.io.IOException;
import java.util.Vector;

public class Main 
{
	private static int stato[];
	private static Vector<Dato> dati;
	
	public static void main(String[] args) throws IOException 
	{
		int scelta;
		
		do 
		{
			scelta = menu();
		}
		while(scelta != 0);
	}
	 
	public static int menu() throws IOException
	{
		
		System.out.println();
		System.out.println("1-Scarica dei dati ed esegui il test di una strategia");
		System.out.println("2-Carica e visualizza dei dati da file");
		System.out.println("0-Esci dal programma");
		System.out.println("-->");
		ConsoleInputManager in = new ConsoleInputManager();
		int scelta = in.readInt();
		
		switch(scelta)
		{
			case 1: 
					analisi();
					break;
			
			case 2: 
					String nome = in.readLine("Inserisci nome del file-->");
					try
					{
						Vector<Trade> trades = GestoreFile.caricaDaFile(nome);
						Risultati risultati = new Risultati(trades);
						risultati.stampaRisultati();
					}
					catch(NullPointerException e)
					{
						System.err.println("Nessun trade nel file");
					}
					catch(FileNonPresenteException e)
					{
						System.err.println("Il file inserito non esiste");
					}
					break;
					
			case 0: System.out.println("Chiusura software in corso...");
					break;
			default: System.out.println("Comando inserito non valido");
				
		}
		
		return scelta;
	}
	
	public static void analisi()
	{		
		if(setPiattaforma())
		{
			if(setStrategia())
				testStrategia();
		}
	}
	
	public static boolean setPiattaforma()
	{
		//esempio binance: 1, 01-01-2019, 01-01-2021, BTCUSDT, 1d
		//esempio yahoo finance: 2, 01-01-2018, 01-01-2022, tsla, 1d

		System.out.println("Set della piattaforma. Inserisci:");
		ConsoleInputManager in = new ConsoleInputManager();
		int sceltaPiattaforma=in.readInt("1-Binance, 2-YahooFinance -->");
		String inizio = in.readLine("Data di inizio, formato dd-MM-aaaa -->");
		String fine = in.readLine("Data di inizio, formato dd-MM-aaaa -->");
		String simbolo = in.readLine("Simbolo -->");
		String tf = in.readLine("Inserisci timeframe\nDisponibili per binance: 1m, 3m, 5m, 15m, 30m, 1h, 2h, 4h, 8h, 12h, 1d, 3d, 1w, 1M\nDisponibili per YahooFinance: 1d, 1w, 1M\n-->");

		PlatformFactory pf = PlatformCreator.getPlatformCreator();
		Platform platform = pf.createPlatform(inizio, fine, tf, simbolo, sceltaPiattaforma);
		try
		{
			dati = platform.loadData();
		}
		catch(IOException e) {System.out.println("Problema nella connessione alla piattaforma"); return false;}
		catch(JSONException e) {System.out.println("Problema nella conversione JSON"); return false;}
		catch(PlatformException e) {e.errore(); return false;}
		
		return true;
	}

	public static boolean setStrategia() 
	{
		System.out.println("1-Strategia a medie mobili");
		System.out.println("2-Strategia ROC");
		System.out.println();
		
		ConsoleInputManager in = new ConsoleInputManager();
		int sceltaStrategia = in.readInt();
		
		Strategia strategia;
		
		switch(sceltaStrategia)
		{
			case 1:
				System.out.println("Inserisci periodo media lunga-->");
				int lunga = in.readInt();
				System.out.println("Inserisci periodo media corta-->");
				int corta = in.readInt();
				
				try
				{
					strategia = new MediaMobile(corta, lunga, dati);
					stato = strategia.calcolaStrategia();
				}
				catch(StrategiaException e)
				{
					e.errore();
					return false;
				}
				break;
				
			case 2:
				System.out.println("Inserisci periodo ROC-->");
				int roc = in.readInt();
				System.out.println("Inserisci periodo media-->");
				int periodo = in.readInt();
				
				try
				{
					strategia = new ROC(periodo, roc, dati);
					stato = strategia.calcolaStrategia();
				}
				catch(StrategiaException e) 
				{
					e.errore();
					return false;
				}
				
				break;
				
			default: 
				System.out.println("Inserire codice strategia valido");
				return false;

		}
		
		return true;
	}
	
	public static void testStrategia()
	{
		Backtest test = new Backtest(stato, dati);
		Vector<Trade> trades = test.backTest();

		try
		{
			Risultati risultati = new Risultati(trades);
			risultati.stampaRisultati();
			
			ConsoleInputManager in = new ConsoleInputManager();
			String nome = in.readLine("Vuoi salvare quest'analisi su un file? Inserisci 'si'-->");
			if(nome.equals("si"))
			{
				nome = in.readLine("Inserisci nome del file-->");
				GestoreFile.salvaSuFile(nome, trades);
			};
		}
		catch(NullPointerException e)
		{
			System.err.println("Nessun trade effettuato");
		}
	}
}
