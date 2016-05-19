package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;

/**
 * Placeholder class to be able to set the help menu
 */
public class Ping extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws Exception{}

	@Override
	public String[] getAliases()
	{
		return new String[]{"ping", "pong"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-ping";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-ping || " + L10N.getString("ping.explanation", channel)
		};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("ping.notes", channel);
	}
}
