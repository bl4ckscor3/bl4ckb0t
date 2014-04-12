package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.core.Listener;
import bl4ckb0tX.util.Utilities;

@SuppressWarnings("rawtypes")
public class Switch
{
	public static void off(MessageEvent event)
	{	
		if(Utilities.validUser(event))
		{
			if(Listener.enabled)
			{
				Listener.enabled = false;
				Utilities.chanMsg(event, "Successfully disabled :(");
			}
			else
				Utilities.chanMsg(event, "I am already disabled :(");
		}
		else
			Utilities.sorry(event);
	}

	public static void on(MessageEvent event)
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
}
