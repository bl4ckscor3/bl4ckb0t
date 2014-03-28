package bl4ckb0tX.commands.normal;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Decide
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		Random r = new Random();
		int decision = r.nextInt(2);
		
		switch(decision)
		{
			case 0:
				Stuffz.respond(event, "No", true);
				break;
			case 1:
				Stuffz.respond(event, "Yes", true);
				break;
			default:
				Stuffz.chanMsg(event, "I failed badly: " + decision);
		}
	}
}
