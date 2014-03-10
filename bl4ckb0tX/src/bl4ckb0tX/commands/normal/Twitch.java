package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Twitch
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		String[] seperate = Stuffz.getMessage(event).split(" ");
		
		Stuffz.respond(event, "http://www.twitch.tv/" + seperate[1], false);
	}
}
