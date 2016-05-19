package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Twitch extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length == 1)
			Utilities.sendMessage(event.getChannel().getName(), "http://www.twitch.tv/" + args[01]);
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}
	
	@Override
	public String[] getAliases()
	{
		return new String[]{"tv", "twitch"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-tv <" + L10N.getString("tv.help.channel", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-tv <" + L10N.getString("tv.help.channel", channel) + "> || " + L10N.getString("tv.explanation", channel)};
	}
}
