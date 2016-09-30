package bl4ckscor3.bot.bl4ckb0t.modules;

import java.lang.management.ManagementFactory;
import java.net.JarURLConnection;
import java.util.Date;

import org.pircbotx.Colors;
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
	public void setup()
	{
		getBuilder().registerChannelCommand(this, new Command());
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"Info usage"}; //TODO: L10N
	}
	
	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			//TODO: L10N
			Utilities.sendStarMsg(event.getChannel().getName(),
					"Version: " + Colors.NORMAL + Core.bot.getConfiguration().getVersion(),
					"Uptime: " + Colors.NORMAL + TimeParser.lts(ManagementFactory.getRuntimeMXBean().getUptime(), "%s:%s:%s:%s"), 
					"Bulid date: " + Colors.NORMAL + new Date(getBuildDate()),
					"Java version: " + Colors.NORMAL + System.getProperty("java.version"),
					"Author: " + Colors.NORMAL + "bl4ckscor3");
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"info", "about"};
		}

		@Override
		public String getSyntax()
		{
			return Core.bot.getCmdPrefix() + "info";
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
