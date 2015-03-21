package bl4ckscor3.bot.bl4ckb0t.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class SpellingCorrection
{
	//<channel, <user#message>>
	public static HashMap<String, List<String>> storage = new HashMap<String, List<String>>();
	//needed to check if the message should be added to the array or not (used in Listener.java)
	public static boolean corrected = false;

	private static void correctSpelling(MessageEvent<Bot> event, String[] split, boolean correctsDifferentUser, String userToCorrect)
	{
		String toReplace = split[1];
		String replaceWith = split[2];

		for(String s : storage.get(event.getChannel().getName()))
		{
			if(s == null)
				break;

			if(userToCorrect.equals(s.split("#")[0]))
			{
				String previousMessage = getLatestMessage(userToCorrect, storage.get(event.getChannel().getName()));
				String correctedMessage = getLatestMessage(userToCorrect, storage.get(event.getChannel().getName())).replace(toReplace, replaceWith);

				if(previousMessage.equals(correctedMessage))
					return;
				
				updateLatestMessage(event.getChannel().getName(), correctedMessage, userToCorrect);

				if(correctsDifferentUser)
					Utilities.chanMsg(event, userToCorrect + " " + L10N.strings.getString("correction.1") + " " + event.getUser().getNick() + " " + L10N.strings.getString("correction.2") + ": " + correctedMessage);
				else
					Utilities.chanMsg(event, userToCorrect + " " + L10N.strings.getString("correction.3") + ": " + correctedMessage);
			}
		}
	}

	/**
	 * Adds or updates a user with his latest message
	 * @param msg - The message to write into the array
	 * @param username - The name of the user who wrote the message
	 */
	public static void updateLatestMessage(String channel, String msg, String username)
	{
		int i = 0;

		if(!storage.containsKey(channel))
			storage.put(channel, new ArrayList<String>());
		
		for(String s : storage.get(channel))
		{ 
			//if the current array position contains no data to replace, stop the loop and add the data
			if(s == null)
				break;

			//checking for the correct array position to potentially replace the latest message with
			if(s.split("#")[0].equals(username))
			{
				storage.get(channel).set(i, username + "#" + msg);
				return;
			}

			i++;
		}

		storage.get(channel).add(username + "#" + msg);
	}

	/**
	 * Returns the latest message from the given user
	 * @param user - The name of the user to get the latest message from
	 * @return - The latest message from the given user
	 */
	private static String getLatestMessage(String user, List<String> messages)
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
	public static void checkForSpellingCorrection(MessageEvent<Bot> event, String message)
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
					correctSpelling(event, split, true, colon ? message.split(":")[0] : message.split(",")[0]);
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
