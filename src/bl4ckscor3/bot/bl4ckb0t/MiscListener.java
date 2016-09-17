package bl4ckscor3.bot.bl4ckb0t;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.NickAlreadyInUseEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.channel.ChangeNick;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.misc.LinkManager;
import bl4ckscor3.bot.bl4ckb0t.misc.SpellingCorrection;
import bl4ckscor3.bot.bl4ckb0t.misc.YouTubeStats;
import bl4ckscor3.bot.bl4ckb0t.util.Reminder;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class MiscListener extends ListenerAdapter
{
	@Override
	public void onMessage(MessageEvent event) throws Exception
	{
		try
		{
			if(event.getUser().getNick().equals("Maunz") && event.getMessage().toLowerCase().contains("was pushed to the steam client!"))
			{
				Utilities.sendMessage(event.getChannel().getName(), "bl4ckscor3, Vauff, ^");
				return;
			}
			
			if(Utilities.isIgnored(event.getUser().getNick()))
			{
				Logging.warn("Ignoring user " + event.getUser().getNick());
				return;
			}

			String message = event.getMessage();
			String channel = event.getChannel().getName();

			if(Core.bot.getConfig().isEnabled("ping"))
			{
				if(event.getMessage().startsWith("-ping"))
				{
					Utilities.sendMessage(channel, "Pong!");
					return;
				}
				else if(Core.bot.getConfig().isEnabled("allowCommandAliases") && event.getMessage().startsWith("-pong"))
				{
					Utilities.sendMessage(channel, "Ping!");
					return;
				}
			}

			if(message.startsWith(Core.bot.getCmdPrefix()))
				return;

			if(message.startsWith("?enabled"))
			{
				if(message.startsWith("?enabled #") && Core.bot.isEnabled())
					Utilities.sendMessage(channel, message.split(" ")[1] + ": " + Core.bot.getChannelStates().get(message.split(" ")[1].replace("#", "")));
				else
					Utilities.sendMessage(channel, "global: " + Core.bot.isEnabled());
				return;
			}

			if(Core.bot.isEnabled())
			{
				if(Core.bot.getConfig().isEnabled("spellingCorrection"))
				{
					SpellingCorrection.checkForSpellingCorrection(event, message);

					//making sure the above messages dont get added as a latest message
					if(!SpellingCorrection.corrected)
						SpellingCorrection.updateLatestMessage(channel, event.getMessage(), event.getUser().getNick());
					else
						SpellingCorrection.corrected = false;
				}

				//sending a welcome back message
				if(Core.bot.getConfig().isEnabled("showWelcomeBackMsg") && (message.toLowerCase().startsWith("re ") || message.toLowerCase().equals("re")))
					Utilities.sendMessage(channel, "wb, " + event.getUser().getNick());
				//youtube recognition
				else if(Core.bot.getConfig().isEnabled("showYouTubeStats") && (message.contains("www.youtube.com/watch") || message.contains("youtu.be/")))
					YouTubeStats.sendVideoStats(event);
				//checking for urls and sending the title if available
				else
					LinkManager.checkForLinkAndSendTitle(event);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent event) throws Exception
	{
		try
		{
			if(Utilities.isIgnored(event.getUser().getNick()))
			{
				Logging.warn("Ignoring user " + event.getUser().getNick());
				return;
			}

			if(event.getMessage().startsWith(Core.bot.getCmdPrefix()))
				Utilities.sendMessage(event.getUser().getNick(), "Commands can only be sent through channel messages. Use -help in a channel to get more info.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onConnect(ConnectEvent event) throws MalformedURLException, IOException
	{
		try
		{
			Startup.setDefaultChans();
			Logging.info("Joining channels...");
			Core.bot.joinDefaults();
			Logging.info("Bot successfully connected!");
			Reminder.loadReminders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onAction(ActionEvent event) throws Exception
	{
		try
		{
			if(Utilities.isIgnored(event.getUser().getNick()))
			{
				Logging.warn("Ignoring user " + event.getUser().getNick());
				return;
			}

			if(Core.bot.getConfig().isEnabled("shrugs") && event.getAction().startsWith("shrugs"))
				Utilities.sendMessage(event.getChannel().getName(), "¯\\_(ツ)_/¯");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onNickAlreadyInUse(NickAlreadyInUseEvent event) throws Exception
	{
		try
		{
			ChangeNick.isInUse = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
