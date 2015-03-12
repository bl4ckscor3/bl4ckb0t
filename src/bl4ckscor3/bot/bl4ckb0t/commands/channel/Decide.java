package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Decide implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event)
	{
		if(event.getMessage().endsWith("?"))
		{
			Random r = new Random();
			int decision = r.nextInt(101);

			if(decision >= 0 && decision <= 49)
				Utilities.respond(event, L10N.strings.getString("decide.no"), true);
			else if(decision >= 50 && decision <= 100)
				Utilities.respond(event, L10N.strings.getString("decide.yes"), true);
			else
				Utilities.chanMsg(event, L10N.strings.getString("decide.fail") + ": " + decision);
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
		return "-decide <" + L10N.strings.getString("decide.help.question") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-decide <" + L10N.strings.getString("decide.help.question") + "> || " + L10N.strings.getString("decide.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("decide.notes");
	}
}
