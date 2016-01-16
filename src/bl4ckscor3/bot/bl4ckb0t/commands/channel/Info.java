package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.lang.management.ManagementFactory;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Info extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws Exception
	{
		Utilities.chanMsg(event, Colors.BOLD + "** " + L10N.getString("info.version", event).replace("#version", Colors.BOLD + Core.bot.getConfiguration().getVersion()) +
				Colors.BOLD + " ** " + L10N.getString("info.uptime", event).replace("#uptime", Colors.BOLD + Utilities.millisToTimestamp(ManagementFactory.getRuntimeMXBean().getUptime())) + 
				Colors.BOLD + " ** " + L10N.getString("info.author", event).replace("#author", Colors.BOLD + "bl4ckscor3") + Colors.BOLD + " **");
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"info", "about"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-info";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-info || " + L10N.getString("info.explanation", event)};
	}
}
