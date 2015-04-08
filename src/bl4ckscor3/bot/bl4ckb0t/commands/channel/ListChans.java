package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ListChans implements ICommand<MessageEvent<Bot>>
{
	public static int secretChannelAmount = 0;

	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException, MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] chans = Utilities.getJoinedChannels(false);
		String result = "";

		for(String s : chans)
		{
			if(s != null)
				result += (s + " | ");
		}

		result = result.substring(0, result.lastIndexOf(" | "));
		
		if(secretChannelAmount != 0)
			result += " + " + secretChannelAmount + " " + L10N.strings.getString("listchans.secret");

		Utilities.chanMsg(event, L10N.strings.getString("listchans.list") + ": " + result);
		secretChannelAmount = 0;
		
		if(args.length == 2 && args[1].equalsIgnoreCase("show"))
		{
			if(Utilities.isValidUser(event))
			{

				Utilities.pm(event.getUser().getNick(), L10N.strings.getString("listchans.secret.show"));

				for(String s : Utilities.getSecretChannels())
				{
					Utilities.pm(event.getUser().getNick(), s);
				}
			}
			else
				Utilities.chanMsg(event, L10N.strings.getString("listchans.secret.show.fail"));
		}
	}

	@Override
	public String getAlias()
	{
		return "listchans";
	}

	@Override
	public String getSyntax()
	{
		return "-listchans [show]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-listchans || " + L10N.strings.getString("listchans.explanation.1"),
				"-listchans show || " + L10N.strings.getString("listchans.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
