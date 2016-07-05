package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;

public class PrivateJoin extends BasePrivateCommand
{
	@Override
	public void exe(PrivateMessageEvent event, String[] args)
	{
		if(args.length > 1)
			Core.bot.joinChannelWithPassword(args[0], args[1]);
		else
			Core.bot.joinChannel(args[0]);
	}

	@Override
	public String getMainAlias()
	{
		return "join";
	}

	
	@Override
	public String getConfigEntry()
	{
		return "privatejoin";
	}
}
