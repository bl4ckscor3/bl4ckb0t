package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

@SuppressWarnings("rawtypes")
public class Calc
{
	public static void exe(MessageEvent event)
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length != 1)
		{
			if(args.length > 3 && args.length <= 22)
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
					Utilities.respond(event, "I'm too dumb to divide :(", true);
				else if(args[1].equals("pow") || args[1].equals("^") || args[1].equals("^"))
					pow(numbers, event);
			}
			else 
			{
				if(args.length == 2)
					Utilities.respond(event, "please tell me which numbers I should calculate with.", true);
				else if(args.length == 3)
					Utilities.respond(event, "you can't just give me one number to calculate with!", true);
				else
					Utilities.respond(event, "I'm too dumb to calculate more than 20 numbers :(", true);
			}
		}
		else
			Utilities.respond(event, "please tell me which operation I should do, and which numbers I should calculate with.", true);
	}
	
	private static void add(float[] numbers, MessageEvent event)
	{
		float solution = numbers[0];
		
		for(int i = 1; i < numbers.length; i++)
		{
			solution += numbers[i];
		}		
		
		checkForIntAndSend(solution, event);
	}
	
	private static void subtract(float[] numbers, MessageEvent event)
	{
		float solution = numbers[0];
		
		for(int i = 1; i < numbers.length; i++)
		{
			solution -= numbers[i];
		}

		checkForIntAndSend(solution, event);
	}
	
	private static void multiply(float[] numbers, MessageEvent event)
	{
		float solution = 1;
		
		for(int i = 0; i < numbers.length; i++)
		{
			solution *= numbers[i];
		}
		
		checkForIntAndSend(solution, event);
	}
	
	private static void pow(float[] numbers, MessageEvent event)
	{
		double solution = Math.pow(numbers[0], numbers[1]);
		
		Utilities.chanMsg(event, "The solution is: " + solution);
	}
	
	private static void checkForIntAndSend(float solution, MessageEvent event)
	{
		System.out.println(solution);
		if(Float.toString(solution).endsWith(".0"))
			Utilities.chanMsg(event, "The solution is: " + (int)solution);
		else
			Utilities.chanMsg(event, "The solution is: " + solution);
	}
}
