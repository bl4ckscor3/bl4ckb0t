package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Test
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		if(event.getUser().getNick().equals("bl4ckscor3") || event.getUser().getNick().equals("bl4ckgon3") || event.getUser().getNick().equals("bl4ckweb") || event.getUser().getNick().equals("bl4ckdro1d"))
		{
			Stuffz.chanMsg(event, "Nothing to see here");
		}
		else
		{
			Stuffz.sorry(event);
		}
	}
}
