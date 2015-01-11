package bl4ckscor3.bot.bl4ckb0t.commands;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

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
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}
	
	@Override
	public String getAlias()
	{
		return "decide";
	}

	@Override
	public String getSyntax()
	{
		return "-decide <question>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-decide <question> || Decides for you. Please only enter yes/no answerable questions."};
	}

	@Override
	public String getNotes()
	{
		return "Don't forget the questionmark!";
	}
}
