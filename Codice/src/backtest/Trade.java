package backtest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Trade 
{
	private LocalDateTime inizio;
	private LocalDateTime fine;
	private double buyPrice;
	private double sellPrice;
	
	public Trade(LocalDateTime inizio, double bp)
	{
		this.inizio = inizio;
		buyPrice = bp;
	}
	
	public void setFine(LocalDateTime fine, double sp)
	{
		this.fine = fine;
		sellPrice = sp;
	}
	
	public double getProfit()
	{
		return (sellPrice - buyPrice)/buyPrice;
	}
	
	public void stampaTrade()
	{
		System.out.println("Data ingresso: " + inizio + "\tPrezzo: " + buyPrice);
		System.out.println("Data uscita: " + fine + "\tPrezzo: " + sellPrice);
		System.out.println("Profitto/Perdita: " + getProfit()*100);
		System.out.println();
	}
	
	public Long getInizio()
	{
		return inizio.toEpochSecond(ZoneOffset.UTC);
	}
	
	public Long getFine()
	{
		return fine.toEpochSecond(ZoneOffset.UTC);
	}
	
	public double getBuyPrice()
	{
		return buyPrice;
	}
	
	public double getSellPrice()
	{
		return sellPrice;
	}
}
