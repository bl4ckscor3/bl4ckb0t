package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ChanMsg implements IPrivateCommand<PrivateMessageEvent<Bot>>
{
	@Override
	public void exe(PrivateMessageEvent<Bot> event)
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String msg = "";
		
		for(int i = 1; i < args.length; i++)
		{
			msg += args[i] + " ";
		}

		msg = msg.substring(0, msg.length() - 1);
		Core.bot.sendCustomMessage(args[0], msg);
	}

	@Override
	public String getAlias()
	{
		return "#";
	}
}
