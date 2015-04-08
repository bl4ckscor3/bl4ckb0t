package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Select implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException 
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] options = event.getMessage().substring(8).split(",");
		
		if(args.length == 2)
		{
			Random r = new Random();
			int i = r.nextInt(options.length);

			Utilities.respond(event, options[i], false);
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias() 
	{
		return "select";
	}

	@Override
	public String getSyntax()
	{
		return "-select <" + L10N.getString("select.help.options") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-select <" + L10N.getString("select.help.options") + "> || " + L10N.getString("select.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.getString("select.notes");
	}
}
