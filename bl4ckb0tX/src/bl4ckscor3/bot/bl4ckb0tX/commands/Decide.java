package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Decide implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		if(event.getMessage().endsWith("?"))
		{
			Random r = new Random();
			int decision = r.nextInt(101);

			if(decision >= 0 && decision <= 49)
				Utilities.respond(event, "no", true);
			else if(decision >= 50 && decision <= 100)
				Utilities.respond(event, "yes", true);
			else
				Utilities.chanMsg(event, "I failed badly: " + decision);
		}
		else
			Utilities.respond(event, "that's not a question! Maybe try using a questionmark? (Yes, I'm ocd about that :P)", true);
	}
	
	@Override
	public String getAlias()
	{
		return "decide";
	}
}
