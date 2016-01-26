package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Scramble extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length > 1)
		{
			String result = "";
		
			for(int i = 1; i < args.length; i++)
			{
				char[] cA = args[i].toCharArray();
				List<Character> list = new ArrayList<Character>();
				
				for(char c : cA)
				{
					list.add(c);
				}
				
				Collections.shuffle(list);
				
				for(char c : list)
				{
					result += "" + c;
				}
				
				result += " ";
			}
			
			Utilities.chanMsg(event, result.trim());
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}
	
	@Override
	public String[] getAliases()
	{
		return new String[]{"scramble", "blrscame", "shuffle"};
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
