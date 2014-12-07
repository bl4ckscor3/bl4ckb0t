package bl4ckscor3.bot.bl4ckb0tX.misc;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class SpellingCorrection
{
	//making room for 100 users to store their latest message
	private static String[] messages = new String[100];
	//needed to check if the message should be added to the array or not
	public static boolean corrected = false;

	public static void correctSpelling(MessageEvent event, String[] split, boolean correctsDifferentUser, String userToCorrect)
	{
		String toReplace = split[1];
		String replaceWith = split[2];

		System.out.println(userToCorrect);

		for(String s : messages)
		{
			if(s == null)
				break;

			if(userToCorrect.equals(s.split("#")[0]))
			{
				String correctedMessage = getLatestMessage(userToCorrect).replaceAll(toReplace, replaceWith);

				updateLatestMessage(correctedMessage, userToCorrect);

				if(correctsDifferentUser)
					Utilities.chanMsg(event, userToCorrect + " was corrected by " + event.getUser().getNick() + " and " + Colors.BOLD + " meant " + Colors.BOLD + "to say: " + correctedMessage);
				else
					Utilities.chanMsg(event, userToCorrect + Colors.BOLD + " meant " + Colors.BOLD + "to say: " + correctedMessage);
			}
		}
	}

	/**
	 * Adds or updates a user with his latest message
	 * @param msg - The message to write into the array
	 * @param username - The name of the user who wrote the message
	 */
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
//				System.out.println("stuff written into array position " + i + " -- stuff: " + messages[i]);
				return;
			}

			i++;
		}

		messages[i] = username + "#" + msg;
//		System.out.println("stuff written into array position " + i + " -- stuff: " + messages[i]);
	}

	/**
	 * Returns the latest message from the given user
	 * @param user - The name of the user to get the latest message from
	 * @return - The latest message from the given user
	 */
	private static String getLatestMessage(String user)
	{
		for(String s : messages)
		{
			if(s.split("#")[0].equals(user))
				return s.split("#")[1];
		}

		return "Will never happen.";
	}

	/**
	 * Determine if the message contains a syntax to correct a message sent before
	 * @param event - The triggered MessageEvent
	 * @param message - The message sent to the channel which triggered the event
	 */
	public static void checkForSpellingCorrection(MessageEvent event, String message)
	{
		//checking if someone corrects someone else
		if(message.split(" ")[0].endsWith(":") || message.split(" ")[0].endsWith(","))
		{
			boolean colon;

			if(message.split(" ")[0].endsWith(":"))
				colon = true;
			else
				colon = false;

			if(message.split(" ")[1].startsWith("s/"))
			{
				String[] split;
				String newMessage = "";
				int i = 0;

				//actually getting only the s/x/y message if it contains spaces	
				for(String s : message.split(" "))
				{
					if(i != 0)
						newMessage += s + " ";

					i++;
				}

				//removing the last character of the string to prevent 2 spaces
				newMessage = newMessage.substring(0, newMessage.length() - 1);
				split = newMessage.split("/");

				if(split.length == 3 && split[0].equals("s"))
				{
					SpellingCorrection.correctSpelling(event, split, true, colon ? message.split(":")[0] : message.split(",")[0]);
					corrected = true;
				}

				return;
			}
		}

		//checking if someone is correcting himself
		if(message.startsWith("s/"))
		{
			String[] split = message.split("/");

			if(split.length == 3 && split[0].equals("s"))
			{
				SpellingCorrection.correctSpelling(event, split, false, event.getUser().getNick());
				corrected = true;
			}
			else
				return;
		}
	}
}
