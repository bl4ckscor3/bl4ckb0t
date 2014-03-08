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

		if(Stuffz.getMessage(event).startsWith("-say *say"))
		{
			Stuffz.chanMsg(event, "Stop trying to abuse us, " + Stuffz.getNick(event) + "! We aren't spambots, are we, Maunz?");
		}
		else
		{
			Stuffz.chanMsg(event, msg);
		}
	}
}