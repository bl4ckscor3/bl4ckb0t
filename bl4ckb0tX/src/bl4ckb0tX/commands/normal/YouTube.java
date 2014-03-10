package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class YouTube
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		String[] seperate = Stuffz.getMessage(event).split(" ");
		
		if(seperate[1].equalsIgnoreCase("emmablackery"))
			Stuffz.respond(event, "only bl4ckscor3 can get girlballs, sorry :(", true);
		else
			Stuffz.respond(event, "http://www.youtube.com/" + seperate[1], false);
	}
}
