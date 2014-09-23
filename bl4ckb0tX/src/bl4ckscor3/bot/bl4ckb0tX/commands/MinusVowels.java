package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class MinusVowels implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		char[] chars = event.getMessage().substring(8).toLowerCase().toCharArray();
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < chars.length; i++)
		{
			switch(chars[i])
			{
				case 'a': builder.append(""); break;
				case 'e': builder.append(""); break;
				case 'i': builder.append(""); break;
				case 'o': builder.append(""); break;
				case 'u': builder.append(""); break;
				default: builder.append(chars[i]);
			}
		}
		
		Utilities.respond(event, builder.toString(), false);
	}
	
	@Override
	public String getAlias()
	{
		return "vowels";
	}

	@Override
	public String getSyntax()
	{
		return "-vowels <sentence>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-vowels <sentence> || Removes all vowels from the given sentence."};
	}

	@Override
	public String getNotes()
	{
		return "Useless.";
	}
}
