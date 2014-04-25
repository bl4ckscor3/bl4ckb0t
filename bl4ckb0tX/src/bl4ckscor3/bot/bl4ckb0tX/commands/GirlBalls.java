package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class GirlBalls implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		if(Utilities.validUser(event))
			Utilities.chanMsg(event, "http://www.youtube.com/emmablackery");
		else
			Utilities.respond(event, "only bl4ckscor3 can get girlballs, sorry :(", true);
	}
	
	@Override
	public String getAlias()
	{
		return "girlballs";
	}
}
