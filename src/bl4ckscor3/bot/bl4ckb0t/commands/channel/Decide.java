package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Decide extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length >= 2 && event.getMessage().endsWith("?"))
		{
			Random r = new Random();
			int decision = r.nextInt(101);

			if(decision >= 0 && decision <= 49)
				Utilities.chanMsg(event, L10N.getString("decide.no", event));
			else if(decision >= 50 && decision <= 100)
				Utilities.chanMsg(event, L10N.getString("decide.yes", event));
			else
				Utilities.chanMsg(event, L10N.getString("decide.fail", event) + ": " + decision);
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"decide"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-decide <" + L10N.getString("decide.help.question", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-decide <" + L10N.getString("decide.help.question", event) + "> || " + L10N.getString("decide.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("decide.notes", event);
	}
}
