package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class LongURL implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
		{
			String longUrl = "";
			String temp;
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://api.longurl.org/v2/expand?url=" + args[1]).openStream()));
			
			reader.readLine();
			reader.readLine();
			longUrl = reader.readLine();
			temp = longUrl;
			longUrl = "http:" + temp.split(":")[1].split("]")[0];
			
			Utilities.chanMsg(event, L10N.getString("longurl.output", event) + ": " + longUrl);
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias() 
	{
		return "longurl";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-longurl <" + L10N.getString("longurl.help.shortUrl", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-longurl <" + L10N.getString("longurl.help.shortUrl", event) + "> || " + L10N.getString("longurl.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("longurl.notes", event);
	}
	
	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
