package bl4ckscor3.bot.bl4ckb0tX.misc;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class SpellingCorrection
{
	//making room for 100 users to store their latest message
	private static String[] messages = new String[100];

	public static void correctSpelling(MessageEvent event, String[] split)
	{
		String user = event.getUser().getNick();
		String toReplace = split[1];
		String replaceWith = split[2];
		int i = 0;
		
		for(String s : messages)
		{
			if(messages[i] == null)
				break;
			
			if(user.equals(messages[i].split("#")[0]))
			{
				String betterMessage = getLatestMessage(user).replaceAll(toReplace, replaceWith);
				System.out.println(messages[i]);
				updateLatestMessage(betterMessage, user);
				Utilities.chanMsg(event, user + Colors.BOLD + " meant " + Colors.BOLD + "to say: " + betterMessage);
			}
			
			i++;
		}
	}

	public static void updateLatestMessage(String msg, String username)
	{
		int i = 0;

		for(String s : messages)
		{ 
			//if the current array position contains no data to replace, stop the loop and add the data
			if(s == null)
				break;

			//checking for the correct array position to potentially replace the latest message with
			if(s.split("#")[0].equals(username))
			{
				messages[i] = username + "#" + msg;
				return;
			}

			i++;
		}

		messages[i] = username + "#" + msg;
	}
	
	private static String getLatestMessage(String user)
	{
		for(String s : messages)
		{
			if(s.split("#")[0].equals(user))
				return s.split("#")[1];
		}
		
		return "Will never happen.";
	}
}
