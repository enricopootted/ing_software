package datafeed;

import java.time.LocalDateTime;

public class Dato 
{
	private double close;
	private double open;
	private double max;
	private double min;
	private LocalDateTime time;
	
	public Dato(double close, double open, double max, double min, LocalDateTime time)
	{
		this.close = close;
		this.open = open;
		this.max = max;
		this.min = min;
		this.time = time;
	}
	
	
	public double getClose()
	{
		return close;
	}
	
	public double getOpen()
	{
		return open;
	}
	
	public double getMax()
	{
		return max;
	}
	
	public double getMin()
	{
		return min;
	}
	
	public LocalDateTime getTime()
	{
		return time;
	}
	
	public void stampa()
	{
		System.out.println("Time: " + time + "\tOpen: " + open + "\tMax: " + max + "\tMin: " + min + "\tClose: " + close);
	}
	
}
