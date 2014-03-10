package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class GirlBalls
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		if(Stuffz.validUser(event))
			Stuffz.chanMsg(event, "http://www.youtube.com/emmablackery");
		else
			Stuffz.respond(event, "only bl4ckscor3 can get girlballs, sorry :(", true);
	}
}
