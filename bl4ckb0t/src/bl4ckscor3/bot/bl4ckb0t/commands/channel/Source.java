package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Source extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args)
	{
		Utilities.sendMessage(event.getChannel().getName(), "https://github.com/bl4ckscor3/bl4ckb0t");
	}
	
	@Override
	public String[] getAliases()
	{
		return new String[]{"source"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-source";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-source || " + L10N.getString("source.explanation", channel)};
	}
}
