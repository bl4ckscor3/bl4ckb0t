package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Leet implements Command<MessageEvent>
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
				case 'b': builder.append("b"); break;
				case 'c': builder.append("c"); break;
				case 'd': builder.append("d"); break;
				case 'e': builder.append("3"); break;
				case 'f': builder.append("f"); break;
				case 'g': builder.append("6"); break;
				case 'h': builder.append("h"); break;
				case 'i': builder.append("1"); break;
				case 'j': builder.append("j"); break;
				case 'k': builder.append("k"); break;
				case 'l': builder.append("|"); break;
				case 'm': builder.append("m"); break;
				case 'n': builder.append("n"); break;
				case 'o': builder.append("0"); break;
				case 'p': builder.append("p"); break;
				case 'q': builder.append("q"); break;
				case 'r': builder.append("r"); break;
				case 's': builder.append("5"); break;
				case 't': builder.append("7"); break;
				case 'u': builder.append("u"); break;
				case 'v': builder.append("v"); break;
				case 'w': builder.append("w"); break;
				case 'x': builder.append("x"); break;
				case 'y': builder.append("y"); break;
				case 'z': builder.append("z"); break;
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
