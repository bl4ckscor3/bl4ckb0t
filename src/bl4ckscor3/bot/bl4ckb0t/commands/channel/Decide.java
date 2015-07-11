package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Decide implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException
	{
		if(Utilities.toArgs(event.getMessage()).length >= 2 && event.getMessage().endsWith("?"))
		{
			Random r = new Random();
			int decision = r.nextInt(101);

			if(decision >= 0 && decision <= 49)
				Utilities.chanMsg(event, L10N.getString("decide.no"));
			else if(decision >= 50 && decision <= 100)
				Utilities.chanMsg(event, L10N.getString("decide.yes"));
			else
				Utilities.chanMsg(event, L10N.getString("decide.fail") + ": " + decision);
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias()
	{
		return "decide";
	}

	@Override
	public String getSyntax()
	{
		return "-decide <" + L10N.getString("decide.help.question") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-decide <" + L10N.getString("decide.help.question") + "> || " + L10N.getString("decide.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.getString("decide.notes");
	}
	
	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
