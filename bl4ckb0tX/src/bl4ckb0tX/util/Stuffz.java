package bl4ckb0tX.util;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

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
	
	public static void respond(MessageEvent event, String msg)
	{
		chanMsg(event, getNick(event) + ", " + msg);
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
}
