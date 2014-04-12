package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

public class Twitch
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		String[] args = Utilities.toArgs(event.getMessage());
		
		if(args.length == 2)
			Utilities.respond(event, "http://www.twitch.tv/" + args[1], false);
		else
			Utilities.respond(event, "please provide a channel name for me. Example: -tv ohaiichun", true);
	}
}
