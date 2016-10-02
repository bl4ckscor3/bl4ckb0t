package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;

public class PrivateLeave extends BasePrivateCommand
{
	@Override
	public void exe(PrivateMessageEvent event, String[] args)
	{
		Core.bot.leaveChannel(args[0].startsWith("#") ? args[0] : "#" + args[0]);
	}

	@Override
	public String getMainAlias()
	{
		return "leave";
	}
	

	
	@Override
	public String getConfigEntry()
	{
		return "privateleave";
	}
}
