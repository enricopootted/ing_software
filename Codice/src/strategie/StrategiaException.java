package strategie;

public class StrategiaException extends RuntimeException
{
	private String messaggio;
	
	public StrategiaException(String messaggio)
	{
		this.messaggio = messaggio;
	}
	public void errore()
	{
		System.err.println("Errore: " + messaggio);
	}
}
