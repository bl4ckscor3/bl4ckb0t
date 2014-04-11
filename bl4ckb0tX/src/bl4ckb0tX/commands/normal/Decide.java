package bl4ckb0tX.commands.normal;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Decide
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		if(Stuffz.getMessage(event).endsWith("?"))
		{
			Random r = new Random();
			int decision = r.nextInt(101);

			if(decision >= 0 && decision <= 49)
				Stuffz.respond(event, "No", true);
			else if(decision >= 50 && decision <= 100)
				Stuffz.respond(event, "Yes", true);
			else
				Stuffz.chanMsg(event, "I failed badly: " + decision);
		}
		else
			Stuffz.respond(event, "that's not a question! Maybe try using a questionmark?", true);
	}
}
