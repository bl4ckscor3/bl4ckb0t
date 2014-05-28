package bl4ckscor3.bot.bl4ckb0tX.core;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

public class Core
{	
	public static PircBotX bot;

	public static void main(String args[])
	{
		Configuration config = new Configuration.Builder()	
		.setName("bl4ckb0t")
		.setVersion("1.0")
		.setServerHostname("chaos.esper.net")
		.setServerPort(6667)
		.setLogin("bl4ckb0t")		
		.addAutoJoinChannel("#bl4ckscor3")
		.setNickservPassword("xxx")
		.setAutoNickChange(true)
		.setCapEnabled(true)
		.addListener(new Listener())
		.setMessageDelay(500)
		.buildConfiguration();

		bot = new PircBotX(config);

		if(!bot.isConnected())
		{
			while(true)//only way of auto reconnect as of now - bugs out when using "-stop no"
			{
				try
				{
					if(!Listener.stopped)
						bot.startBot();
				}
				catch(Exception e){}
			}
		}
	}

	public static void main2()
	{
		Configuration config = new Configuration.Builder()	
		.setName("bl4ckb0t")
		.setVersion("1.0")
		.setServerHostname("chaos.esper.net")
		.setServerPort(6667)
		.setLogin("bl4ckb0t")
		.addAutoJoinChannel("#bl4ckscor3")
		.setNickservPassword("xxx")
		.setAutoNickChange(true)
		.setCapEnabled(true)
		.addListener(new Listener())
		.setMessageDelay(500)
		.buildConfiguration();

		bot = new PircBotX(config);
		
		if(!bot.isConnected())
		{
			while(true)//only way of auto reconnect as of now - bugs out when using "-stop no"
			{
				try
				{
					if(!Listener.stopped)
						bot.startBot();
				}
				catch(Exception e){}
			}
		}
	}
}
