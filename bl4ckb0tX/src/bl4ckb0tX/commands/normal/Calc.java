package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

@SuppressWarnings("rawtypes")
public class Calc
{
	public static void exe(MessageEvent event)
	{
		if(event.getUser().getNick().equals("bl4ckscor3")){
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length != 1)
		{
			if(args.length != 2 && args.length <= 22)
			{
				int[] numbers = new int[args.length - 2];
				
				for(int i = 2; i < args.length; i++)
				{
					numbers[i - 2] = Integer.parseInt(args[i]);
				}
				
				if(args[1].equals("+"))
					add(numbers, event);
				else if(args[1].equals("-"))
					subtract(numbers, event);
				else if(args[1].equals("*") || args[1].equals("x"))
					multiply(numbers, event);
				else if(args[1].equals("/"))
					Utilities.respond(event, "I'm too dumb to divide :(", true);
			}
			else 
			{
				if(args.length == 2)
					Utilities.respond(event, "please tell me which numbers I should calculate with.", true);
				else
					Utilities.respond(event, "I'm too dumb to calculate more than 20 numbers :(", true);
			}
		}
		else
			Utilities.respond(event, "please tell me which operation I should do, and which numbers I should calculate with.", true);}
	}
	
	private static void add(int[] numbers, MessageEvent event)
	{
		int solution = numbers[0];
		
		for(int i = 1; i < numbers.length; i++)
		{
			solution += numbers[i];
		}		
		
		Utilities.chanMsg(event, "The solution is: " + solution);
	}
	
	private static void subtract(int[] numbers, MessageEvent event)
	{
		int solution = numbers[0];
		
		for(int i = 1; i < numbers.length; i++)
		{
			solution -= numbers[i];
		}

		Utilities.chanMsg(event, "The solution is: " + solution);
	}
	
	private static void multiply(int[] numbers, MessageEvent event)
	{
		int solution = 1;
		
		for(int i = 0; i < numbers.length; i++)
		{
			solution *= numbers[i];
			System.out.println(solution);
		}

		Utilities.chanMsg(event, "The solution is: " + solution);
	}
}
