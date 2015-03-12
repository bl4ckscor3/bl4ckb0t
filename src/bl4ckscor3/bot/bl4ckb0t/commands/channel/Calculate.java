package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Calculate implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event)
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length != 1)
		{
			if((args.length > 3 && args.length <= 22) || args[1].equals("!"))
			{
				float[] numbers = new float[args.length - 2];

				for(int i = 2; i < args.length; i++)
				{
					numbers[i - 2] = Float.parseFloat(args[i]);
				}

				if(args[1].equals("+"))
					add(numbers, event);
				else if(args[1].equals("-"))
					subtract(numbers, event);
				else if(args[1].equals("*") || args[1].equals("x"))
					multiply(numbers, event);
				else if(args[1].equals("/"))
					Utilities.respond(event, L10N.strings.getString("calc.cannotDivide"), true);
				else if(args[1].equals("pow") || args[1].equals("^"))
					pow(numbers, event);
				else if(args[1].equals("%"))
					modulo(numbers, event);
				else if(args[1].equals("!"))
					Utilities.chanMsg(event, "" + fact(event, Integer.parseInt(args[2])));
			}
			else 
				Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}

	private void add(float[] numbers, MessageEvent<Bot> event)
	{
		float solution = numbers[0];

		for(int i = 1; i < numbers.length; i++)
		{
			solution += numbers[i];
		}		

		checkForIntAndSend(solution, event);
	}

	private void subtract(float[] numbers, MessageEvent<Bot> event)
	{
		float solution = numbers[0];

		for(int i = 1; i < numbers.length; i++)
		{
			solution -= numbers[i];
		}

		checkForIntAndSend(solution, event);
	}

	private void multiply(float[] numbers, MessageEvent<Bot> event)
	{
		float solution = 1;

		for(int i = 0; i < numbers.length; i++)
		{
			solution *= numbers[i];
		}

		checkForIntAndSend(solution, event);
	}

	private void pow(float[] numbers, MessageEvent<Bot> event)
	{
		double solution = Math.pow(numbers[0], numbers[1]);

		checkForIntAndSend((float)solution, event);
	}

	private void modulo(float[] numbers, MessageEvent<Bot> event) 
	{
		float solution = numbers[0];

		for(int i = 1; i < numbers.length; i++)
		{
			solution %= numbers[i];
		}

		checkForIntAndSend(solution, event);
	}

	private int fact(MessageEvent<Bot> event, int i)
	{
		if(i == 1)
			return 1;
		else
			return i * fact(event, i - 1);
	}

	private void checkForIntAndSend(float solution, MessageEvent<Bot> event)
	{
		if(Float.toString(solution).endsWith(".0"))
			Utilities.chanMsg(event, L10N.strings.getString("calc.solution") + ": " + (int)solution);
		else
			Utilities.chanMsg(event, L10N.strings.getString("calc.solution") + ": " + solution);
	}

	@Override
	public String getAlias()
	{
		return "calc";
	}

	@Override
	public String getSyntax() 
	{
		return "-calc <+|-|*|x|/|pow|^|!> <" + L10N.strings.getString("calc.help.numbers") + ">";
	}

	@Override
	public String[] getUsage() 
	{
		return new String[]
				{
				"-calc + 1 2 || 1 + 2 " + L10N.strings.getString("calc.help.example"),
				"-calc - 1 2 || 1 - 2 " + L10N.strings.getString("calc.help.example"),
				"-calc <*|x> 1 2 || 1 * 2 " + L10N.strings.getString("calc.help.exampleMutliply"),
				"-calc / 1 2 || 1 / 2 " + L10N.strings.getString("calc.help.example"),
				"-calc <^|pow> 1 2 || 1² " + L10N.strings.getString("calc.help.example"),
				"-calc ! 5 || 5 * 4 * 3 * 2 * 1 " + L10N.strings.getString("calc.help.example")
				};
	}

	@Override
	public String getNotes() 
	{
		return null;
	}
}
