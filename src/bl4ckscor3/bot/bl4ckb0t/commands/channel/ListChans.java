package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ListChans extends BaseChannelCommand
{
	public static int secretChannelAmount = 0;

	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException, MalformedURLException, IOException
	{
		String[] chans = Utilities.getJoinedChannels(false);
		String result = "";
		String channel = event.getChannel().getName();
		
		for(String s : chans)
		{
			if(s != null)
				result += (s + " | ");
		}

		if(result.lastIndexOf(" | ") != -1)
			result = result.substring(0, result.lastIndexOf(" | "));

		if(secretChannelAmount != 0)
			result += L10N.getString("listchans.secret", channel).replace("#secretChannelAmount", "" + secretChannelAmount);

		Utilities.sendMessage(channel, L10N.getString("listchans.list", channel) + ": " + result);
		secretChannelAmount = 0;

		if(args.length == 1 && args[0].equalsIgnoreCase("show"))
		{
			if(Utilities.isValidUser(event.getUser()))
			{
				Utilities.sendMessage(event.getUser().getNick(), L10N.getString("listchans.secret.show", channel));

				for(String s : Utilities.getSecretChannels())
				{
					Utilities.sendMessage(event.getUser().getNick(), s);
				}
			}
			else
				Utilities.sendMessage(channel, L10N.getString("listchans.secret.show.fail", channel));
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"listchans", "lc"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-listchans [show]";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-listchans || " + L10N.getString("listchans.explanation.1", channel),
				"-listchans show || " + L10N.getString("listchans.explanation.2", channel)
		};
	}
}
