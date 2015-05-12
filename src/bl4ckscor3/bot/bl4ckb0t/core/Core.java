package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.Configuration;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;

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
		config = new Configuration.Builder<Bot>()
				.setServer("irc.esper.net", 6697)
				.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates())
				.setName("bl4ckb0t")
				.setLogin("bl4ckb0t")
				.setVersion("3.11_WIP")
				.setNickservPassword(Passwords.getPassword("nickserv"))
				.setAutoNickChange(true)
				.setMessageDelay(1000)
				.addListener(new Listener())
				.buildConfiguration();
		bot = new Bot(config);
		bot.startBot();
	}
}
