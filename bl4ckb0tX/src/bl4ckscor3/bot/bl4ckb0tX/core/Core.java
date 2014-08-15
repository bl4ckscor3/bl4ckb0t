package bl4ckscor3.bot.bl4ckb0tX.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.Configuration;
import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

public class Core
{	
	public static PircBotX bot;

	public static void main(String args[]) throws IOException, IrcException
	{
		createBot();
	}

	public static void createBot() throws IOException, IrcException
	{
		Configuration config = new Configuration.Builder()	
		.setName("bl4ckb0t")
		.setVersion("1.0")
		.setServerHostname("irc.esper.net")
		.setServerPort(6667)
		.setLogin("bl4ckb0t")
		.setNickservPassword("xxx")
		.setAutoNickChange(true)
		.setCapEnabled(true)
		.addListener(new Listener())
		.setMessageDelay(500)
		.buildConfiguration();
		
		bot = new PircBotX(config);
		bot.startBot();
	}
}
