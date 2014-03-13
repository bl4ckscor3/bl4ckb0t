package bl4ckb0tX.commands.normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Test
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event) throws IOException
	{
		if(Stuffz.validUser(event))
		{
//			Stuffz.chanMsg(event, "Nothing to see here");
			try
			{
				String user = Stuffz.seperate(event);
				BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://axis.iaero.me/accstatus?username=" + user + "&format=plain").openStream()));
				String status = reader.readLine();
				Stuffz.chanMsg(event, "status");
			}
			catch (ArrayIndexOutOfBoundsException e) 
			{
				event.respond("Provide a username for me please!");
			}
		}
		else
		{
			Stuffz.sorry(event);
		}
	}
}
