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
	/*
	 * FROM HERE ONLY MSG EVENT
	 */
	
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

	public static boolean isValidUser(MessageEvent event) throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/dyvu276rdwmbt9z/validUsers.txt?dl=1").openStream()));
		
		for(String s : reader.readLine().split(","))
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

	/*
	 * FROM HERE ONLY PRIVATE MSG EVENT
	 */
	
	public static boolean isValidUser(PrivateMessageEvent event) throws MalformedURLException, IOException
	{	
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/dyvu276rdwmbt9z/validUsers.txt?dl=1").openStream()));
		
		for(String s : reader.readLine().split(","))
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
	
	/*
	 * FROM HERE ONLY NON MSG EVENT
	 */
	
	public static void sendHelp(String nick, String syntax, String[] usage, String notes)
	{
		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "-------------------------Syntax--------------------------");
		Utilities.pm(nick, syntax);
		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "--------------------------Usage--------------------------");

		for(String s : usage)
		{
			Utilities.pm(nick, s);
		}

		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "--------------------------Notes--------------------------");
		Utilities.pm(nick, notes == null ? "None." : notes);
	}
	
	public static String[] getValidUsers() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/dyvu276rdwmbt9z/validUsers.txt?dl=1").openStream()));
		
		return reader.readLine().split(",");
	}
	
	public static String[] toArgs(String line)
	{
		return line.split(" ");
	}
	
	public static boolean hasJoinedChannel(String[] args)
	{
		String[] chans = getJoinedChannels();

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
			//if the channel has the flag +s, it does not get shown
			if(!o.toString().contains("secret=true"))
				chans[i] = o.toString().split(",")[0].split("=")[1];

			i++;
		}

		return chans;
	}

	public static String[] getDefaultChans() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/tishdl84z1wmcgs/bl4ckb0t%20chans.txt?dl=1").openStream()));
		
		return reader.readLine().split(",");
	}

	public static String[] getAutoJoinChans() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/tishdl84z1wmcgs/bl4ckb0t%20chans.txt?dl=1").openStream()));

		if(Core.bot.getNick().equals("bl4ckb0t"))
			return reader.readLine().split(",");
		else
			return new String[]{"#bl4ckb0tTest"};
	}
}
