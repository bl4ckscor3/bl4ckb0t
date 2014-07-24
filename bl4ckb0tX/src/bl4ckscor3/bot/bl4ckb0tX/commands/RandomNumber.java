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
		String[] args = event.getMessage().split(" ");
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
					output.append(numbs[i]).append(" ");
				}

				Utilities.pm(event.getUser().getNick(), output.toString());
			}
			else
				Utilities.chanMsg(event, "Please don't put in a number higher than 50.");
		}
		else
			Utilities.respond(event, "please tell me how many numbers I should select for you. Example: -number 5", true);
	}
	@Override
	public String getAlias()
	{
		return "number";
	}
}
