package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.Core;

public class UserMsg implements IPrivateCommand<PrivateMessageEvent<Bot>>
{
	@Override
	public void exe(PrivateMessageEvent<Bot> event, String[] args) throws MalformedURLException, IOException
	{
		String msg = "";
		
		for(int i = 2; i < args.length; i++)
		{
			msg += args[i] + " ";
		}

		msg = msg.substring(0, msg.length() - 1);
		Core.bot.sendCustomMessage(args[1], msg);
	}

	@Override
	public String getAlias()
	{
		return "msg ";
	}
}
