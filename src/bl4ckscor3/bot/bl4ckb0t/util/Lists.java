package bl4ckscor3.bot.bl4ckb0t.util;

import java.util.ArrayList;
import java.util.List;

public class Lists
{
	private static final List<String> defaultChans = new ArrayList<String>();
	private static final List<String> allowedUsers = new ArrayList<String>();
	private static final List<String> validUsers = new ArrayList<String>();
	private static final List<String> ignoredUsers = new ArrayList<String>();
	
	public static void addDefaultChan(String c)
	{
		defaultChans.add(c);
	}
	
	public static void addAllowedUser(String a)
	{
		allowedUsers.add(a);
	}
	
	public static void addValidUser(String v)
	{
		validUsers.add(v);
	}
	
	public static void addIgnoredUser(String i)
	{
		ignoredUsers.add(i);
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
	}
}
