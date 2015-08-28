package bl4ckscor3.bot.bl4ckb0t.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.channel.ListChans;
import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.CMDListener;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

import com.google.common.collect.ImmutableSortedSet;

public class Utilities
{
	/*
	 * FROM HERE ONLY MSG EVENT
	 */

	/**
	 * Sends a message to a channel
	 * @param event The event which holds the channel to send the message to
	 * @param msg The message
	 */
	public static void chanMsg(MessageEvent<Bot> event, String msg)
	{
		event.getChannel().send().message(msg);
		Logging.message(event.getChannel().getName(), Core.bot.getNick(), msg);
	}
	
	/**
	 * Sends a notice to a user
	 * @param event The event which holds the user to send the notice to
	 * @param notice The message
	 */
	public static void notice(MessageEvent<Bot> event, String notice)
	{
		event.getUser().send().notice(notice);
		Logging.noticeSent(event.getUser().getNick(), notice);
	}

	/**
	 * Sends a pm to a user
	 * @param name The user who receives the pm
	 * @param msg The message to send
	 */
	public static void pm(String name, String msg)
	{
		Core.bot.sendCustomMessage(name, msg);
	}

	/**
	 * Tells the user that he doesn't have permission for the action he wanted to take
	 * @param event The event which holds the user and the channel from the action
	 */
	public static void noPermission(MessageEvent<Bot> event)
	{
		chanMsg(event, L10N.getString("noPermission"));
	}

	/**
	 * Sends a help line to a user
	 * @param event The event which holds the user
	 * @param msg The help line to send
	 */
	public static void addHelpLine(MessageEvent<Bot> event, String msg)
	{
		pm(event.getUser().getNick(), Colors.BOLD + Colors.RED + msg);
	}

	/**
	 * Checks wether a user is a valid user (equal to admin)
	 * @param event The event which holds the user to check
	 * @return Wether the user is a valid user
	 */
	public static boolean isValidUser(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		return event.getUser().isVerified() && Lists.getValidUsers().contains(event.getUser().getNick());
	}

	/**
	 * Checks wether a user is an allowed user (equal to moderator)
	 * @param event The event which holds the user to check
	 * @return Wether the user is an allowed user
	 */
	public static boolean isAllowedUser(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		return event.getUser().isVerified() && Lists.getAllowedUsers().contains(event.getUser().getNick());
	}
	
	/**
	 * Gets the permission level of the user
	 * @param event The event which holds the user
	 * @return The permission level of the user
	 */
	public static int getUserPermissionLevel(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		return isValidUser(event) ? 3 : (isAllowedUser(event) ? 2 : 1);
	}
	
	/*
	 * FROM HERE ONLY PRIVATE MSG EVENT
	 */

	/**
	 * Checks wether a user is a valid user (equal to admin)
	 * @param event The event which holds the user to check
	 * @return Wether the user is a valid user
	 */
	public static boolean isValidUser(PrivateMessageEvent<Bot> event) throws MalformedURLException, IOException
	{	
		return event.getUser().isVerified() && Lists.getValidUsers().contains(event.getUser().getNick());
	}

	/*
	 * FROM HERE ONLY NON MSG EVENT
	 */

	/**
	 * Sends a help menu to a user
	 * @param nick The user to receive the menu
	 * @param syntax The syntax of the command which's help menu gets shown
	 * @param usage The usage of the command
	 * @param notes The notes of the command
	 */
	public static void sendHelp(String nick, String syntax, String[] usage, String notes)
	{
		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "-----------" + L10N.getString("helpMenu.syntax") + "----------");
		Utilities.pm(nick, syntax);
		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("helpMenu.usage") + "----------");

		for(String s : usage)
		{
			Utilities.pm(nick, s);
		}

		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("helpMenu.notes") + "----------");
		Utilities.pm(nick, notes == null ? L10N.getString("helpMenu.noNotes") : notes);
	}

	/**
	 * Splits a string into all its words
	 * @param line The string to split
	 * @return The string array of all the words
	 */
	public static String[] toArgs(String line)
	{
		return line.split(" ");
	}

	/**
	 * Checks wether the bot has joined the given channel
	 * @param channel The channel to check
	 * @return Wether the bot has joined the given channel or not
	 */
	public static boolean hasJoinedChannel(String channel)
	{
		String[] chans = getJoinedChannels(true);

		for(String s : chans)
		{
			if(s != null)
			{
				if(s.equalsIgnoreCase(channel))
					return true;
			}
		}
		return false;
	}

	/**
	 * Gets the channels with mode +s the bot has joined
	 */
	public static List<String> getSecretChannels()
	{
		ImmutableSortedSet<Channel> list = Core.bot.getUserBot().getChannels();
		Object[] x = list.toArray();
		List<String> chans = new ArrayList<String>();

		for(Object o : x)
		{
			//if the channel has the flag +s, it does not get shown
			if(o.toString().contains("secret=true"))
				chans.add(o.toString().split(",")[0].split("=")[1]);
		}

		return chans;
	}

	/**
	 * Gets the channels the bot has joined
	 * @param listSecretChannels Wether to list secret channels
	 */
	public static String[] getJoinedChannels(boolean listSecretChannels)
	{
		ImmutableSortedSet<Channel> list = Core.bot.getUserBot().getChannels();
		Object[] x = list.toArray();
		String[] chans = new String[x.length];
		int i = 0;

		for(Object o : x)
		{
			if(!listSecretChannels)
			{
				//if the channel has the flag +s, it does not get shown
				if(!o.toString().contains("secret=true"))
					chans[i] = o.toString().split(",")[0].split("=")[1];
				else
					ListChans.secretChannelAmount++;
			}
			else
				chans[i] = o.toString().split(",")[0].split("=")[1];
			i++;
		}

		return chans;
	}

	/**
	 * Joins a channel given the bot hasn't already joined the channel
	 * @param channel The channel to join
	 */
	public static void joinChannel(String channel)
	{
		Core.bot.sendIRC().joinChannel(channel);

		if(!L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.put(channel, "english");

		if(!CMDListener.channelStates.containsKey(channel))
			CMDListener.channelStates.put(channel, true);

		Logging.info("Joined " + channel + "...");
	}

	/**
	 * Joins a channel with a password given the bot hasn't already joined the channel
	 * @param channel The channel to join
	 * @param password The password of the channel
	 */
	public static void joinChannelWithPassword(String channel, String password)
	{
		Core.bot.sendIRC().joinChannel(channel, password);

		if(!L10N.chanLangs.containsKey(channel))
		{
			if(channel.equals("#akino_germany"))
				L10N.chanLangs.put(channel, "german");
			else
				L10N.chanLangs.put(channel, "english");
		}

		if(!CMDListener.channelStates.containsKey(channel))
			CMDListener.channelStates.put(channel, true);
		
		Logging.info("Joined " + channel + "...");
	}

	/**
	 * Joins the default channels given the bot hasn't already joined them
	 */
	public static void joinDefaults() throws MalformedURLException, IOException
	{
		List<String> channelsToJoin = Lists.getDefaultChans();

		for(String s : channelsToJoin)
		{
			if(s.equalsIgnoreCase("#akino_germany"))
				joinChannelWithPassword(s, Passwords.AKINO_GERMANY.getPassword());
			else if(s.equalsIgnoreCase("#BreakInBadStaff"))
				joinChannelWithPassword(s, Passwords.BREAKINBADSTAFF.getPassword());
			else
				joinChannel(s);
		}
	}

	/**
	 * Leaves a channel given the bot hasn't already left the channel
	 * @param channel The channel to leave
	 */
	public static void leaveChannel(String channel)
	{
		Core.bot.sendRaw().rawLine("PART " + channel + " :" + L10N.getString("channel.part"));

		if(L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.remove(channel);

		if(CMDListener.channelStates.containsKey(channel))
			CMDListener.channelStates.remove(channel);
		
		Logging.info("Left " + channel + "...");
	}

	/**
	 * Capiatlizes the first letter of a string
	 * @param s The string to capialize the first letter of
	 * @return The capitalized first letter including the rest of the string
	 */
	public static String capitalizeFirstLetter(String s)
	{
		return s.substring(0, 1).toUpperCase(L10N.currentLocale) + s.substring(1);
	}
	
	/**
	 * Sends an action to a channel (/me)
	 * @param target The channel to send the message to
	 * @param msg The action to take (/me)
	 */
	public static void action(String target, String msg)
	{
		Core.bot.sendIRC().action(target, msg);
		Logging.action(target, Core.bot.getNick(), msg);
	}
	
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
		return Core.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
	}
}
