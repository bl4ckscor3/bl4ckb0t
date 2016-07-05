package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Action extends BasePrivateCommand
{
	@Override
	public void exe(PrivateMessageEvent event, String[] args)
	{
		String msg = "";
		
		if(!args[0].startsWith("#"))
		{
			Utilities.sendMessage(event.getUser().getNick(), "Channel names start with #. Please use the following syntax: * #<channelName> <message>");
			return;
		}
		
		for(int i = 1; i < args.length; i++)
		{
			msg += args[i] + " ";
		}

		msg = msg.substring(0, msg.length() - 1);
		Core.bot.action(args[0], msg);
	}

	@Override
	public String getMainAlias()
	{
		return "*";
	}
	
	@Override
	public String getConfigEntry()
	{
		return "action";
	}
}
