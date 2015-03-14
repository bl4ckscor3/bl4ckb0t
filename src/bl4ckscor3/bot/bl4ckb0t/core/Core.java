package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.Configuration;
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
		//turning off logging (ugh)
		Logger.getLogger("").setLevel(Level.OFF);

		Configuration<Bot> config = new Configuration.Builder<Bot>()
		.setVersion("3.7")
		.setName("bl4ckb0t")
		.setServerHostname("irc.esper.net")
		.setServerPort(6667)
		.setNickservPassword(Passwords.nickserv)
		.setLogin("bl4ckb0t")
		.setAutoNickChange(true)
		.addListener(new Listener()) 
		.setMessageDelay(1000)
		.buildConfiguration();

		bot = new Bot(config);
		bot.startBot();
	}
}
