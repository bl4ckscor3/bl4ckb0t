package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class YouTube
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		String channel = Stuffz.seperate(event);
		
		if(channel.equalsIgnoreCase("emmablackery"))
			Stuffz.respond(event, "only bl4ckscor3 can get girlballs, sorry :(", true);
		else
			Stuffz.respond(event, "http://www.youtube.com/" + channel, false);
	}
}
