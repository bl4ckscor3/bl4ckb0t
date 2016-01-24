package bl4ckscor3.bot.bl4ckb0t;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.Configuration;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;

public class Core
{
	public static Bot bot;
	private static boolean wasStartedAsWIP;
	private static final String botName = "bl4ckb0t";
	private static final String version = "5.0.1";
	private static ConfigurationFile customConfig;
	
	public static void main(String args[]) throws IOException, IrcException
	{
		Logging.setup(botName);
		Logger.getLogger("").setLevel(Level.OFF);
		Logging.info("Disabled loggers...");
		wasStartedAsWIP = args.length >= 1 && args[0].equals("-wip");
		
		if(wasStartedAsWIP)
			Logging.info("Bot was started as WIP version...");
		
		customConfig = new ConfigurationFile();
		createBot(wasStartedAsWIP);
	}

	public static void createBot(boolean wip) throws IOException, IrcException
	{
		try
		{
			Configuration<Bot> config;

			config = new Configuration.Builder<Bot>()
					.setVersion(version + (wip ? "_WIP" : ""))
					.setName(botName)
					.setLogin(botName)
					.setServer("irc.esper.net", 6697)
					.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates())
					.setNickservPassword(Passwords.NICKSERV.getPassword())
					.setAutoNickChange(true)
					.setAutoReconnect(true)
					.setMessageDelay(0)
					.addListener(new MiscListener())
					.addListener(new CMDListener())
					.addListener(new Logging())
					.buildConfiguration();
			Logging.info("Created PircBotX config...");
			bot = new Bot(config, wip, customConfig);
			Lists.clearAll();
			Startup.callMethods();
			Logging.info("Completed last setup steps...");
			Logging.info("Starting bot...");
			bot.startBot();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
