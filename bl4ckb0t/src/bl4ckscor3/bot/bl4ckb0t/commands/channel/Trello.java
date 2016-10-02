package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Trello extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args)
	{
		Utilities.sendMessage(event.getChannel().getName(), "https://trello.com/b/039j1jFa/bl4ckb0t");
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"trello", "tr"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-trello";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-trello | " + L10N.getString("trello.explanation", channel)};
	}
}
