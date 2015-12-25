package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Calculate extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException
	{
		Utilities.chanMsg(event, L10N.getString("calc.disabled", event));
	}

	@Override
	public String getAlias()
	{
		return "calc";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event) 
	{
		return "";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event) 
	{
		return new String[]{};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event) 
	{
		return L10N.getString("calc.disabled", event);
	}
}
