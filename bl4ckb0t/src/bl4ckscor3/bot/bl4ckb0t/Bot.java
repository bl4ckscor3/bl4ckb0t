package bl4ckscor3.bot.bl4ckb0t;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;

public class Bot extends PircBotX
{
	private final String cmdPrefix;
	
	public Bot(Configuration configuration, String prefix)
	{
		super(configuration);
		cmdPrefix = prefix;
	}
	
	/**
	 * Kicks a user from a channel
	 * @param channel The channel to kick from
	 * @param target The user to kick
	 * @param reason The reason for the kick
	 */
	public void kick(String channel, String target, String reason) throws IOException
	{
		sendRawLineToServer("KICK " + channel + " " + target + " :" + reason);
		Logging.info("Kicked " + target + " from " + channel + " for \"" + reason + "\"");
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
	 * Joins a channel given the bot hasn't already joined the channel
	 * @param channel The channel to join
	 */
	public void joinChannel(String channel)
	{
		sendIRC().joinChannel(channel);
		
		if(!L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.put(channel, "english");
		
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
		
		if(!L10N.chanLangs.containsKey(channel))
		{
			if(channel.equals("#akino_germany"))
				L10N.chanLangs.put(channel, "german");
			else
				L10N.chanLangs.put(channel, "english");
		}
		
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
	
	/**
	 * Joins the default channels given the bot hasn't already joined them
	 */
	public void joinDefaults() throws MalformedURLException, IOException
	{
		List<String> channelsToJoin = Lists.getDefaultChans();

		outer:
		for(String s : channelsToJoin)
		{
			for(Passwords p : Passwords.class.getEnumConstants())
			{
				if(s.replace("#", "").equalsIgnoreCase(p.toString()))
				{
					joinChannelWithPassword(s, p.getPassword());
					continue outer;
				}
			}
			
			joinChannel(s);
		}
}
}
