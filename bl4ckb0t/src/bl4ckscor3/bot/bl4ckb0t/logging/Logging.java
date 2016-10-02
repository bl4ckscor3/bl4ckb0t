package bl4ckscor3.bot.bl4ckb0t.logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.KickEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.NickChangeEvent;
import org.pircbotx.hooks.events.NoticeEvent;
import org.pircbotx.hooks.events.PartEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.events.QuitEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

/**
 * To use this class, you first need to disable all loggers with "java.util.logging.Logger.getLogger("").setLevel(java.util.logging.Level.OFF);
 * If you use slf4j, make sure that you use slf4j-nop and not anything else. If you use a different logging interface for PircBotX, use its slf4j-nop equivalent.
 * 
 * To set up this logger, simply add Logging.setup("yourBotsName"); before you create your bot's configuration and add this class as a Listener.
 * Call any public method in here to log to the console and to the file.
 * Make sure that you change the debug() method to your liking, as it is not made to work for any bot.
 * 
 * Upon starting, it will create a new file called yourBotsName.log in the root folder of your project/the jar location.
 * When that file already exists, it copys the contents of it into a new file into a new folder called logs. The file name is "yourBotsName - current date and time.log"
 * After copying is done, the original file will get used for new logging. 
 * 
 * @author bl4ckscor3
 */
public class Logging extends ListenerAdapter
{
	private static boolean enabled = true;
	private static File f;
	private static FileWriter writer;
	private static boolean failed = true;
	private static final List<String> buffer = new ArrayList<String>();

	/**
	 * Sets up the logger and saves the previous logging file into a seperate folder
	 * @param botName The name of the bot
	 */
	public static void setup(String botName)
	{
		try
		{
			String jarPath = Utilities.getJarLocation();

			new File(jarPath + "/logs/").mkdirs();
			f = new File(jarPath + "/logs/" + botName + ".log");

			if(!f.exists())
			{
				buffer.add("\"" + botName + ".log\" does not exist, creating new file...");
				f.createNewFile();
				buffer.add("File successfully created!");
			}
			else
			{
				File copy = new File(jarPath + "/logs/");

				copy.mkdirs();
				copy = new File(jarPath + "/logs/" + botName + " - " + Utilities.getCurrentDate().toString().replace(":", "-") + ".log");
				buffer.add("Created new file to copy to: \"" + copy.getName() + "\"");
				buffer.add("Starting copy process...");

				FileWriter copyWriter = new FileWriter(copy);
				BufferedReader reader = new BufferedReader(new FileReader(f));
				String line = "";

				while((line = reader.readLine()) != null)
				{
					copyWriter.write(line + "\n");
					copyWriter.flush();
				}

				copyWriter.close();
				reader.close();
				buffer.add("Successfully copied old logging file.");
			}

			writer = new FileWriter(f);
			failed = false;
			enable();
		}
		catch(Exception e)
		{
			severe("Could not create logger!");
			Logging.stackTrace(e);
			disable();
		}
	}

	/**
	 * Enables the logger if it hasn't failed setting it up
	 */
	public static void enable()
	{
		if(!failed)
		{
			enabled = true;
			start();
		}
	}

	/**
	 * Disables the logger
	 */
	public static void disable()
	{
		end();
		enabled = false;
	}
	
	/**
	 * Logs a severe message
	 * @param line The message to log
	 */
	public static void severe(String line)
	{
		log("[SEVERE] " + line);
	}

	/**
	 * Logs a StackTrace
	 * @param t The Throwable of which the StackTrace gets logged
	 */
	public static void stackTrace(Throwable t)
	{
		raw(t.toString());

		for(StackTraceElement e : t.getStackTrace())
		{
			raw("	at " + e.toString());
		}
	}

	/**
	 * Logs a warning message
	 * @param line The message to log
	 */
	public static void warn(String line)
	{
		log("[WARNING] " + line);
	}

	/**
	 * Logs an info message
	 * @param line The message to log
	 */
	public static void info(String line)
	{
		log("[INFO] " + line);
	}

	/**
	 * Logs a debug message if the bot is in a development version (This is hardcoded for bl4ckb0t)
	 * @param line The message to log
	 */
	public static void debug(Object line)
	{
		if(Core.bot.isDevelopment())
			log("[DEBUG] " + line.toString());
	}

	/**
	 * Logs an irc message
	 * @param target The channel/user the message got sent to
	 * @param sender The sender of the message
	 * @param msg The message
	 */
	public static void message(String target, String sender, String msg)
	{
		log("[MESSAGE] " + target + " | " + sender + ": " + msg);
	}

	/**
	 * Logs a sent irc notice
	 * @param receiver The receiver of the notice
	 * @param notice The notice
	 */
	public static void noticeSent(String receiver, String notice)
	{
		log("[NOTICE] " + receiver + " | " + Core.bot.getNick() + " - " + notice);
	}


	/**
	 * Logs a received irc notice
	 * @param sender The sender of the notice
	 * @param notice The notice
	 */
	public static void noticeReceived(String sender, String notice)
	{
		log("[NOTICE] " + sender + ": " + notice);
	}

	/**
	 * Logs an irc pm
	 * @param sender The sender of the pm
	 * @param pm The pm
	 */
	public static void pm(String sender, String pm)
	{
		log("[PM] " + sender + ": " + pm);
	}

	/**
	 * Logs an irc action (/me)
	 * @param channel The channel the action got sent to
	 * @param sender The sender of the action
	 * @param action The action
	 */
	public static void action(String channel, String sender, String action)
	{
		log("[ACTION] " + channel + " | *" + sender + " " + action);
	}

	/**
	 * Logs the starting point of the logging
	 */
	private static void start()
	{
		raw("Started logging on " + Utilities.getCurrentDate().toString());

		for(String s : buffer)
		{
			info(s);
		}

		buffer.clear();
	}

	/**
	 * Logs the ending point of the logging
	 */
	private static void end()
	{
		raw("Ended logging on " + Utilities.getCurrentDate().toString());
	}

	/**
	 * Logs a raw line (without date) if logging is enabled and it hasn't failed setting up
	 * @param line The line
	 */
	private static void raw(String line)
	{
		if(enabled)
		{
			if(!failed)
			{
				try
				{
					writer.write(line + "\n");
					writer.flush();
				}
				catch (IOException e)
				{
					e.printStackTrace(); //don't call Logging.stackTrace(e); here
				}
			}

			System.out.println(Colors.removeFormattingAndColors(line));
		}
		
		System.gc();
	}

	/**
	 * Logs a message
	 * @param message The message
	 */
	private static void log(String line)
	{
		if(enabled)
		{
			if(!failed)
			{
				try
				{
					writer.write(Utilities.getCurrentDate().toString() + " " + Colors.removeFormattingAndColors(line) + "\n");
					writer.flush();
				}
				catch (IOException e)
				{
					e.printStackTrace(); //don't call Logging.stackTrace(e); here
				}
			}

			System.out.println(Utilities.getCurrentDate().toString() + " " + Colors.removeFormattingAndColors(line));
		}
		
		System.gc();
	}

	/***************************Listeners***************************/

	@Override
	public void onMessage(MessageEvent event) throws Exception
	{
		message(event.getChannel().getName(), event.getUser().getNick(), event.getMessage());
	}
	@Override
	public void onAction(ActionEvent event) throws Exception
	{
		action(event.getChannel().getName(), event.getUser().getNick(), event.getMessage());
	}

	@Override
	public void onNotice(NoticeEvent event) throws Exception
	{
		noticeReceived(event.getUser().getNick(), event.getMessage());
	}

	@Override
	public void onKick(KickEvent event) throws Exception
	{
		if(event.getRecipient().getNick().equals(Core.bot.getNick()))
			warn("Bot kicked from " + event.getChannel().getName() + " for \"" + event.getReason() + "\".");
		else
			info(event.getRecipient().getNick() + " was kicked from " + event.getChannel().getName() + " for \"" + event.getReason() + "\".");
	}

	@Override
	public void onNickChange(NickChangeEvent event) throws Exception
	{
		info(event.getOldNick() + " changed their nick to " + event.getNewNick() + ".");
	}

	@Override
	public void onPart(PartEvent event) throws Exception
	{
		info(event.getUser().getNick() + " left " + event.getChannel().getName() + ": \"" + event.getReason() + "\".");
	}

	@Override
	public void onJoin(JoinEvent event) throws Exception
	{
		if(!event.getUser().getNick().equals(Core.bot.getNick()))
			info(event.getUser().getNick() + " joined " + event.getChannel().getName() + ".");
	}

	@Override
	public void onQuit(QuitEvent event) throws Exception
	{
		info(event.getUser().getNick() + " quit: \"" + event.getReason() + "\".");
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent event) throws Exception
	{
		pm(event.getUser().getNick(), event.getMessage());
	}

	@Override
	public void onDisconnect(DisconnectEvent event) throws Exception
	{
		severe("Disconnected from server!");
	}

	/***************************Getters***************************/

	public static boolean isEnabled()
	{
		return enabled;
	}

	public static File getFile()
	{
		return f;
	}

	public static FileWriter getWriter()
	{
		return writer;
	}

	public static boolean hasFailed()
	{
		return failed;
	}
}
