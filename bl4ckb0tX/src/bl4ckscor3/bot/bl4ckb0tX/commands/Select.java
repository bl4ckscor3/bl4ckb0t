package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Select implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) 
	{
		Random r = new Random();
		String[] args = event.getMessage().substring(8).split(",");
		int i = r.nextInt(args.length);
		
		Utilities.respond(event, args[i], false);
	}

	@Override
	public String getAlias() 
	{
		return "select";
	}
}
