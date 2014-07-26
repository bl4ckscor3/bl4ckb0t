package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import com.google.common.collect.ImmutableSortedSet;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Join implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception
	{
		if(Utilities.validUser(event))
		{
			String[] args = Utilities.toArgs(event.getMessage());	

			if(args.length == 1)
				Utilities.respond(event, "you need to specify a channel!", true);
			else if(args.length == 2)
			{
				if(!args[1].startsWith("#"))
				{
					Utilities.chanMsg(event, "This is not a channel. Channel names always start with a hashtag (#)");
					return;
				}
				
				if(!Utilities.hasJoinedChannel(args))
				{
					Utilities.chanMsg(event, "I will join the channel " + Colors.BOLD + args[1]);
					Core.bot.sendIRC().joinChannel(args[1]);
				}
				else
					Utilities.chanMsg(event, "I already joined that channel.");
			}
			else
				Utilities.respond(event, "I don't know what you did, but you just need to specify a channel, nothing else...", true);
		}
		else
			Utilities.sorry(event);
	}

	@Override
	public String getAlias()
	{
		return "join";
	}
}
