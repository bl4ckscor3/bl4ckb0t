package bl4ckscor3.bot.bl4ckb0t.core;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

public class Bot extends PircBotX
{
	public Bot(Configuration<? extends PircBotX> configuration)
	{
		super(configuration);
	}
	
	public void sendCustomMessage(String target, String msg)
	{
		sendIRC().message(target, msg);
	}
}
