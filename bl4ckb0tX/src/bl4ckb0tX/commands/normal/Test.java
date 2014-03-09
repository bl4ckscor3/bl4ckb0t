package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Test
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		if(Stuffz.validUser(event))
		{
			Stuffz.chanMsg(event, "Nothing to see here");
		}
		else
		{
			Stuffz.sorry(event);
		}
	}
}
