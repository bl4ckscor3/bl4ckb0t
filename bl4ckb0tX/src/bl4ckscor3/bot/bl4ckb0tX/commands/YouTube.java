package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class YouTube implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws MalformedURLException, IOException
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
			Utilities.respond(event, "please provide a channel name for me and nothing else. Example: -yt antvenom", true);
	}
	
	@Override
	public String getAlias()
	{
		return "yt";
	}
}

