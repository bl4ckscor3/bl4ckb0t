package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.core.Listener;
import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Switch
{
	public static void off(MessageEvent event)
	{	
		if(Stuffz.validUser(event))
		{
			if(Listener.enabled)
			{
				Listener.enabled = false;
				Stuffz.chanMsg(event, "Successfully disabled :(");
			}
			else
			{
				Stuffz.chanMsg(event, "I am already disabled :(");
			}
		}
		else
		{
			Stuffz.sorry(event);
		}
	}

	public static void on(MessageEvent event)
	{
		if(Stuffz.getNick(event).equalsIgnoreCase("bl4ckscor3") || Stuffz.getNick(event).equalsIgnoreCase("bl4ckgon3") || Stuffz.getNick(event).equalsIgnoreCase("bl4ckweb") || Stuffz.getNick(event).equalsIgnoreCase("bl4ckdro1d"))
		{
			if(!Listener.enabled)
			{
				Listener.enabled = true;
				Stuffz.chanMsg(event, "Successfully enabled >:D");
			}
			else
			{
				Stuffz.chanMsg(event, "I am already enabled >:D");
			}
		}
		else
		{
			Stuffz.sorry(event);
		}
	}
}
