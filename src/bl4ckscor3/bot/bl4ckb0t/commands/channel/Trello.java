package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Trello extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args)
	{
		Utilities.chanMsg(event, "https://trello.com/b/039j1jFa/bl4ckb0t");
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"trello", "tr"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-trello";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{"-trello | " + L10N.getString("trello.explanation", event)};
	}
}
