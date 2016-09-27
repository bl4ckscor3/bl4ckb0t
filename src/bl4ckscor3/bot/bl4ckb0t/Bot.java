package bl4ckscor3.bot.bl4ckb0t;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

public class Bot extends PircBotX
{
	private boolean wasStartedAsWIP;
	private final String cmdPrefix;
	
	public Bot(Configuration configuration, boolean wip, String prefix)
	{
		super(configuration);
		wasStartedAsWIP = wip;
		cmdPrefix = prefix;
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
		Logging.severe("BOT LEFT THE SERVER");
	}
	
	/**
	 * Checks wether the bot is started with -wip or not
	 */
	public boolean isDevelopment()
	{
		return wasStartedAsWIP;
	}
	
	/**
	 * Joins a channel given the bot hasn't already joined the channel
	 * @param channel The channel to join
	 */
	public void joinChannel(String channel)
	{
		sendIRC().joinChannel(channel);
		Logging.info("Joined " + channel);
	}
	
	/**
	 * Joins a channel with a password given the bot hasn't already joined the channel
	 * @param channel The channel to join
	 * @param password The password of the channel
	 */
	public void joinChannelWithPassword(String channel, String password)
	{
		sendIRC().joinChannel(channel, password);
		Logging.info("Joined " + channel);
	}

	/**
	 * Leaves a channel given the bot hasn't already left the channel
	 * @param channel The channel to leave
	 */
	public void leaveChannel(String channel)
	{
		sendRaw().rawLine("PART " + channel + " :");
		Logging.info("Left " + channel);
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
	
	/**
	 * @return The prefix the commands of this bot should start with
	 */
	public String getCmdPrefix()
	{
		return cmdPrefix;
	}
}
