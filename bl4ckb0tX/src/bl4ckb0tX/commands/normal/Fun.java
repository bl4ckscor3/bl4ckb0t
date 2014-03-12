package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.core.Listener;
import bl4ckb0tX.util.Stuffz;

public class Fun
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		if(!Listener.fun)
		{
			Listener.fun = true;
			Stuffz.chanMsg(event, "Activated the fun!");
		}
		else
		{
			Listener.fun = false;
			Stuffz.chanMsg(event, "Deactivated the fun >.>");
		}
	}
}
