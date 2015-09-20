package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;

public class Bot extends PircBotX
{
	public Bot(Configuration<? extends PircBotX> configuration)
	{
		super(configuration);
	}
	
	/**
	 * Sends a message to someone
	 * @param target The message receiver
	 * @param msg The message
	 */
	public void sendCustomMessage(String target, String msg)
	{
		sendIRC().message(target, msg);
		Logging.info("Sent message \"" + msg + "\" to " + target + ".");
	}
	
	/**
	 * Kicks a user from a channel
	 * @param channel The channel to kick from
	 * @param target The user to kick
	 * @param reason The reason for the kick
	 */
	public void kick(String channel, String target, String reason)
	{
		sendRaw().rawLine("KICK " + channel + " " + target + " :" + reason);
		Logging.warn("Kicked " + target + " from " + channel + " for \"" + reason + "\".");
	}

	/**
	 * Quits the server with a reason
	 * @param reason The reason
	 */
	public void quit(String reason)
	{
		sendIRC().quitServer(reason);
		Logging.warn("BOT LEFT THE SERVER");
	}
	
	/**
	 * Checks wether the bot is started with -wip or not
	 */
	public boolean isDevelopment()
	{
		return getConfiguration().getVersion().endsWith("_WIP");
	}
	
	/**
	 * Joins a channel given the bot hasn't already joined the channel
	 * @param channel The channel to join
	 */
	public void joinChannel(String channel)
	{
		sendIRC().joinChannel(channel);

		if(!L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.put(channel, "english");

		if(!CMDListener.channelStates.containsKey(channel))
			CMDListener.channelStates.put(channel, true);

		Logging.info("Joined " + channel + "...");
	}
	
	/**
	 * Joins a channel with a password given the bot hasn't already joined the channel
	 * @param channel The channel to join
	 * @param password The password of the channel
	 */
	public void joinChannelWithPassword(String channel, String password)
	{
		sendIRC().joinChannel(channel, password);

		if(!L10N.chanLangs.containsKey(channel))
		{
			if(channel.equals("#akino_germany"))
				L10N.chanLangs.put(channel, "german");
			else
				L10N.chanLangs.put(channel, "english");
		}

		if(!CMDListener.channelStates.containsKey(channel))
			CMDListener.channelStates.put(channel, true);
		
		Logging.info("Joined " + channel + "...");
	}

	/**
	 * Joins the default channels given the bot hasn't already joined them
	 */
	public void joinDefaults() throws MalformedURLException, IOException
	{
		List<String> channelsToJoin = Lists.getDefaultChans();

		for(String s : channelsToJoin)
		{
			if(s.equalsIgnoreCase("#akino_germany"))
				joinChannelWithPassword(s, Passwords.AKINO_GERMANY.getPassword());
			else if(s.equalsIgnoreCase("#BreakInBadStaff"))
				joinChannelWithPassword(s, Passwords.BREAKINBADSTAFF.getPassword());
			else
				joinChannel(s);
		}
	}
	
	/**
	 * Leaves a channel given the bot hasn't already left the channel
	 * @param channel The channel to leave
	 */
	public void leaveChannel(String channel)
	{
		sendRaw().rawLine("PART " + channel + " :" + L10N.getString("channel.part"));

		if(L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.remove(channel);

		if(CMDListener.channelStates.containsKey(channel))
			CMDListener.channelStates.remove(channel);
		
		Logging.info("Left " + channel + "...");
	}
	
	/**
	 * Sends an action to a channel (/me)
	 * @param target The channel to send the message to
	 * @param msg The action to take (/me)
	 */
	public void action(String target, String msg)
	{
		sendIRC().action(target, msg);
		Logging.action(target, getNick(), msg);
	}
}
