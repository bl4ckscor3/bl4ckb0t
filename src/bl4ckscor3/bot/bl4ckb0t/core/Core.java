package bl4ckscor3.bot.bl4ckb0t.core;

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
	public static boolean wasStartedAsWIP;

	public static void main(String args[]) throws IOException, IrcException
	{
		Logging.setup("bl4ckb0t");
		//turning off logging (ugh)
		Logger.getLogger("").setLevel(Level.OFF);
		Logging.info("Disabled loggers...");
		wasStartedAsWIP = args.length >= 1 && args[0].equals("-wip");
		Logging.info("Bot was started as WIP version...");
		createBot(wasStartedAsWIP);
	}

	public static void createBot(boolean wip) throws IOException, IrcException
	{
		try
		{
			Configuration<Bot> config;

			Lists.clearAll();
			Startup.callMethods();
			config = new Configuration.Builder<Bot>()
					.setVersion("4.4.1" + (wip ? "_WIP" : ""))
					.setName("bl4ckb0t")
					.setLogin("bl4ckb0t")
					.setServer("irc.esper.net", 6697)
					.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates())
					.setNickservPassword(Passwords.NICKSERV.getPassword())
					.setAutoNickChange(true)
					.setAutoReconnect(true)
					.setMessageDelay(1000)
					.addListener(new MiscListener())
					.addListener(new CMDListener())
					.addListener(new Logging())
					.buildConfiguration();
			Logging.info("Created bot config...");
			bot = new Bot(config);
			Logging.info("Starting bot...");
			bot.startBot();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
