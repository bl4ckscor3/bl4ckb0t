package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Decide extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length >= 1 && event.getMessage().endsWith("?"))
		{
			int decision = new Random().nextInt(101);
			String channel = event.getChannel().getName();
			
			if(decision >= 0 && decision <= 49)
				Utilities.sendMessage(channel, L10N.getString("decide.no", channel));
			else if(decision >= 50 && decision <= 100)
				Utilities.sendMessage(channel, L10N.getString("decide.yes", channel));
			else
				Utilities.sendMessage(channel, L10N.getString("decide.fail", channel) + ": " + decision);
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"decide"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-decide <" + L10N.getString("decide.help.question", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-decide <" + L10N.getString("decide.help.question", channel) + "> || " + L10N.getString("decide.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("decide.notes", channel);
	}
}
