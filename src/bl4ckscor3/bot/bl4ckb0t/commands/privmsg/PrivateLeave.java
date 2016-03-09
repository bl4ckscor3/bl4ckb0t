package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;

public class PrivateLeave extends BasePrivateCommand<PrivateMessageEvent>
{
	@Override
	public void exe(PrivateMessageEvent event, String[] args)
	{
		Core.bot.leaveChannel(args[1].startsWith("#") ? args[1] : "#" + args[1]);
	}

	@Override
	public String getMainAlias()
	{
		return "leave ";
	}
	

	
	@Override
	public String getConfigEntry()
	{
		return "privateleave";
	}
}
