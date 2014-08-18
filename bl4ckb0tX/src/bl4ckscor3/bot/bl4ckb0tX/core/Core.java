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
	private static String esperNsPass = "xxx";
	public static String lastJoinedNetwork = "";
	public static String channel = "";
	public static String password = "";
	
	public static void main(String args[]) throws IOException, IrcException
	{
		createBot("irc.esper.net", esperNsPass, null); //not needed because esper
	}

	public static void createNewBot() throws IOException, IrcException
	{
		createBot("irc.esper.net", esperNsPass, null); //not needed because esper
	}
	
	public static void createBot(String server, String nsPass, String firstChanToJoin) throws IOException, IrcException
	{
		lastJoinedNetwork = server;
		channel = firstChanToJoin;
		password = nsPass;
		
		Configuration config = new Configuration.Builder()	
		.setName("bl4ckb0t")
		.setVersion("1.0")
		.setServerHostname(server)
		.setServerPort(6667)
		.setLogin("bl4ckb0t")
		.setAutoNickChange(true)
		.setCapEnabled(true)
		.addListener(new Listener())
		.setMessageDelay(500)
		.buildConfiguration();
		
		bot = new PircBotX(config);
		bot.startBot();
	}
}
