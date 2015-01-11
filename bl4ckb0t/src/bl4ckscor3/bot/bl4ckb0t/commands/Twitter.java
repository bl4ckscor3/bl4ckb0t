package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Twitter implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
			Utilities.respond(event, "http://www.twitter.com/" + args[1], false);
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}
	
	@Override
	public String getAlias()
	{
		return "tw";
	}
	
	@Override
	public String getSyntax()
	{
		return "-tw <profile>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-tw <profile> || Gives you the link to the specified Twitter profile."};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
