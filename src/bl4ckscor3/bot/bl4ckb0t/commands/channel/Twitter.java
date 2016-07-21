package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Twitter extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException, IOException
	{
		if(args.length == 1)
		{
			String channel = event.getChannel().getName();
			
			try
			{
				Jsoup.connect("http://www.twitter.com/" + args[0]).get();
				Utilities.sendMessage(channel, "http://www.twitter.com/" + args[0]);
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
		return new String[]{"tw", "twitter"};
	}
	
	@Override
	public String getSyntax(String channel)
	{
		return "-tw <" + L10N.getString("tw.help.profile", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-tw <" + L10N.getString("tw.help.profile", channel) + "> || " + L10N.getString("tw.explanation", channel)};
	}
}
