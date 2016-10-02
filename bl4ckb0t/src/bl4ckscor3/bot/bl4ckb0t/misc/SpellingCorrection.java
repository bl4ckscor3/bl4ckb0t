package bl4ckscor3.bot.bl4ckb0t.misc;

import java.util.ArrayList;
import java.util.List;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class SpellingCorrection
{
	//<channel, <user#message>>
	public static ArrayMap<String, List<String>> storage = new ArrayMap<String, List<String>>();
	//needed to check if the message should be added to the array or not (used in MiscListener.java)
	public static boolean corrected = false;

	/**
	 * Determine if the message contains a syntax to correct a message sent before
	 * @param event - The triggered MessageEvent
	 * @param message - The message sent to the channel which triggered the event
	 */
	public static void checkForSpellingCorrection(MessageEvent event, String message)
	{
		String[] spaceSplit = message.split(" ");
		
		//checking if someone corrects someone else
		if(spaceSplit.length > 1 && (spaceSplit[0].endsWith(":") || spaceSplit[0].endsWith(",")))
		{
			boolean colon;

			if(spaceSplit[0].endsWith(":"))
				colon = true;
			else
				colon = false;

			if(spaceSplit[1].startsWith("s/"))
			{
				String[] split;
				String newMessage = "";
				int i = 0;

				//actually getting only the s/x/y message if it contains spaces	
				for(String s : spaceSplit)
				{
					if(i != 0)
						newMessage += s + " ";

					i++;
				}

				//removing the last character of the string to prevent 2 spaces
				newMessage = newMessage.substring(0, newMessage.length() - 1);

				if(newMessage.endsWith("/"))
					newMessage += " ";
				
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
			String[] split;

			if(message.endsWith("/"))
				message += "/"; //a not displayed character to prevent a nullpointer exception
			
			split = message.split("/");

			if(split.length == 3 && split[0].equals("s"))
			{
				correctSpelling(event, split, false, event.getUser().getNick());
				corrected = true;
			}
		}
	}
	
	/**
	 * Prepares and sends the message of the corrected spelling
	 * @param event The MessageEvent this spelling correction originated from
	 * @param split The message split by slashes
	 * @param correctsDifferentUser Wether or not the issuer corrects their own or another person's spelling
	 * @param userToCorrect The user to correct the spelling of
	 */
	private static void correctSpelling(MessageEvent event, String[] split, boolean correctsDifferentUser, String userToCorrect)
	{
		String toReplace = split[1];
		String replaceWith = split[2];
		String channel = event.getChannel().getName();
		
		for(String s : storage.get(channel))
		{
			if(s == null)
				break;

			if(userToCorrect.equals(s.split("#")[0]))
			{
				String previousMessage;
				String newMessage;
				String correctedMessage;

				if(toReplace.equals(""))
				{
					previousMessage = getLatestMessage(userToCorrect, storage.get(channel));
					newMessage = replaceWith;
					correctedMessage = "\u001d" + replaceWith;
				}
				else
				{
					previousMessage = getLatestMessage(userToCorrect, storage.get(channel));
					newMessage = getLatestMessage(userToCorrect, storage.get(channel)).replace(toReplace, replaceWith); //w/o italics
					correctedMessage = getLatestMessage(userToCorrect, storage.get(channel)).replace(toReplace, "\u001d" + replaceWith + Colors.NORMAL); //w/ italics
				}
				
				if(previousMessage.equals(correctedMessage))
					return;

				if(correctsDifferentUser)
					Utilities.sendMessage(channel, Colors.NORMAL + L10N.getString("correction.1", channel).replace("#user1", userToCorrect).replace("#user2", event.getUser().getNick()).replace("#correctedMsg", correctedMessage));
				else
					Utilities.sendMessage(channel, Colors.NORMAL + L10N.getString("correction.2", channel).replace("#user", userToCorrect).replace("#correctedMsg", correctedMessage));

				updateLatestMessage(channel, newMessage, userToCorrect);
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
}