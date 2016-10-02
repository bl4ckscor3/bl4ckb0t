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
	private static final List<String> blacklistedWebsites = new ArrayList<String>();
	
	/**
	 * Adds a default channel to the bot
	 * @param c The channel to add
	 */
	public static void addDefaultChan(String c)
	{
		defaultChans.add(c);
		Logging.info("Added " + c + " to channel list...");
	}
	
	/**
	 * Gives a user permission level 2
	 * @param a The user to give the permission level to
	 */
	public static void addAllowedUser(String a)
	{
		allowedUsers.add(a);
		Logging.info("Added " + a + " to allowed user list...");
	}
	
	/**
	 * Gives a user permission level 3
	 * @param v The user to give the permission level to
	 */
	public static void addValidUser(String v)
	{
		validUsers.add(v);
		Logging.info("Added " + v + " to valid user list...");
	}
	
	/**
	 * Makes the bot ignore a user
	 * @param i The user to ignore
	 */
	public static void addIgnoredUser(String i)
	{
		ignoredUsers.add(i);
		Logging.info("Added " + i + " to ignored user list...");
	}
	
	/**
	 * Makes the bot ignore a website
	 * @param i The website to ignore
	 */
	public static void addBlacklistedWebsite(String i)
	{
		blacklistedWebsites.add(i);
		Logging.info("Added " + i + " to blacklisted websites list...");
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
	public static List<String> getAllowedUsers()
	{
		return allowedUsers;
	}
	
	/**
	 * @return All users with a permission level of 3
	 */
	public static List<String> getValidUsers()
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
	 * @return All blacklisted websites
	 */
	public static List<String> getBlacklistedWebsites()
	{
		return blacklistedWebsites;
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
		blacklistedWebsites.clear();
		Logging.info("Cleared all lists...");
	}
}
