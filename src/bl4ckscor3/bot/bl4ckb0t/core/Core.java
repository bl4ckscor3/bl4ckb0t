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

	public static void main(String args[]) throws IOException, IrcException
	{
		//turning off logging (ugh)
		Logger.getLogger("").setLevel(Level.OFF);
		Logging.info("Disabled loggers...");
		createBot();
	}

	public static void createBot() throws IOException, IrcException
	{
		try
		{
			Configuration<Bot> config;

			Lists.clearAll();
			Startup.callMethods();
			config = new Configuration.Builder<Bot>()
					.setVersion("3.16_WIP")
					.setName("bl4ckb0t")
					.setLogin("bl4ckb0t")
					.setServer("irc.esper.net", 6697)
					.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates())
					.setNickservPassword(Passwords.NICKSERV.getPassword())
					.setAutoNickChange(true)
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
