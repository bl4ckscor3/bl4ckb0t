package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Select extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException 
	{
		String[] args = Utilities.toArgs(event.getMessage());
		
		if(args.length != 1)
		{
			Random r = new Random();
			String[] options = event.getMessage().substring(8).split(",");
			int i = r.nextInt(options.length);

			Utilities.chanMsg(event, options[i]);
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"select"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-select <" + L10N.getString("select.help.options", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-select <" + L10N.getString("select.help.options", event) + "> || " + L10N.getString("select.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("select.notes", event);
	}
}
