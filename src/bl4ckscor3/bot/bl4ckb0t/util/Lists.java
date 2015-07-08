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
	
	public static void addDefaultChan(String c)
	{
		defaultChans.add(c);
		Logging.info("Added " + c + " to channel list...");
	}
	
	public static void addAllowedUser(String a)
	{
		allowedUsers.add(a);
		Logging.info("Added " + a + " to allowed user list...");
	}
	
	public static void addValidUser(String v)
	{
		validUsers.add(v);
		Logging.info("Added " + v + " to valid user list...");
	}
	
	public static void addIgnoredUser(String i)
	{
		ignoredUsers.add(i);
		Logging.info("Added " + i + " to ignored user list...");
	}
	
	public static List<String> getDefaultChans()
	{
		return defaultChans;
	}
	
	public static List<String> getAllowedUsers()
	{
		return allowedUsers;
	}
	
	public static List<String> getValidUsers()
	{
		return validUsers;
	}
	
	public static List<String> getIgnoredUsers()
	{
		return ignoredUsers;
	}
	
	public static void clearAll()
	{
		defaultChans.clear();
		allowedUsers.clear();
		validUsers.clear();
		ignoredUsers.clear();
		Logging.info("Cleared all lists...");
	}
}
