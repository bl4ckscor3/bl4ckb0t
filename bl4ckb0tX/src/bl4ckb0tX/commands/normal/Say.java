package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Say
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		String seperate = Stuffz.getMessage(event).substring(4);
		String msg = seperate.substring(1);
		Stuffz.chanMsg(event, msg);
	}
}
