package bl4ckscor3.bot.bl4ckb0t.commands;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.core.Listener;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Disable implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws MalformedURLException, IOException
	{	
		if(Utilities.isValidUser(event))
		{
			if(Listener.enabled)
			{
				Listener.enabled = false;
				Utilities.chanMsg(event, "Successfully disabled :(");
				System.out.println(Utilities.getJoinedChannels().length);
				if(Utilities.getJoinedChannels().length == 1)
					return;
				
				for(String s : Utilities.getJoinedChannels())
				{
					if(!s.equalsIgnoreCase(event.getChannel().getName()))
						Core.bot.sendIRC().message(s, "FYI, I was disabled. Cya in a bit :)");
				}
			}
			else
				Utilities.chanMsg(event, "I am already disabled :(");
		}
		else
			Utilities.sorry(event);
	}
	
	@Override
	public String getAlias()
	{
		return "disable";
	}

	@Override
	public String getSyntax()
	{
		return "-disable";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-disable || Disables all the bot's features."};
	}

	@Override
	public String getNotes()
	{
		return "Only useable by OPs.";
	}
}
