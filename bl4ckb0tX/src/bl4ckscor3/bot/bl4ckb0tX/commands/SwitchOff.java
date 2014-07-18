package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;
import bl4ckscor3.bot.bl4ckb0tX.core.Listener;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class SwitchOff implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{	
		if(Utilities.validUser(event))
		{
			if(Listener.enabled)
			{
				Listener.enabled = false;
				Utilities.chanMsg(event, "Successfully disabled :(");
				
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
}
