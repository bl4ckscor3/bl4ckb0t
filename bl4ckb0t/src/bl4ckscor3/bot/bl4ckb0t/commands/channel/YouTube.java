package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class YouTube extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException, IOException
	{
		if(args.length == 1)
		{
			String channel = event.getChannel().getName();
			
			try
			{
				Jsoup.connect("http://www.youtube.com/" + args[0]).get();
				Utilities.sendMessage(channel, "http://www.youtube.com/" + args[0]);
			}
			catch(HttpStatusException e)
			{
				if(e.getStatusCode() == 404)
					Utilities.sendMessage(channel, L10N.getString("socials.error", channel));
			}
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"yt", "youtube"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-yt <" + L10N.getString("yt.help.channel", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-yt <" + L10N.getString("ytv.help.channel", channel) + "> || " + L10N.getString("yt.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("yt.notes", channel);
	}
}