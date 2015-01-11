package bl4ckscor3.bot.bl4ckb0t.commands;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Select implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) 
	{
		String[] args = event.getMessage().substring(8).split(",");
		
		if(args.length == 2)
		{
			Random r = new Random();
			int i = r.nextInt(args.length);

			Utilities.respond(event, args[i], false);
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}

	@Override
	public String getAlias() 
	{
		return "select";
	}

	@Override
	public String getSyntax()
	{
		return "-select <" + L10N.strings.getString("select.help.options") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-select <" + L10N.strings.getString("select.help.options") + "> || " + L10N.strings.getString("select.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("select.notes");
	}
}
