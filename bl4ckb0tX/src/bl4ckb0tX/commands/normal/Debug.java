package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.core.Listener;
import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Debug
{
	public static void exe(MessageEvent event)
	{
		if(Stuffz.validUser(event))
		{
			if(!Listener.debug)
			{
				Listener.debug = true;
				Stuffz.chanMsg(event, "Debug Mode activated.");
			}
			else
			{
				Listener.debug = false;
				Stuffz.chanMsg(event, "Debug Mode deactivated.");
			}
		}
	}
}
