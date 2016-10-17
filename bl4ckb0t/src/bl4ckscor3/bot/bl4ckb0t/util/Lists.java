package bl4ckscor3.bot.bl4ckb0t.util;

import java.util.ArrayList;
import java.util.List;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

public class Lists
{
	private static final List<String> defaultChans = new ArrayList<String>();
	private static final List<String> allowedUsers = new ArrayList<String>();
	private static final List<String> validUsers = new ArrayList<String>();
	private static final List<String> ignoredUsers = new ArrayList<String>();

	/**
	 * Adds a default channel to the bot
	 * @param c The channel to add
	 */
	public static void addDefaultChan(String c)
	{
		defaultChans.add(c);
		Logging.info("  Added " + c + " to channel list");
	}

	/**
	 * Gives a user permission level 2
	 * @param a The user to give the permission level to
	 */
	public static void addLvl2User(String a)
	{
		allowedUsers.add(a);
		Logging.info("  Added " + a + " to allowed user list");
	}

	/**
	 * Gives a user permission level 3
	 * @param v The user to give the permission level to
	 */
	public static void addLvl3User(String v)
	{
		validUsers.add(v);
		Logging.info("  Added " + v + " to valid user list");
	}

	/**
	 * Makes the bot ignore a user
	 * @param i The user to ignore
	 */
	public static void addIgnoredUser(String i)
	{
		ignoredUsers.add(i);
		Logging.info("  Added " + i + " to ignored user list");
	}

	/**
	 * @return The bot's default channels
	 */
	public static List<String> getDefaultChans()
	{
		return defaultChans;
	}

	/**
	 * @return All users with a permission level of 2
	 */
	public static List<String> getLvl2Users()
	{
		return allowedUsers;
	}

	/**
	 * @return All users with a permission level of 3
	 */
	public static List<String> getLvl3Users()
	{
		return validUsers;
	}

	/**
	 * @return All ignored users
	 */
	public static List<String> getIgnoredUsers()
	{
		return ignoredUsers;
	}

	/**
	 * Checks wether the given user has permission level 2
	 * @param user The user to check
	 * @return true if the given user has permission level 2, false otherwise
	 */
	public static boolean isLvl2User(String user)
	{
		return getLvl2Users().contains(user) || getLvl3Users().contains(user);
	}
	
	/**
	 * Checks wether the given user has permission level 3
	 * @param user The user to check
	 * @return true if the given user has permission level 3, false otherwise
	 */
	public static boolean isLvl3User(String user)
	{
		return getLvl3Users().contains(user);
	}
	
	/**
	 * Checks wether the given user is being ignored by the bot
	 * @param user The user to check
	 * @return true if the given user is being ignored, false otherwise
	 */
	public static boolean isIgnored(String user)
	{
		return getIgnoredUsers().contains(user);
	}
	
	/**
	 * Clears all lists
	 */
	public static void clearAll()
	{
		defaultChans.clear();
		allowedUsers.clear();
		validUsers.clear();
		ignoredUsers.clear();
		Logging.info("Cleared all lists");
	}
}
