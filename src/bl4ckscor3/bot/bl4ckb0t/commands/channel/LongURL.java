package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class LongURL extends BaseCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		if(args.length == 2)
		{
			String longUrl = "";
			String temp;
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://api.longurl.org/v2/expand?url=" + args[1]).openStream()));
			
			reader.readLine();
			reader.readLine();
			longUrl = reader.readLine();
			reader.close();
			temp = longUrl;
			longUrl = "http:" + temp.split(":")[1].split("]")[0];
			Utilities.chanMsg(event, L10N.getString("longurl.output", event).replace("#long", longUrl));
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"longurl"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-longurl <" + L10N.getString("longurl.help.shortUrl", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{"-longurl <" + L10N.getString("longurl.help.shortUrl", event) + "> || " + L10N.getString("longurl.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent event)
	{
		return L10N.getString("longurl.notes", event);
	}
}
