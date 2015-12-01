package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.misc.LinkTitle;
import bl4ckscor3.bot.bl4ckb0t.misc.SpellingCorrection;
import bl4ckscor3.bot.bl4ckb0t.misc.YouTubeStats;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class MiscListener extends ListenerAdapter<Bot>
{
	@Override
	public void onMessage(MessageEvent<Bot> event) throws Exception
	{
		if(Lists.getIgnoredUsers().contains(event.getUser().getNick()))
		{
			Logging.warn("Ignoring user " + event.getUser().getNick());
			return;
		}

		String message = event.getMessage();

		L10N.changeLocalization(L10N.parseLangCode(L10N.chanLangs.get(event.getChannel().getName()), 0), L10N.parseLangCode(L10N.chanLangs.get(event.getChannel().getName()), 1), event.getChannel().getName());

		if(event.getMessage().startsWith("-ping"))
		{
			Utilities.chanMsg(event, "Pong!");
			return;
		}

		if(message.startsWith(CMDListener.cmdPrefix))
			return;

		if(message.startsWith("?enabled"))
		{
			if(message.startsWith("?enabled #") && CMDListener.enabled)
				Utilities.chanMsg(event, message.split(" ")[1] + ": " + CMDListener.channelStates.get(message.split(" ")[1]));
			else
				Utilities.chanMsg(event, "global: " + CMDListener.enabled);
			return;
		}

		if(CMDListener.enabled)
		{
			SpellingCorrection.checkForSpellingCorrection(event, message);

			//making sure the above messages dont get added as a latest message
			if(!SpellingCorrection.corrected)
				SpellingCorrection.updateLatestMessage(event.getChannel().getName(), event.getMessage(), event.getUser().getNick());
			else
				SpellingCorrection.corrected = false;

			//sending a welcome back message
			if(message.toLowerCase().startsWith("re ") || message.toLowerCase().equals("re"))
				Utilities.chanMsg(event, "wb, " + event.getUser().getNick());
			//youtube recognition
			else if(message.contains("www.youtube.com/watch") || message.contains("youtu.be/"))
				YouTubeStats.sendVideoStats(event);
			//checking for urls and sending the title if available
			else
				LinkTitle.checkForLinkAndSendTitle(event);
		}
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent<Bot> event) throws Exception
	{
		if(Lists.getIgnoredUsers().contains(event.getUser().getNick()))
		{
			Logging.warn("Ignoring user " + event.getUser().getNick());
			return;
		}
		
		if(!event.getMessage().startsWith(CMDListener.cmdPrefix))
		{
			for(String user : Lists.getValidUsers())
			{
				Core.bot.sendCustomMessage(user, event.getUser().getNick() + ": " + event.getMessage());
			}
		}
		else
		{
			Utilities.pm(event.getUser().getNick(), L10N.getString("pm.noCommands"));
		}
	}

	@Override
	public void onConnect(ConnectEvent<Bot> event) throws MalformedURLException, IOException
	{
		Startup.setDefaultChans();
		Logging.info("Joining channels...");
		Core.bot.joinDefaults();
		Logging.info("Bot successfully connected!");
	}
}
