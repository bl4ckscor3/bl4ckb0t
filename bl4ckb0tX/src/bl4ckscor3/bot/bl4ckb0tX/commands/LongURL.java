package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class LongURL implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception 
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 1)
			Utilities.chanMsg(event, "you need to specify a short link which you want me to expand. Usage: -longurl <shortLink>");
		else if(args.length == 2)
		{
			String longUrl;
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://api.longurl.org/v2/expand?url=" + args[1]).openStream()));
			
			reader.readLine();
			reader.readLine();
			longUrl = "h" + reader.readLine().split("h")[1];
			longUrl = longUrl.split("]")[0];
			
			Utilities.chanMsg(event, "This is the long url of the given link: " + longUrl);
		}
		else
			Utilities.respond(event, "I don't know what you did, but you just need to specify a short link, nothing else...", true);
	}

	@Override
	public String getAlias() 
	{
		return "longurl";
	}
}
