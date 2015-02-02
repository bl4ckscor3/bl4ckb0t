package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

public class Core
{	
	public static PircBotX bot;
	public static String password = "4k1N08L4Ck";
	
	public static void main(String args[]) throws IOException, IrcException
	{
		createBot();
	}

	public static void createBot() throws IOException, IrcException
	{
		//turning off logging (ugh)
		Logger.getLogger("").setLevel(Level.OFF);

		Configuration config = new Configuration.Builder()	
		.setName("bl4ckb0t")
		.setVersion("1.0")
		.setServerHostname("irc.esper.net")
		.setServerPort(6667)
		.setNickservPassword("7h1515H0n357lY7h3L45771M31W1llr3m4K37h34Cc0un7")
		.setLogin("bl4ckb0t")
		.setAutoNickChange(true)
		.addListener(new Listener()) 
		.setMessageDelay(1000)
		.buildConfiguration();

		bot = new PircBotX(config);
		bot.startBot();
	}
}
