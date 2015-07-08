package bl4ckscor3.bot.bl4ckb0t.core;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

public class Bot extends PircBotX
{
	public Bot(Configuration<? extends PircBotX> configuration)
	{
		super(configuration);
	}
	
	public void sendCustomMessage(String target, String msg)
	{
		sendIRC().message(target, msg);
		Logging.info("Sent message \"" + msg + "\" to " + target + ".");
	}
	
	public void kick(String channel, String target, String reason)
	{
		sendRaw().rawLine("KICK " + channel + " " + target + " :" + reason);
		Logging.warn("Kicked " + target + " from " + channel + " for \"" + reason + "\".");
	}
}
