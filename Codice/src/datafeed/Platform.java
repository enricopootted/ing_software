package datafeed;


import java.util.Vector;
import java.io.IOException;
import org.json.JSONException;

public abstract class Platform 
{
	public abstract Vector<Dato> loadData() throws IOException, JSONException;
}
