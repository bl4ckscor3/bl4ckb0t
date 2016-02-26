package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.lang.management.ManagementFactory;
import java.net.JarURLConnection;
import java.util.Date;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.TimeParser;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Info extends BaseCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		Utilities.sendStarMsg(event,
				Colors.BOLD + L10N.getString("info.version", event).replace("#version", Colors.NORMAL + Core.bot.getConfiguration().getVersion()),
				Colors.BOLD + L10N.getString("info.uptime", event).replace("#uptime", Colors.NORMAL + new TimeParser(getMainAlias()).lts(ManagementFactory.getRuntimeMXBean().getUptime(), "%s:%s:%s:%s")), 
				Colors.BOLD + L10N.getString("info.buildDate", event).replace("#time", Colors.NORMAL + new Date(getBuildDate())),
				Colors.BOLD + L10N.getString("info.author", event).replace("#author", Colors.NORMAL + "bl4ckscor3"));
	}

	private long getBuildDate()
	{
		try
		{
			return ((JarURLConnection)ClassLoader.getSystemResource(Core.class.getName().replace('.', '/') + ".class").openConnection()).getJarFile().getEntry("META-INF/MANIFEST.MF").getTime();
		}
		catch(Exception e)
		{
			return 0L;
		}
	}
	
	@Override
	public String[] getAliases()
	{
		return new String[]{"info", "about"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-info";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{"-info || " + L10N.getString("info.explanation", event)};
	}
}
