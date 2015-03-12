package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Twitch implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event)
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
		return "-tv <" + L10N.strings.getString("tv.help.channel") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-tv <" + L10N.strings.getString("tv.help.channel") + "> || " + L10N.strings.getString("tv.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
