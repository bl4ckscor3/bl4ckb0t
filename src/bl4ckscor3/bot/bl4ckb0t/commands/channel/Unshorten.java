package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Unshorten extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		if(args.length == 1)
		{
			Document doc = Jsoup.connect("http://api.unshorten.it?shortURL=" + args[0] + "&apiKey=" + Passwords.UNSHORTENITAPIKEY.getPassword()).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0").get();
			String channel = event.getChannel().getName();
			String output = doc.text();
			
			if(output.equals("error (0)"))
				Utilities.sendMessage(channel, L10N.getString("unshorten.invalidURL", channel));
			else if(output.equals("error (3)"))
				Utilities.sendMessage(channel, L10N.getString("unshorten.couldntUnshort", channel));
			else
				Utilities.sendMessage(channel, L10N.getString("unshorten.output", channel).replace("#url", doc.text()));
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"unshorten", "longurl"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-unshorten <" + L10N.getString("unshorten.help.shortUrl", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-unshorten <" + L10N.getString("unshorten.help.shortUrl", channel) + "> || " + L10N.getString("unshorten.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("unshorten.notes", channel);
	}
}
