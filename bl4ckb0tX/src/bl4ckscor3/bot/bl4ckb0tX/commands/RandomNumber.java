package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class RandomNumber implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		StringBuilder output = new StringBuilder();
		String[] number = event.getMessage().split(" ");
		int[] numbs = new int[50];
		int n = Integer.parseInt(number[1]);
		
		if(event.getMessage().equalsIgnoreCase("-number"))
			Utilities.chanMsg(event, "Please put in a number. Usage: -number <number>");
	
		if(!(n > 20))
		{
			Random r = new Random();
			
			for(int i = 0; i < n; i++)
			{
				numbs[i] = r.nextInt(10);
				output.append(numbs[i]).append(" ");
			}
			
			Utilities.chanMsg(event, output.toString());
		}
		else
			Utilities.chanMsg(event, "Please don't put in a number higher than 50.");
	}
	
	@Override
	public String getAlias()
	{
		return "number";
	}
}
