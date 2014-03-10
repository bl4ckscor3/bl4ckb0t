package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class YouTube
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		String[] seperate = Stuffz.getMessage(event).split(" ");
		
		Stuffz.respond(event, "http://www.youtube.com/" + seperate[1], false);
	}
}
