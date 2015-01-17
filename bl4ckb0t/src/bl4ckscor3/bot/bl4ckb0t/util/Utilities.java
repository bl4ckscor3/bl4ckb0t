package bl4ckscor3.bot.bl4ckb0t.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;

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
		chanMsg(event, L10N.strings.getString("noPermission"));
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
		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "-----------" + L10N.strings.getString("helpMenu.syntax") + "----------");
		Utilities.pm(nick, syntax);
		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.strings.getString("helpMenu.usage") + "----------");

		for(String s : usage)
		{
			Utilities.pm(nick, s);
		}

		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.strings.getString("helpMenu.notes") + "----------");
		Utilities.pm(nick, notes == null ? L10N.strings.getString("helpMenu.noNotes") : notes);
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
	
	public static void joinChannel(String channel)
	{
		Core.bot.sendIRC().joinChannel(channel);
		
		if(!L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.put(channel, "english");
	}
	
	public static void joinChannelWithPassword(String channel, String password)
	{
		Core.bot.sendIRC().joinChannel(channel, password);
		
		if(!L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.put(channel, "english");
	}
	
	public static void joinDefaults() throws MalformedURLException, IOException
	{
		String[] channelsToJoin = getAutoJoinChans();

		for(String s : channelsToJoin)
		{
			if(s.equals("#akino_germany"))
				joinChannelWithPassword(s, Core.password);
			else
				joinChannel(s);
		}
	}
	
	public static void leaveChannel(String channel)
	{
		Core.bot.sendRaw().rawLine("PART " + channel + " :" + L10N.strings.getString("channel.part"));
		
		if(L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.remove(channel);
	}
}
