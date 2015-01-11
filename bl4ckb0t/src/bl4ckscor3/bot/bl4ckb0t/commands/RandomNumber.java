package bl4ckscor3.bot.bl4ckb0t.commands;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class RandomNumber implements ICommand<MessageEvent>
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
				Utilities.chanMsg(event, L10N.strings.getString("ln.fail"));
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}
	
	@Override
	public String getAlias()
	{
		return "number";
	}
	
	@Override
	public String getSyntax()
	{
		return "-number <" + L10N.strings.getString("ln.help.number") + ">";
	}
	
	@Override
	public String[] getUsage()
	{
		return new String[]{"-number <" + L10N.strings.getString("ln.help.number") + "> || " + L10N.strings.getString("letter.explanation")};
	}
	
	@Override
	public String getNotes()
	{
		return L10N.strings.getString("ln.notes");
	}
}
