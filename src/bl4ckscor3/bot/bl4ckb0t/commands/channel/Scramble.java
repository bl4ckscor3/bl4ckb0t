package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Scramble extends BaseCommand<MessageEvent<Bot>>
{
	private Random r = new Random();
	private String[][] actions = {
			new String[]{"R", "R'", "R2"}, 
			new String[]{"L", "L'", "L2"}, 
			new String[]{"D", "D'", "D2"}, 
			new String[]{"U", "U'", "U2"}, 
			new String[]{"F", "F'", "F2"}, 
			new String[]{"B", "B'", "B2"}
	};

	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException
	{
		if(Utilities.toArgs(event.getMessage()).length == 1)
		{
			String[] results = new String[20];
			String result = "";

			for(int i = 0; i < 20; i++)
			{
				results[i] = (actions[r.nextInt(actions.length)][r.nextInt(3)]);
			}

			for(int i = 0; i < results.length - 2; i++)
			{
				compareJumping(results, i);
			}

			//comparing the last two ones (preventing an aioob exception in the for loop above)
			compareDirectly(results, 18);

			for(String s : results)
			{
				result += s + " ";
			}

			Utilities.chanMsg(event, result);
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	private void compareDirectly(String[] results, int i)
	{
		//compare if both values are in the same array
		if(getFirstLetter(results[i]).equals(getFirstLetter(results[i + 1])))
		{
			reRoll(results, i + 1); //reroll if so
			compareDirectly(results, i); //revalidate in case it re-rolls to the same array value
		}
	}

	private void compareJumping(String[] results, int i)
	{
		//compare if both values are in the same array
		if(getFirstLetter(results[i]).equals(getFirstLetter(results[i + 2])))
		{
			reRoll(results, i + 2); //reroll if so
			compareJumping(results, i); //revalidate in case it re-rolls to the same array value
		}

		//make sure the above code didn't screw up
		compareDirectly(results, i);
	}

	private String getFirstLetter(String value)
	{
		return value.substring(0, 1);
	}

	private void reRoll(String[] results, int i)
	{
		results[i] = actions[r.nextInt(actions.length)][r.nextInt(3)];
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"scramble", "erlbscma"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-scramble";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-scramble || " + L10N.getString("scramble.explanation", event)};
	}
}
