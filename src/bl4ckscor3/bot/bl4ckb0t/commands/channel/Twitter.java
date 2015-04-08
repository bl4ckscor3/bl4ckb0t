package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Twitter implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
			Utilities.respond(event, "http://www.twitter.com/" + args[1], false);
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}
	
	@Override
	public String getAlias()
	{
		return "tw";
	}
	
	@Override
	public String getSyntax()
	{
		return "-tw <" + L10N.getString("tw.help.profile") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-tw <" + L10N.getString("tw.help.profile") + "> || " + L10N.getString("tw.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
