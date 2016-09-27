package bl4ckscor3.bot.bl4ckb0t;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.Configuration;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;

public class Core
{
	public static Bot bot;
	private static boolean wasStartedAsWIP;
	private static final String botName = "bl4ckb0t";
	private static final String version = "6.0";
	public static Modules modules;
	
	public static void main(String args[]) throws IOException, IrcException
	{
		try
		{
			Logging.setup(botName);
			Logger.getLogger("").setLevel(Level.OFF);
			Logging.info("Disabled loggers");
			wasStartedAsWIP = args.length >= 1 && args[0].equals("-wip");

			if(wasStartedAsWIP)
				Logging.info("Bot was started as WIP version");

			createBot(wasStartedAsWIP);
		}
		catch(Exception e)
		{
			Logging.stackTrace(e);
		}
	}

	/**
	 * Creates the bot and starts it
	 * @param wip Wether or not the bot should be started as a development version
	 */
	public static void createBot(boolean wip) throws Exception
	{
		Configuration.Builder builder = new Configuration.Builder()
				.setVersion(version + (wip ? "_WIP" : ""))
				.setName(botName)
				.setLogin(botName)
				.addServer("irc.esper.net", 6697)
				.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates())
				.setNickservPassword(Passwords.NICKSERV.getPassword())
				.setAutoNickChange(true)
				.setAutoReconnect(true)
				.setMessageDelay(0)
				.addAutoJoinChannel("#bl4ckb0tTest")
				.addListener(new Logging());
		Logging.info("Created PircBotX config");
		Logging.info("Starting to load modules");
		modules = new Modules(builder);
		builder = modules.init();
		Logging.info("All modules loaded.");
		bot = new Bot(builder.buildConfiguration(), wip, "-");
		Logging.info("Completed last setup steps");
		Logging.info("Starting bot");
		bot.startBot();
	}
}
