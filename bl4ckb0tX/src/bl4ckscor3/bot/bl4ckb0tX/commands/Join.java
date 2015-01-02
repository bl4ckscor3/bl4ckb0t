package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Join implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception
	{
		if(Utilities.isValidUser(event))
		{
			String[] args = Utilities.toArgs(event.getMessage());	

			if(args.length == 1)
				Utilities.respond(event, "you need to specify a channel!", true);
			else if(args.length == 2)
			{
				if(args[1].equals("d"))
				{
					Utilities.chanMsg(event, "I will join my default channels now.");
					
					for(String s : Utilities.getDefaultChans())
					{
						Core.bot.sendIRC().joinChannel(s);
					}
					
					return;
				}
				
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
				Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
		}
		else
			Utilities.sorry(event);
	}

	@Override
	public String getAlias()
	{
		return "join";
	}

	@Override
	public String getSyntax()
	{
		return "join <channel>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"join <channel> || The bot joins into the given channel if it's not already in there."};
	}

	@Override
	public String getNotes()
	{
		return "Only useable by OPs.";
	}
}
