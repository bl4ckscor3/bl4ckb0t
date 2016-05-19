package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Twitter extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length == 1)
			Utilities.sendMessage(event.getChannel().getName(), "http://www.twitter.com/" + args[0]);
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
