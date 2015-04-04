package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Action implements IPrivateCommand<PrivateMessageEvent<Bot>>
{
	@Override
	public void exe(PrivateMessageEvent<Bot> event)
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String msg = "";
		
		if(!args[1].startsWith("#"))
		{
			Core.bot.sendCustomMessage(event.getUser().getNick(), L10N.strings.getString("action.noHashtag"));
			return;
		}
		
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
