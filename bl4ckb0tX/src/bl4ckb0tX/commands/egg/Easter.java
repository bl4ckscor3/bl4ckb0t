package bl4ckb0tX.commands.egg;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.core.Core;
import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Easter
{
	public static void init(MessageEvent event)
	{
		love(event);
		fourtyTwo(event);
	}

	private static void love(MessageEvent event)
	{
		if(Stuffz.getMessage(event).contains(Core.bot.getNick()) && Stuffz.getMessage(event).contains("love") && !((Stuffz.getMessage(event).contains("don't") || Stuffz.getMessage(event).contains("does not")) || Stuffz.getMessage(event).contains("not") || Stuffz.getMessage(event).contains("hate")))
		{
			if(Stuffz.validUser(event))
				Stuffz.respond(event, "I love you too :)", true);
			else
				Stuffz.respond(event, "sorry, I only love bl4ckscor3.", true);
		}
	}

	private static void fourtyTwo(MessageEvent event)
	{
		if(Stuffz.getMessage(event).contains("answer of life, universe and everything") || Stuffz.getMessage(event).contains("answer to everything") || Stuffz.getMessage(event).contains("answer of everything"))
		{
			Stuffz.respond(event, "42", false);
		}
		if(Stuffz.getMessage(event).equals("42"))
		{
			Stuffz.respond(event, "this is the answer of life, universe and everything!", true);
		}
	}
}
