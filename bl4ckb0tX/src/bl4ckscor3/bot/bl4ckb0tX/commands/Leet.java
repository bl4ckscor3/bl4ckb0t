package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Leet implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		char[] chars = event.getMessage().substring(6).toLowerCase().toCharArray();
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < chars.length; i++)
		{
			switch(chars[i])
			{
				case 'a': builder.append("4"); break;
				case 'e': builder.append("3"); break;
				case 'g': builder.append("6"); break;
				case 'h': builder.append("h"); break;
				case 'i': builder.append("1"); break;
				case 'l': builder.append("|"); break;
				case 's': builder.append("5"); break;
				case 't': builder.append("7"); break;
				case 'z': builder.append("2"); break;
				case ' ': builder.append(" "); break;
				default: builder.append(chars[i]);
			}
		}
		Utilities.chanMsg(event, builder.toString());
	}
	
	@Override
	public String getAlias()
	{
		return "leet";
	}
}
