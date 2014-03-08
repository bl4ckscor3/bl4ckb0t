package bl4ckb0tX.commands.normal;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class RandomStuff
{
	public static void letter(MessageEvent event)
	{
		StringBuilder output = new StringBuilder();
		String[] number = event.getMessage().split(" ");
		String[] chars = new String[20];
		int n = Integer.parseInt(number[1]);
		
		if(Stuffz.getMessage(event).equalsIgnoreCase("-letter"))
		{
			Stuffz.chanMsg(event, "Please put in a number.");
		}
		
		if(!(n > 20))
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
				
				output.append(chars[i]);
				output.append(" ");
			}
			Stuffz.chanMsg(event, output.toString());
		}
		else
		{
			Stuffz.chanMsg(event, "Please don't put in a number higher than 20");
		}
	}
	
	public static void number(MessageEvent event)
	{
		StringBuilder output = new StringBuilder();
		String[] number = event.getMessage().split(" ");
		int[] numbs = new int[20];
		int n = Integer.parseInt(number[1]);

		if(Stuffz.getMessage(event).equalsIgnoreCase("-number"))
		{
			Stuffz.chanMsg(event, "Please put in a number.");
		}
		
		if(!(n > 20))
		{
			for(int i = 0; i < n; i++)
			{
				Random rand = new Random();
				numbs[i] = rand.nextInt(10);
				output.append(numbs[i]);
				output.append(" ");
			}
			Stuffz.chanMsg(event, output.toString());
		}
		else
		{
			Stuffz.chanMsg(event, "Please don't put in a number higher than 20");
		}
	}
}
