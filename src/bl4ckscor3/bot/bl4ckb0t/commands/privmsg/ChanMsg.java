package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;

public class ChanMsg extends BasePrivateCommand<PrivateMessageEvent>
{
	@Override
	public void exe(PrivateMessageEvent event, String[] args)
	{
		String msg = "";
		
		for(int i = 1; i < args.length; i++)
		{
			msg += args[i] + " ";
		}

		msg = msg.substring(0, msg.length() - 1);
		Core.bot.sendCustomMessage(args[0], msg);
	}

	@Override
	public String getMainAlias()
	{
		return "#";
	}
	
	@Override
	public String getConfigEntry()
	{
		return "chanmsg";
	}
}
