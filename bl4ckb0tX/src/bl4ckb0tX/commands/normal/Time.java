package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Time
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
			String time = new java.util.Date().toString();
			Stuffz.chanMsg(event, "My time is " + time);
	}
}
