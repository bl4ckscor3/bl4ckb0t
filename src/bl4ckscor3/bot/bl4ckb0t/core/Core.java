package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import bl4ckscor3.bot.bl4ckb0t.util.Passwords;

public class Core
{	
	public static PircBotX bot;
	
	public static void main(String args[]) throws IOException, IrcException
	{
		createBot();
	}

	public static void createBot() throws IOException, IrcException
	{
		//turning off logging (ugh)
		Logger.getLogger("").setLevel(Level.OFF);

		Configuration config = new Configuration.Builder()
		.setVersion("3.5.7")
		.setName("bl4ckb0t")
		.setVersion("1.0")
		.setServerHostname("irc.esper.net")
		.setServerPort(6667)
		.setNickservPassword(Passwords.nickserv)
		.setLogin("bl4ckb0t")
		.setAutoNickChange(true)
		.addListener(new Listener()) 
		.setMessageDelay(1000)
		.buildConfiguration();

		bot = new PircBotX(config);
		bot.startBot();
	}
}
