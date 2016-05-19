package bl4ckscor3.bot.bl4ckb0t;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;

import bl4ckscor3.bot.bl4ckb0t.commands.ICommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class Bot extends PircBotX
{
	private boolean enabled = true;
	private final ArrayMap<String, Boolean> channelStates = new ArrayMap<String, Boolean>(); //false = disabled | true = enabled
	private boolean wasStartedAsWIP;
	private ConfigurationFile config;
	private final String cmdPrefix;
	
	public Bot(Configuration configuration, boolean wip, ConfigurationFile cfg, String prefix)
	{
		super(configuration);
		wasStartedAsWIP = wip;
		config = cfg;
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

		if(!L10N.chanLangs.containsKey(channel))
		{
			try
			{
				L10N.setChannelLanguage(channel, "english");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if(!channelStates.containsKey(channel))
			channelStates.put(channel, true);

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
			try
			{
				if(channel.equals("#akino_germany"))
					L10N.setChannelLanguage(channel, "german");
				else
					L10N.setChannelLanguage(channel, "english");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		if(!channelStates.containsKey(channel))
			channelStates.put(channel, true);

		Logging.info("Joined " + channel + "...");
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
	
	/**
	 * Leaves a channel given the bot hasn't already left the channel
	 * @param channel The channel to leave
	 */
	public void leaveChannel(String channel)
	{
		sendRaw().rawLine("PART " + channel + " :");
		channel = channel.replace("#", "");

		if(L10N.chanLangs.containsKey(channel))
			L10N.chanLangs.remove(channel);

		if(channelStates.containsKey(channel))
			channelStates.remove(channel);
		
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
	
	/**
	 * Checks wether the bot is enabled or not and returns that value
	 */
	public boolean isEnabled()
	{
		return enabled;
	}
	
	/**
	 * Disables the bot
	 */
	public void disable()
	{
		enabled = false;
	}
	
	/**
	 * Enables the bot
	 */
	public void enable()
	{
		enabled = true;
	}
	
	/**
	 * Gets the ArrayMap of channel states (enabled/disabled per channel)
	 */
	public ArrayMap<String, Boolean> getChannelStates()
	{
		return channelStates;
	}
	
	/**
	 * Gets the bot's custom configuration file
	 */
	public ConfigurationFile getConfig()
	{
		return config;
	}
	
	/**
	 * Executes a command
	 * @param e The type of Event the command got sent from (either PrivateMessageEvent or MessageEvent)
	 * @param cmd The command that should get issued
	 * @param args The argumetns the command should get issued with
	 */
	public <E extends Event> void dispatchCommand(E e, ICommand<E> cmd, String[] args) throws Exception
	{
		String[] newArgs = new String[args.length - 1];
		
		for(int i = 0; i < newArgs.length; i++)
		{
			newArgs[i] = args[i + 1];
		}

		cmd.exe(e, newArgs);
		System.gc();
	}
	
	/**
	 * @return The prefix the commands of this bot should start with
	 */
	public String getCmdPrefix()
	{
		return cmdPrefix;
	}
}
