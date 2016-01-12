package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class RandomNumber extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws IncorrectCommandExecutionException
	{
		String result = "";
		int[] numbs = new int[50];
		int n = Integer.parseInt(args[1]);

		if(args.length == 2)
		{
			if(!(n > 50))
			{
				Random r = new Random();

				for(int i = 0; i < n; i++)
				{
					numbs[i] = r.nextInt(10);
					result += numbs[i] + " ";
				}

				Utilities.pm(event.getUser().getNick(), result);
			}
			else
				Utilities.chanMsg(event, L10N.getString("ln.fail", event));
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}
	
	@Override
	public String[] getAliases()
	{
		return new String[]{"number", "randomnumber", "rn"};
	}
	
	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-number <" + L10N.getString("ln.help.number", event) + ">";
	}
	
	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-number <" + L10N.getString("ln.help.number", event) + "> || " + L10N.getString("letter.explanation", event)};
	}
	
	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("ln.notes", event);
	}
}
