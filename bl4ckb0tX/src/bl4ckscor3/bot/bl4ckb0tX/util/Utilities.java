package bl4ckscor3.bot.bl4ckb0tX.util;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

public class Utilities
{
	private static String[] validUsers =
		{
			"bl4ckscor3",
			"bl4ckgon3",
			"bl4ckwebgerät",
			"bl4ck_ubuntu",
			"bl4ckdro1d"
		};
	
	public static void chanMsg(MessageEvent event, String msg)
	{
		event.getChannel().send().message(msg);
	}
	
	public static void userMsg(MessageEvent event, String msg)
	{
		event.getUser().send().notice(msg);
	}
	
	public static void respond(MessageEvent event, String msg, boolean comma)
	{
		if(comma)
			chanMsg(event, event.getUser().getNick() + ", " + msg);
		else
			chanMsg(event, event.getUser().getNick() + ": " + msg);
	}
	
	public static void sorry(MessageEvent event)
	{
		chanMsg(event, "Sorry, only bl4ckscor3 is allowed to control me.");
	}
	
	public static void addHelpLine(MessageEvent event, String msg)
	{
		Utilities.userMsg(event, Colors.BOLD + Colors.RED + msg);
	}
	
	public static void addEmptyLine(MessageEvent event, boolean sendToChan)
	{
		if(sendToChan)
		{
			Utilities.chanMsg(event, " ");
		}
		else
		{
			Utilities.userMsg(event, " ");
		}
	}
	
	public static boolean validUser(MessageEvent event)
	{
		for(int i = 0; i < validUsers.length; i++)
		{
			if(event.getUser().getNick().equalsIgnoreCase(validUsers[i]))
			{
				return true;
			}
		}
		return false;
	}
	
	public static String[] toArgs(String line)
	{
		return line.split(" ");
	}
	
	public static boolean validUser(PrivateMessageEvent event)
	{	
		for(int i = 0; i < validUsers.length; i++)
		{
			if(event.getUser().getNick().equalsIgnoreCase(validUsers[i]))
			{
				return true;
			}
		}
		return false;
	}
	
	public static String[] getValidUsers()
	{
		return validUsers;
	}
}
