package bl4ckscor3.bot.bl4ckb0t.misc;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class SpellingCorrection
{
	//making room for 50 channels, each storing 50 users including their latest message
	private static String[][] messages = new String[50][50];
	//storing the position of the channels in the messages array
	private static String[] channels = new String[50];
	//needed to check if the message should be added to the array or not (used in Listener.java)
	public static boolean corrected = false;

	public static void correctSpelling(MessageEvent<Bot> event, String[] split, boolean correctsDifferentUser, String userToCorrect)
	{
		String toReplace = split[1];
		String replaceWith = split[2];
		int channelPosition = getChannelPosition(event.getChannel().getName());

		for(String s : messages[channelPosition])
		{
			if(s == null)
				break;

			if(userToCorrect.equals(s.split("#")[0]))
			{
				String previousMessage = getLatestMessage(userToCorrect, channelPosition);
				String correctedMessage = getLatestMessage(userToCorrect, channelPosition).replace(toReplace, replaceWith);

				if(previousMessage.equals(correctedMessage))
					return;
				
				updateLatestMessage(channels[channelPosition], correctedMessage, userToCorrect);

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
		int channelPosition = getChannelPosition(channel);

		for(String s : messages[channelPosition])
		{ 
			//if the current array position contains no data to replace, stop the loop and add the data
			if(s == null)
				break;

			//checking for the correct array position to potentially replace the latest message with
			if(s.split("#")[0].equals(username))
			{
				messages[channelPosition][i] = username + "#" + msg;
				return;
			}

			i++;
		}

		messages[channelPosition][i] = username + "#" + msg;
	}

	/**
	 * Returns the latest message from the given user
	 * @param user - The name of the user to get the latest message from
	 * @return - The latest message from the given user
	 */
	private static String getLatestMessage(String user, int channelPosition)
	{
		for(String s : messages[channelPosition])
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

	/**
	 * Returns the position of the given channel in the channels array
	 * @param channel - The channel to search for
	 * @return - Position of the given channel in the channels array
	 */
	private static int getChannelPosition(String channel)
	{
		boolean channelNotFound = false;
		int channelPosition = 0;
		
		try
		{
			for(String s : channels)
			{
				if(s.equals(channel))
					break;

				channelPosition++;
			}
		}
		catch(NullPointerException e)
		{
			channelNotFound = true;
		}

		//writing the channel position into the array if the channel has no been found
		if(channelNotFound)
		{
			channelPosition = 0;

			for(String s : channels)
			{
				if(s == null)
				{
					channels[channelPosition] = channel;
					break;
				}

				channelPosition++;
			}
		}
		
		return channelPosition;
	}
}
