package datafeed;

public class PlatformCreator implements PlatformFactory
{
	private static PlatformCreator istanza;
	
	private PlatformCreator() //devo specificare che il costruttore è private
	{
	}
	
	public Platform createPlatform(String inizio, String fine, String tf, String simbolo, int scelta) throws PlatformException
	{
		//Factory pattern
		switch(scelta)
		{
			case 1: return new Binance(simbolo, inizio, fine, tf);
			case 2: return new Yahoofin(inizio, fine, tf, simbolo);
			default: throw new PlatformException("Piattaforma scelta non esistente");
		}
	}
	
	public static PlatformCreator getPlatformCreator()	//Singleton pattern
	{
		if(istanza == null)
			istanza = new PlatformCreator();
		
		return istanza;
	}
}
