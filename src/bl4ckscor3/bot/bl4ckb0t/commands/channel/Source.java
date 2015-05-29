package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Source implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event)
	{
		Utilities.chanMsg(event, "https://github.com/bl4ckscor3/bl4ckb0t");
	}
	
	@Override
	public String getAlias()
	{
		return "source";
	}

	@Override
	public String getSyntax()
	{
		return "-source";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-source || " + L10N.getString("source.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
