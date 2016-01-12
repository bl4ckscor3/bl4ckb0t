package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class RandomLetter extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws IncorrectCommandExecutionException
	{
		String result = "";
		String[] chars = new String[50];

		if(args.length == 2)
		{
			int n = Integer.parseInt(args[1]);

			if(!(n > 50))
			{
				for(int i = 0; i < n; i++)
				{
					Random rand = new Random();
					int r = rand.nextInt(26);

					switch(r)
					{
						case 0: chars[i] = "A"; break; 
						case 1: chars[i] = "B"; break;
						case 2: chars[i] = "C"; break;
						case 3: chars[i] = "D"; break;
						case 4: chars[i] = "E"; break;
						case 5: chars[i] = "F"; break;
						case 6: chars[i] = "G"; break;
						case 7: chars[i] = "H"; break;
						case 8: chars[i] = "I"; break;
						case 9: chars[i] = "J"; break;
						case 10: chars[i] = "K"; break;
						case 11: chars[i] = "L"; break;
						case 12: chars[i] = "M"; break;
						case 13: chars[i] = "N"; break;
						case 14: chars[i] = "O"; break;
						case 15: chars[i] = "P"; break;
						case 16: chars[i] = "Q"; break;
						case 17: chars[i] = "R"; break;
						case 18: chars[i] = "S"; break;
						case 19: chars[i] = "T"; break;
						case 20: chars[i] = "U"; break;
						case 21: chars[i] = "V"; break;
						case 22: chars[i] = "W"; break;
						case 23: chars[i] = "X"; break;
						case 24: chars[i] = "Y"; break;
						case 25: chars[i] = "Z"; break;
					}

					result += chars[i];
					result += " ";
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
		return new String[]{"letter", "randomletter", "rl"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-letter <" + L10N.getString("ln.help.number", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-letter <" + L10N.getString("ln.help.number", event) + "> || " + L10N.getString("letter.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("ln.notes", event);
	}
}