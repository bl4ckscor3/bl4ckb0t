package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.core.Listener;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class SwitchOn implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		if(Utilities.validUser(event))
		{
			if(!Listener.enabled)
			{
				Listener.enabled = true;
				Utilities.chanMsg(event, "Successfully enabled >:D");
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
}
