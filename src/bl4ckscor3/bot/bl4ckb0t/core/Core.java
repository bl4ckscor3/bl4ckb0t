package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.Configuration;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;

import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;

public class Core
{
	public static Bot bot;

	public static void main(String args[]) throws IOException, IrcException
	{
		createBot();
	}

	public static void createBot() throws IOException, IrcException
	{
		Configuration<Bot> config;

		Logger.getLogger("").setLevel(Level.OFF); //turning off logging (ugh)
		Passwords.setPasswords();
		Lists.clearAll();
		Startup.setAllowedUsers();
		Startup.setValidUsers();
		Startup.setIgnoredUsers();
		config = new Configuration.Builder<Bot>()
				.setVersion("3.13")
				.setName("bl4ckb0t")
				.setLogin("bl4ckb0t")
				.setServer("irc.esper.net", 6697)
				.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates())
				.setNickservPassword(Passwords.getPassword("nickserv"))
				.setAutoNickChange(true)
				.setMessageDelay(1000)
				.addListener(new MiscListener())
				.addListener(new CMDListener())
				.buildConfiguration();
		bot = new Bot(config);
		bot.startBot();
	}
}
