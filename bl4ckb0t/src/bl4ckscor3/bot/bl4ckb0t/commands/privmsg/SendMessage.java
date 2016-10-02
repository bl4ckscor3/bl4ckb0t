package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class SendMessage extends BasePrivateCommand
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
		Utilities.sendMessage(args[0], msg);
	}

	@Override
	public String getMainAlias()
	{
		return "send";
	}
	
	@Override
	public String getConfigEntry()
	{
		return "sendMessage";
	}
}
