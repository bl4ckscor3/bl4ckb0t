package bl4ckscor3.bot.bl4ckb0t.modules;

import java.lang.management.ManagementFactory;
import java.net.JarURLConnection;
import java.net.URLClassLoader;
import java.util.Date;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.util.TimeParser;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Info extends Module
{
	public Info(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command());
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{Core.l10n.translate("info.explanation", channel)};
	}
	
	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String channel = event.getChannel().getName();
			
			Utilities.sendStarMsg(event.getChannel().getName(),
					Core.l10n.translate("info.version", channel).replace("#version", Core.bot.getConfiguration().getVersion()),
					Core.l10n.translate("info.uptime", channel).replace("#uptime", TimeParser.lts(ManagementFactory.getRuntimeMXBean().getUptime(), "%s:%s:%s:%s")), 
					Core.l10n.translate("info.buildDate", channel).replace("#date", "" + new Date(getBuildDate())),
					Core.l10n.translate("info.javaVersion", channel).replace("#version", System.getProperty("java.version")),
					Core.l10n.translate("info.author", channel));
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"info", "about"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return Core.l10n.translate("info.syntax", channel);
		}
		
		/**
		 * Gets the build date of the jar
		 * @return The build date of the jar as a long, 0 if no jar file has been found
		 */
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
	}
}
