package datafeed;

public interface PlatformFactory 
{
	public Platform createPlatform(String inizio, String fine, String tf, String simbolo, int scelta) throws PlatformException;
}
