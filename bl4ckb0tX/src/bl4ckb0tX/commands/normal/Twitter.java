package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Twitter
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		String user = Stuffz.seperate(event);
		String userUrl = "http://www.twitter.com/" + user;
		
		Stuffz.respond(event, userUrl, false);
	}
}
