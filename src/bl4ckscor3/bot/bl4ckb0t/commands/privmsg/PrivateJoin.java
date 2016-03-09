package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;

public class PrivateJoin extends BasePrivateCommand<PrivateMessageEvent>
{
	@Override
	public void exe(PrivateMessageEvent event, String[] args)
	{
		if(args.length > 2)
			Core.bot.joinChannelWithPassword(args[1], args[2]);
		else
			Core.bot.joinChannel(args[1]);
	}

	@Override
	public String getMainAlias()
	{
		return "join ";
	}

	
	@Override
	public String getConfigEntry()
	{
		return "privatejoin";
	}
}
