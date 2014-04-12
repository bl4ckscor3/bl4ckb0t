package bl4ckb0tX.commands.normal;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

public class Decide
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		if(event.getMessage().endsWith("?"))
		{
			Random r = new Random();
			int decision = r.nextInt(101);

			if(decision >= 0 && decision <= 49)
				Utilities.respond(event, "No", true);
			else if(decision >= 50 && decision <= 100)
				Utilities.respond(event, "Yes", true);
			else
				Utilities.chanMsg(event, "I failed badly: " + decision);
		}
		else
			Utilities.respond(event, "that's not a question! Maybe try using a questionmark?", true);
	}
}
