package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;
import bl4ckscor3.bot.bl4ckb0tX.core.Listener;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Enable implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws MalformedURLException, IOException
	{
		if(Utilities.isValidUser(event))
		{
			if(!Listener.enabled)
			{
				Listener.enabled = true;
				Utilities.chanMsg(event, "Successfully enabled >:D");
			
				for(String s : Utilities.getJoinedChannels())
				{
					if(!s.equalsIgnoreCase(event.getChannel().getName()))
						Core.bot.sendIRC().message(s, "FYI, I was enabled. Welcome me back :)");
				}
			}
			else
				Utilities.chanMsg(event, "I am already enabled >:D");
		}
		else
			Utilities.sorry(event);
	}
	
	@Override
	public String getAlias()
	{
		return "enable";
	}
	
	@Override
	public String getSyntax()
	{
		return "-enable";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-enable || Enables all the bot's features."};
	}

	@Override
	public String getNotes()
	{
		return "Only useable by OPs.";
	}
}
