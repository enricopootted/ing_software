package datafeed;

import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import prog.io.ConsoleInputManager;
import java.time.format.DateTimeFormatter;


public class Binance extends Platform
{ 
	
	private String link;
	
	public Binance(String symbol, String inizio, String fine, String timeframe)
	{
		link = "https://api.binance.com/api/v3/klines?";
		
		link += "symbol=";
		link += symbol.toUpperCase();
		
		link += "&interval=";
		link += timeframe;
		
		DateTimeFormatter tf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		link += "&startTime=";
		link += Long.toString(1000*LocalDateTime.parse(inizio + " 00:00:00", tf).toEpochSecond(ZoneOffset.UTC));
		
		link += "&endTime=";
		link += Long.toString(1000*LocalDateTime.parse(fine + " 00:00:00", tf).toEpochSecond(ZoneOffset.UTC));

		link += "&limit=1000";
	}
	
	@Override
	public Vector<Dato> loadData() throws IOException, JSONException, PlatformException
	{
		Vector<Dato> dati = new Vector<Dato>();
				
		HttpURLConnection connection = null;
		
		
			URL url = new URL(link);
			URLConnection urlConnection = url.openConnection();
			connection = (HttpURLConnection) urlConnection;
			
			connection.setRequestMethod("GET");
			
			connection.connect();
			
			int responseCode = connection.getResponseCode();
			
			
			if(responseCode == 200)
			{
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = bufferedReader.readLine();
				
				JSONArray jArr = new JSONArray(line);

				for (int i = 0; i < jArr.length(); i++)
				{
					JSONArray jArr2 = jArr.getJSONArray(i);
					
					LocalDateTime time = LocalDateTime.ofEpochSecond(jArr2.getLong(0)/1000, 0, ZoneOffset.UTC);
					
					Dato dato = new Dato(jArr2.getDouble(4), jArr2.getDouble(1), jArr2.getDouble(2), jArr2.getDouble(3), time);
					dati.add(dato);
				}				
			}
			else
			{
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				String line = bufferedReader.readLine();
				JSONObject jObj = new JSONObject(line);
				int codice = jObj.getInt("code");
				String msg = jObj.getString("msg");

				throw new PlatformException("\nCodice: " + codice + "\nMsg: " + msg + "\nhai inserito correttamente i parametri?");
			}
		
		return dati;
	}
}
