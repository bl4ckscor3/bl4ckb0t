package bl4ckscor3.bot.bl4ckb0t.core;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

public class Bot extends PircBotX
{
	public Bot(Configuration<? extends PircBotX> configuration)
	{
		super(configuration);
	}
	
	@Override
	public void setNick(String nick)
	{
		super.setNick(nick);
	}
}
