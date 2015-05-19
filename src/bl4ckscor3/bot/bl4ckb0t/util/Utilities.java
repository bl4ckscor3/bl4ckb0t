package bl4ckscor3.bot.bl4ckb0t.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.channel.ListChans;
import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.core.Listener;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;

import com.google.common.collect.ImmutableSortedSet;

public class Utilities
{
	/*
	 * FROM HERE ONLY MSG EVENT
	 */

	public static void chanMsg(MessageEvent<Bot> event, String msg)
	{
		event.getChannel().send().message(msg);
	}

	public static void notice(MessageEvent<Bot> event, String msg)
	{
		event.getUser().send().notice(msg);
	}

	public static void pm(String name, String msg)
	{
		Core.bot.sendCustomMessage(name, msg);
	}

	public static void respond(MessageEvent<Bot> event, String msg, boolean comma)
	{
		if(comma)
			chanMsg(event, event.getUser().getNick() + ", " + msg);
		else
			chanMsg(event, event.getUser().getNick() + ": " + msg);
	}

	public static void sorry(MessageEvent<Bot> event)
	{
		chanMsg(event, L10N.getString("noPermission"));
	}

	public static void addHelpLine(MessageEvent<Bot> event, String msg)
	{
		pm(event.getUser().getNick(), Colors.BOLD + Colors.RED + msg);
	}

	public static boolean isValidUser(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		return event.getUser().isVerified() && Lists.getValidUsers().contains(event.getUser().getNick());
	}

	/*
	 * FROM HERE ONLY PRIVATE MSG EVENT
	 */

	public static boolean isValidUser(PrivateMessageEvent<Bot> event) throws MalformedURLException, IOException
	{	
		return event.getUser().isVerified() && Lists.getValidUsers().contains(event.getUser().getNick());
	}

	/*
	 * FROM HERE ONLY NON MSG EVENT
	 */

	public static void sendHelp(String nick, String syntax, String[] usage, String notes)
	{
		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "-----------" + L10N.getString("helpMenu.syntax") + "----------");
		Utilities.pm(nick, syntax);
		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("helpMenu.usage") + "----------");

		for(String s : usage)
		{
			Utilities.pm(nick, s);
		}

		Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("helpMenu.notes") + "----------");
		Utilities.pm(nick, notes == null ? L10N.getString("helpMenu.noNotes") : notes);
	}

	public static String[] toArgs(String line)
	{
		return line.split(" ");
	}

	public static boolean hasJoinedChannel(String[] args)
	{
		String[] chans = getJoinedChannels(true);

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

	public static void joinChannel(String channel)
	{
		Core.bot.sendIRC().joinChannel(channel);

		if(!L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.put(channel, "english");

		if(!Listener.channelStates.containsKey(channel))
			Listener.channelStates.put(channel, true);
	}

	public static void joinChannelWithPassword(String channel, String password)
	{
		Core.bot.sendIRC().joinChannel(channel, password);

		if(!L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.put(channel, "english");

		if(!Listener.channelStates.containsKey(channel))
			Listener.channelStates.put(channel, true);
	}

	public static void joinDefaults() throws MalformedURLException, IOException
	{
		List<String> channelsToJoin = Lists.getDefaultChans();

		for(String s : channelsToJoin)
		{
			if(s.equals("#akino_germany"))
				joinChannelWithPassword(s, Passwords.getPassword("#akino_germany"));
			else
				joinChannel(s);
		}
	}

	public static void leaveChannel(String channel)
	{
		Core.bot.sendRaw().rawLine("PART " + channel + " :" + L10N.getString("channel.part"));

		if(L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.remove(channel);

		if(Listener.channelStates.containsKey(channel))
			Listener.channelStates.remove(channel);
	}

	public static String capitalizeFirstLetter(String s)
	{
		return s.substring(0, 1).toUpperCase(L10N.currentLocale) + s.substring(1);
	}
}
