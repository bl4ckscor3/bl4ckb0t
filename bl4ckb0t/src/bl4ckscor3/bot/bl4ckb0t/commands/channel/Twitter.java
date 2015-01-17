package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
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
		return "-tw <" + L10N.strings.getString("tw.help.profile") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-tw <" + L10N.strings.getString("tw.help.profile") + "> || " + L10N.strings.getString("tw.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
