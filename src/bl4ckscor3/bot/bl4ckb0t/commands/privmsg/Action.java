package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Action implements IPrivateCommand<PrivateMessageEvent>
{
	@Override
	public void exe(PrivateMessageEvent event)
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String msg = "";
		
		for(int i = 2; i < args.length; i++)
		{
			msg += args[i] + " ";
		}

		msg = msg.substring(0, msg.length() - 1);
		Core.bot.sendIRC().action(args[1], msg);
	}

	@Override
	public String getAlias()
	{
		return "* ";
	}
}
