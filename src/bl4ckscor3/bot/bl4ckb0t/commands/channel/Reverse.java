package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Reverse implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event)
	{
		String message = event.getMessage().substring(9);
		char[] cA = message.toCharArray();
		List<Character> list = new ArrayList<Character>();
		String finalMsg = "";
		
		for(char c : cA)
		{
			list.add(c);
		}
		
		Collections.reverse(list);
		
		for(char c : list)
		{
			finalMsg += c;
		}
		
		Utilities.chanMsg(event, finalMsg);
	}

	@Override
	public String getAlias()
	{
		return "reverse";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-reverse <" + L10N.getString("cmd.help.sentence", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]
				{
				"-reverse <" + L10N.getString("cmd.help.sentence", event) + "> | " + L10N.getString("reverse.explanation", event)
				};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return null;
	}
	
	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
