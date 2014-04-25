package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Twitter implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
			Utilities.respond(event, "http://www.twitter.com/" + args[1], false);
		else
			Utilities.respond(event, "please provide a profile name for me. Example: -tw sethbling", true);
	}
	
	@Override
	public String getAlias()
	{
		return "tw";
	}
}
