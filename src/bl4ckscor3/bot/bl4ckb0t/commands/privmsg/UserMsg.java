package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class UserMsg implements IPrivateCommand<PrivateMessageEvent<Bot>>
{
	@Override
	public void exe(PrivateMessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String msg = "";
		
		for(int i = 2; i < args.length; i++)
		{
			msg += args[i] + " ";
		}

		msg = msg.substring(0, msg.length() - 1);
		
		if(Utilities.isValidUser(event))
		{
			List<String> users = (ArrayList<String>) Lists.getValidUsers();
			
			users.remove(event.getUser().getNick());
			
			for(String user : users)
			{
				Core.bot.sendCustomMessage(user, event.getUser().getNick() + " to " + args[1] + ": " + msg);
			}
		}
		
		Core.bot.sendCustomMessage(args[1], msg);
	}

	@Override
	public String getAlias()
	{
		return "msg ";
	}
}
