package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;

//placeholder class to be able to set the help menu...
public class Ping extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws Exception{}

	@Override
	public String[] getAliases()
	{
		return new String[]{"ping", "pong"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-ping";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{
				"-ping || " + L10N.getString("ping.explanation", event)
		};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("ping.notes", event);
	}
}
