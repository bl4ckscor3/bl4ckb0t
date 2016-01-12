package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;

public class Action implements IPrivateCommand<PrivateMessageEvent<Bot>>
{
	@Override
	public void exe(PrivateMessageEvent<Bot> event, String[] args)
	{
		String msg = "";
		
		if(!args[1].startsWith("#"))
		{
			Core.bot.sendCustomMessage(event.getUser().getNick(), "Channel names start with a hashtag (#). Please use the following syntax: * #<channelName> <message>");
			return;
		}
		
		for(int i = 2; i < args.length; i++)
		{
			msg += args[i] + " ";
		}

		msg = msg.substring(0, msg.length() - 1);
		Core.bot.action(args[1], msg);
	}

	@Override
	public String getAlias()
	{
		return "* ";
	}
}
