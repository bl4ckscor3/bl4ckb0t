package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Trello implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event)
	{
		Utilities.chanMsg(event, "https://trello.com/b/039j1jFa/bl4ckb0t");
	}

	@Override
	public String getAlias()
	{
		return "trello";
	}

	@Override
	public String getSyntax()
	{
		return "-trello";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-trello | " + L10N.getString("trello.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
