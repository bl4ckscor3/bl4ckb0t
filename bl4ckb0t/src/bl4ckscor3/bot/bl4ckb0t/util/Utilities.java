package bl4ckscor3.bot.bl4ckb0t.util;

import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.Date;

import org.pircbotx.Colors;
import org.pircbotx.User;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
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
	
	/**
	 * Sends help about a module
	 * @param m The module to send help about
	 * @param nick The user to send the help to
	 * @param channel The channel the action that triggered this method got sent in
	 */
	public static void sendHelp(Module m, String nick, String channel)
	{
		if(Utilities.hasPermission(nick, m.getPermissionLevel()))
		{
			Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + Core.l10n.translate("help.header.2", channel).replace("#module", m.getName()));
			Utilities.sendMessage(nick, Core.l10n.translate("help.channelCommands", channel));

			if(m.getChannelCommands() != null)
			{
				for(BaseChannelCommand cmd : m.getChannelCommands())
				{
					String result = cmd.getSyntax(channel);

					if(cmd.getAliases().length > 1) //there are aliases
					{
						result += " " + Core.l10n.translate("help.aliases", channel);
						
						for(String s : cmd.getAliases())
						{
							if(s.equals(cmd.getMainAlias()))
								continue;

							result += s + ", ";
						}

						Utilities.sendMessage(nick, "  " + result.substring(0, result.lastIndexOf(",")) + ")");
						
						break;
					}
					
					Utilities.sendMessage(nick, "  " + result);
				}
			}
			else
				Utilities.sendMessage(nick, "  " + Core.l10n.translate("help.none", channel));

			Utilities.sendMessage(nick, Core.l10n.translate("help.privateCommands", channel));

			if(m.getPrivateCommands() != null)
			{
				for(BasePrivateCommand cmd : m.getPrivateCommands())
				{
					Utilities.sendMessage(nick, "  " + cmd.getSyntax(channel));
				}
			}
			else
				Utilities.sendMessage(nick, "  " + Core.l10n.translate("help.none", channel));

			Utilities.sendMessage(nick, Core.l10n.translate("help.usage", channel));

			for(String s : m.getUsage(channel))
			{
				Utilities.sendMessage(nick, "  " + s);
			}

			Utilities.sendMessage(nick, Core.l10n.translate("help.notes", channel));
			Utilities.sendMessage(nick, "  " + m.getNotes(channel));
		}
		else
			Utilities.sendMessage(nick, Core.l10n.translate("help.noPermission", channel));
	}
	
	/**
	 * Sends a notice to a user
	 * @param user The user to send the notice to
	 * @param The notice to send
	 */
	public static void notice(User user, String notice)
	{
		user.send().notice(notice);
		Logging.noticeSent(user.getNick(), notice);
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
	 * @return The String to be used in coloring back- and foreground of a message or null if either background or foreground is not a color (aka bold/underline/reverse etc.)
	 */
	public static String backgroundColor(String background, String foreground)
	{
		if(!background.startsWith("\u0003") || !foreground.startsWith("\u0003"))
			return "";
		return foreground + "," + background.substring(background.length() - 2);
	}
}
