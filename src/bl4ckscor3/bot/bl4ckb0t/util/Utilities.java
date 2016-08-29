package bl4ckscor3.bot.bl4ckb0t.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

import com.google.common.collect.ImmutableSortedSet;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.ListChans;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

public class Utilities
{
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
	 * Sends a notice to a user
	 * @param user The user to send the notice to
	 * @param notice The message
	 */
	public static void notice(User user, String notice)
	{
		user.send().notice(notice);
		Logging.noticeSent(user.getNick(), notice);
	}

	/**
	 * Tells the user that he doesn't have permission for the action he wanted to take
	 * @param channel The channel to send the message to
	 */
	public static void noPermission(String channel)
	{
		sendMessage(channel, L10N.getString("noPermission", channel));
	}

	/**
	 * Sends a help line to a user
	 * @param user The user to send the help line to
	 * @param msg The help line to send
	 */
	public static void addHelpLine(String user, String msg)
	{
		sendMessage(user, Colors.BOLD + Colors.RED + msg);
	}

	/**
	 * Checks wether a user is a valid user (equal to admin)
	 * @param user The user to check
	 * @return Wether the user is a valid user
	 */
	public static boolean isValidUser(User user) throws MalformedURLException, IOException
	{
		return user.isVerified() && Lists.getValidUsers().contains(user.getNick()); //commented out due to esper blocking whois requests
	}

	/**
	 * Checks wether a user is an allowed user (equal to moderator)
	 * @param user The user to check
	 * @return Wether the user is an allowed user
	 */
	public static boolean isAllowedUser(User user) throws MalformedURLException, IOException
	{
		return user.isVerified() && Lists.getAllowedUsers().contains(user.getNick()); //commented out due to esper blocking whois requests
	}

	/**
	 * Gets the permission level of the user
	 * @param user The user to check
	 * @return The permission level of the user
	 */
	public static int getUserPermissionLevel(User user) throws MalformedURLException, IOException
	{
		return isValidUser(user) ? 3 : (isAllowedUser(user) ? 2 : 1);
	}

	/**
	 * Sends a message in the following format:
	 * 		** x1 ** x2 ** ... ** xn **
	 * Where x1...xn is the data array in order. The message as well as each argument is prefixed by normal color, latter is bold, too 
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
	
	/**
	 * Sends a help menu to a user
	 * @param nick The user to receive the menu
	 * @param aliases The aliases of the command
	 * @param mainAlias The main alias of the command
	 * @param syntax The syntax of the command which's help menu gets shown
	 * @param usage The usage of the command
	 * @param notes The notes of the command
	 * @param channel The channel the help command got sent in
	 */
	public static void sendHelp(String nick, String[] aliases, String mainAlias, String syntax, String[] usage, String notes, String channel)
	{
		String formattedAliases = "";

		sendMessage(nick, Colors.BOLD + Colors.OLIVE + "-----------" + L10N.getString("helpMenu.syntax", channel) + "-----------");
		sendMessage(nick, syntax);
		sendMessage(nick, Colors.BOLD + Colors.OLIVE + "-----------" + L10N.getString("helpMenu.usage", channel) + "-----------");

		for(String s : usage)
		{
			sendMessage(nick, s);
		}

		if(aliases.length != 1)
		{
			for(int i = 0; i < aliases.length; i++)
			{
				if(aliases[i] == null || aliases[i].equals(mainAlias))
					continue;

				formattedAliases += "-" + aliases[i] + " | ";

				if((i + 1) % 10 == 0)
				{
					sendMessage(nick, formattedAliases.trim());
					formattedAliases = "";
				}
			}
		}

		sendMessage(nick, Colors.BOLD + Colors.OLIVE + "-----------" + L10N.getString("helpMenu.aliases", channel) + "-----------");
		
		try
		{
			sendMessage(nick, formattedAliases.substring(0, formattedAliases.lastIndexOf(" | ")));
		}
		catch(StringIndexOutOfBoundsException e)
		{
			formattedAliases += L10N.getString("helpMenu.noNotes", channel);
			
			sendMessage(nick, formattedAliases);
		}
		
		sendMessage(nick, Colors.BOLD + Colors.OLIVE + "-----------" + L10N.getString("helpMenu.notes", channel) + "-----------");
		sendMessage(nick, notes == null ? L10N.getString("helpMenu.noNotes", channel) : notes);
		System.gc();
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
	 * Capiatlizes the first letter of a string
	 * @param s The string to capialize the first letter of
	 * @return The capitalized first letter including the rest of the string
	 */
	public static String capitalizeFirstLetter(String s)
	{
		return s.substring(0, 1).toUpperCase() + s.substring(1);
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
		String path = Core.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

		if(path.endsWith(".jar"))
			path = path.substring(0, path.lastIndexOf("/"));

		return path;
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
	 * Checks wether a user is ignored
	 * @param nick The user to check
	 * @return Wether the user is ignored or not
	 */
	public static boolean isIgnored(String nick)
	{
		return Core.bot.getConfig().isEnabled("allowIgnoringUsers") && Lists.getIgnoredUsers().contains(nick);
	}
	
	/**
	 * Formats a double to two decimal places
	 * @param d The double to format
	 * @return The formatted double
	 */
	public static String formatDouble(double d)
	{
		return new DecimalFormat("#.00").format(d).replace(",", ".");
	}
	
	/**
	 * Generates a String which represents a background and foreground color
	 * @param background The background color to use (use PircBotX's Colors class)
	 * @param foreground The foreground color to use (use PircBotX's Colors class)
	 * @return The String to be used in coloring back- and foreground of a message or an empty String if either background or foreground is not a color (aka bold/underline/reverse etc.)
	 */
	public static String backgroundColor(String background, String foreground)
	{
		if(!background.startsWith("\u0003") || !foreground.startsWith("\u0003"))
			return "";
		return foreground + "," + background.substring(background.length() - 2);
	}
}
