package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.Core;

public class PrivateLeave extends BasePrivateCommand<PrivateMessageEvent<Bot>>
{
	@Override
	public void exe(PrivateMessageEvent<Bot> event, String[] args)
	{
		Core.bot.leaveChannel(args[1].startsWith("#") ? args[1] : "#" + args[1]);
	}

	@Override
	public String getAlias()
	{
		return "leave ";
	}
	

	
	@Override
	public String getConfigEntry()
	{
		return "privateleave";
	}
}
