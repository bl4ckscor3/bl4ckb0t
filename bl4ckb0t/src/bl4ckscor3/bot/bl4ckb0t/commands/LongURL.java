package bl4ckscor3.bot.bl4ckb0t.commands;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class LongURL implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception 
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 1)
			Utilities.chanMsg(event, "You need to specify a short link which you want me to expand. Usage: -longurl <shortLink>");
		else if(args.length == 2)
		{
			String longUrl = "";
			String temp;
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://api.longurl.org/v2/expand?url=" + args[1]).openStream()));
			
			reader.readLine();
			reader.readLine();
			longUrl = reader.readLine();
			temp = longUrl;
			longUrl = "http:" + temp.split(":")[1].split("]")[0];
			
			Utilities.chanMsg(event, "This is the long url of the given link: " + longUrl);
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}

	@Override
	public String getAlias() 
	{
		return "longurl";
	}

	@Override
	public String getSyntax()
	{
		return "-longurl <shortUrl>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-longurl <shortUrl> || Enlarges the given link if possible."};
	}

	@Override
	public String getNotes()
	{
		return "Some link shorteners may not work.";
	}
}
