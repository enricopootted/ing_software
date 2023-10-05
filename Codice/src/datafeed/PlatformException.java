package datafeed;

public class PlatformException extends RuntimeException
{
	private String messaggio;
	
	public PlatformException(String messaggio)
	{
		this.messaggio = messaggio;
	}
	public void errore()
	{
		System.err.println("Errore: " + messaggio);
	}
}
