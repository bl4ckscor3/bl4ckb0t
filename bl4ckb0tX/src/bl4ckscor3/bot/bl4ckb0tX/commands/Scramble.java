package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.util.Arrays;
import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Scramble implements ICommand<MessageEvent>
{
	private Random r = new Random();
	private String[][] actions = 
		{
			new String[]{"R", "R'", "R2"}, 
			new String[]{"L", "L'", "L2"}, 
			new String[]{"D", "D'", "D2"}, 
			new String[]{"U", "U'", "U2"}, 
			new String[]{"F", "F'", "F2"}, 
			new String[]{"B", "B'", "B2"}
		};

	@Override
	public void exe(MessageEvent event)
	{
		if(Utilities.toArgs(event.getMessage()).length != 1)
			Utilities.respond(event, "you did something wrong :/ No arguments required!", true);
		else
		{
			String[] results = new String[25];
			StringBuilder builder = new StringBuilder();

			for(int i = 0; i < 25; i++)
			{
				results[i] = (actions[r.nextInt(actions.length)][r.nextInt(3)]);
			}

			for(int i = 0; i < results.length - 1; i++) 
			{
				compare(results, i);  
			}

			for(String s : results)
			{
				builder.append(s + " ");
			}

			Utilities.chanMsg(event, builder.toString());
		}
	}

	private void compare(String[] results, int index) 
	{
		//compare if both values are in the same array
		if(getFirstLetter(results[index]).equals(getFirstLetter(results[index+1])))
		{
			reRoll(results, index + 1); //reroll if so
			compare(results, index); //revalidate in case it re-rolls to the same array value
		}
	}

	private String getFirstLetter(String value)
	{
		return value.substring(0, 1);
	}

	private void reRoll(String[] results, int i)
	{
		results[i] = (actions[r.nextInt(actions.length)][r.nextInt(3)]);
	}

	public String getAlias()
	{
		return "scramble";
	}
}
