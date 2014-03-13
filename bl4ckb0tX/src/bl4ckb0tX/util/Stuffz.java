package bl4ckb0tX.util;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class Stuffz
{
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
			chanMsg(event, getNick(event) + ", " + msg);
		else
			chanMsg(event, getNick(event) + ": " + msg);
	}
	
	public static void sorry(MessageEvent event)
	{
		chanMsg(event, "Sorry, only bl4ckscor3 is allowed to control me.");
	}
	
	public static String getNick(MessageEvent event)
	{
		return event.getUser().getNick();
	}
	
	public static String getMessage(MessageEvent event)
	{
		return event.getMessage();
	}
	
	public static void addHelpLine(MessageEvent event, String msg)
	{
		Stuffz.userMsg(event, Colors.BOLD + Colors.BLUE + msg);
	}
	
	public static void addEmptyLine(MessageEvent event, boolean sendToChan)
	{
		if(sendToChan)
		{
			Stuffz.chanMsg(event, " ");
		}
		else
		{
			Stuffz.userMsg(event, " ");
		}
	}
	
	public static boolean validUser(MessageEvent event)
	{
		String[] validUsers =
			{
				"bl4ckscor3",
				"bl4ckgon3",
				"bl4ckweb",
				"bl4ckdro1d"
			};
		
		for(int i = 0; i < validUsers.length; i++)
		{
			if(getNick(event).equalsIgnoreCase(validUsers[i]))
			{
				return true;
			}
		}
		return false;
	}
	
	public static String seperate(MessageEvent event)
	{
		return getMessage(event).split(" ")[1];
	}
	
	public static boolean validUser(PrivateMessageEvent event)
	{
		String[] validUsers =
			{
				"bl4ckscor3",
				"bl4ckgon3",
				"bl4ckweb",
				"bl4ckdro1d"
			};
		
		for(int i = 0; i < validUsers.length; i++)
		{
			if(event.getUser().getNick().equalsIgnoreCase(validUsers[i]))
			{
				return true;
			}
		}
		return false;
	}
}
