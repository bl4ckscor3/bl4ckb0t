package bl4ckscor3.bot.bl4ckb0tX.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
			"bl4ckscor3",
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
		chanMsg(event, "Sorry, only certain people are allowed to control me.");
	}
	
	public static void addHelpLine(MessageEvent event, String msg)
	{
		pm(event.getUser().getNick(), Colors.BOLD + Colors.RED + msg);
	}
	
	public static boolean validUser(MessageEvent event)
	{
		for(String s : validUsers)
		{
			if(event.getUser().getNick().equalsIgnoreCase(s))
			{
				if(event.getUser().isVerified())
					return true;
				else
					return false;
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
		for(String s : validUsers)
		{
			if(event.getUser().getNick().equalsIgnoreCase(s))
			{
				if(event.getUser().isVerified())
					return true;
				else
					return false;
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
		String[] chans = new String[x.length];
		int i = 0;

		for(Object o : x)
		{
			chans[i] = o.toString().split(",")[0].split("=")[1];
			i++;
		}
		
		return chans;
	}
	
	public static String[] addAutoJoinChans() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/tishdl84z1wmcgs/bl4ckb0t%20chans.txt?dl=1").openStream()));
	
		if(Core.bot.getNick().equals("bl4ckb0t"))
			return reader.readLine().split(",");
		else
			return new String[]{"#bl4ckb0tTest"};
	}
}
