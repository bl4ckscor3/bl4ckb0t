package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Twitter extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length == 2)
			Utilities.chanMsg(event, "http://www.twitter.com/" + args[1]);
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}
	
	@Override
	public String[] getAliases()
	{
		return new String[]{"tw", "twitter"};
	}
	
	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-tw <" + L10N.getString("tw.help.profile", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-tw <" + L10N.getString("tw.help.profile", event) + "> || " + L10N.getString("tw.explanation", event)};
	}
}
