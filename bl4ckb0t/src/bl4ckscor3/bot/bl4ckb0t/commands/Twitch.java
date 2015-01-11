package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Twitch implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		String[] args = Utilities.toArgs(event.getMessage());
		
		if(args.length == 2)
			Utilities.respond(event, "http://www.twitch.tv/" + args[1], false);
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}
	
	@Override
	public String getAlias()
	{
		return "tv";
	}

	@Override
	public String getSyntax()
	{
		return "-tv <channel>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-tv <channel> || Gives you the link to the specified Twitch channel."};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
