package bl4ckb0tX.commands.normal;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

public class YouTube
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase("emmablackery"))
				Utilities.respond(event, "only bl4ckscor3 can get girlballs, sorry :(", true);
			else
				Utilities.respond(event, "http://www.youtube.com/" + args[1], false);
		}
		else
			Utilities.respond(event, "please provide a channel name for me. Example: -yt antvenom", true);
	}
}

