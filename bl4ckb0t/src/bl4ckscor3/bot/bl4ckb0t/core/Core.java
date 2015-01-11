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
	public static String password = "akin0bl4ck";
	
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
		.setNickservPassword("whyd01k33pc0mm1771n6myp455w0rd")
		.setLogin("bl4ckb0t")
		.setAutoNickChange(true)
		.addListener(new Listener())
		.setMessageDelay(1000)
		.buildConfiguration();

		bot = new PircBotX(config);
		bot.startBot();
	}
}
