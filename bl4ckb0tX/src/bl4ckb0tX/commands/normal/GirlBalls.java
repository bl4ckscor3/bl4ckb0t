package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

public class GirlBalls
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		if(Utilities.validUser(event))
			Utilities.chanMsg(event, "http://www.youtube.com/emmablackery");
		else
			Utilities.respond(event, "only bl4ckscor3 can get girlballs, sorry :(", true);
	}
}
