package bl4ckscor3.bot.bl4ckb0t.util;

import java.net.URISyntaxException;
import java.util.Date;

import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

public class Utilities
{
	/**
	 * Gets the current time from UNIX
	 */
	public static Date getCurrentDate()
	{
		return new Date(System.currentTimeMillis());
	}

	/**
	 * Gets the path of the running jar file
	 */
	public static String getJarLocation() throws URISyntaxException
	{
		String path = Core.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

		if(path.endsWith(".jar"))
			path = path.substring(0, path.lastIndexOf("/"));

		return path;
	}

	/**
	 * Splits a command into its arguments, leaving out the command itself
	 * @param line The string to split
	 * @return An array of all the arguments
	 */
	public static String[] toArgs(String line)
	{
		String[] previous = line.split(" ");
		String[] result = new String[previous.length - 1];

		for(int i = 1; i < previous.length; i++)
		{
			result[i - 1] = previous[i];
		}

		return result;

	}

	/**
	 * Checks if the specified string is starting with a number
	 * @param s The string to check
	 * @return Wether the string starts with a number or not
	 */
	public static boolean startsWithNumber(String s)
	{
		return s.startsWith("1") || s.startsWith("2") || s.startsWith("3") || s.startsWith("4") || s.startsWith("5") || s.startsWith("6") || s.startsWith("7") || s.startsWith("8") || s.startsWith("9") || s.startsWith("0");
	}

	/**
	 * Checks wether the given user has the given permission level
	 * @param user The user to check for
	 * @param level The level to check for, must be either 1, 2 or 3
	 * @return true if the user has the given permission level, false otherwise, or if the level is not 1, 2 or 3
	 */
	public static boolean hasPermission(String user, int level)
	{
		if(level == 1)
			return true;
		else if(level == 2)
			return Lists.isLvl2User(user);
		else if(level == 3)
			return Lists.isLvl3User(user);
		return false;
	}

	/**
	 * Sends a message to a channel or user
	 * @param target The message receiver
	 * @param msg The message
	 */
	public static void sendMessage(String target, String msg)
	{
		Core.bot.sendIRC().message(target, msg);
		Logging.message(target, Core.bot.getNick(), msg);
	}
	
	/**
	 * Sends a message in the following format:
	 * 		** data[0] ** data[1] ** ... ** data[data.length - 1] **
	 * The message as well as each argument is prefixed by normal color, latter is bold, too 
	 * @param event The channel to send the message to
	 * @param args The contents of the message seperated by **
	 */
	public static void sendStarMsg(String channel, String... data)
	{
		String result = Colors.NORMAL + "";
		
		for(String s : data)
		{
			result += Colors.NORMAL + Colors.BOLD + " ** " + s;
		}
		
		sendMessage(channel, result.replaceFirst(" ", "") + Colors.NORMAL + Colors.BOLD + " **");
	}
}
