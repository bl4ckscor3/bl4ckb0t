package bl4ckb0tX.core;

import org.pircbotx.hooks.events.MessageEvent;

public class Fun
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		if(!Listener.fun)
			Listener.fun = true;
		else
			Listener.fun = false;
	}
}
