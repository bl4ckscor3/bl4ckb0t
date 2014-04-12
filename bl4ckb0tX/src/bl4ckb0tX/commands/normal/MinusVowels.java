package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

@SuppressWarnings("rawtypes")
public class MinusVowels
{
	public static void exe(MessageEvent event)
	{
		char[] chars = event.getMessage().substring(8).toLowerCase().toCharArray();
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < chars.length; i++)
		{
			switch(chars[i])
			{
				case 'a':
					builder.append("");
					break;
				case 'e':
					builder.append("");
					break;
				case 'i':
					builder.append("");
					break;
				case 'o':
					builder.append("");
					break;
				case 'u':
					builder.append("");
					break;
				default:
					builder.append(chars[i]);
			}
		}
		Utilities.respond(event, builder.toString(), false);
	}
}
