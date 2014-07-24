package bl4ckscor3.bot.bl4ckb0tX.util;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;

import com.google.common.collect.ImmutableSortedSet;

public class Utilities
{
	private static String[] validUsers =
		{
			"bl4ck",
			"bl4ckscor3",
			"bl4ckgon3",
			"bl4ckwebgerät",
			"bl4ck_ubuntu",
			"bl4ckdro1d",
			"akino_germany"
		};
	
	public static void chanMsg(MessageEvent event, String msg)
	{
		event.getChannel().send().message(msg);
	}
	
	public static void notice(MessageEvent event, String msg)
	{
		event.getUser().send().notice(msg);
	}
	
	public static void pm(String name, String msg)
	{
		Core.bot.sendIRC().message(name, msg);
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
		pm(event.getUser().getNick(), Colors.BOLD + Colors.RED + msg);
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
	
	public static boolean hasJoinedChannel(String[] args)
	{
		String[] chans = getJoinedChannels();
		boolean joined = false;

		for(String s : chans)
		{
			if(s != null)
			{
				if(s.equalsIgnoreCase(args[1]))
					return true;
			}
		}
		return false;
	}
	
	public static String[] getJoinedChannels()
	{
		ImmutableSortedSet<Channel> list = Core.bot.getUserBot().getChannels();
		Object[] x = list.toArray();
		String[] chans = new String[20];
		int i = 0;

		for(Object o : x)
		{
			chans[i] = o.toString().split(",")[0].split("=")[1];
			i++;
		}
		
		return chans;
	}
}
