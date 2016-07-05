package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.lang.management.ManagementFactory;
import java.net.JarURLConnection;
import java.util.Date;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.TimeParser;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Info extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		String channel = event.getChannel().getName();
		
		Utilities.sendStarMsg(channel,
				L10N.getString("info.version", channel).replace("#version", Colors.NORMAL + Core.bot.getConfiguration().getVersion()),
				L10N.getString("info.uptime", channel).replace("#uptime", Colors.NORMAL + new TimeParser(getMainAlias()).lts(ManagementFactory.getRuntimeMXBean().getUptime(), "%s:%s:%s:%s")), 
				L10N.getString("info.buildDate", channel).replace("#time", Colors.NORMAL + new Date(getBuildDate())),
				L10N.getString("info.javaVersion", channel).replace("#version", Colors.NORMAL + System.getProperty("java.version")),
				L10N.getString("info.author", channel).replace("#author", Colors.NORMAL + "bl4ckscor3"));
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
	public String getSyntax(String channel)
	{
		return "-info";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-info || " + L10N.getString("info.explanation", channel)};
	}
}
