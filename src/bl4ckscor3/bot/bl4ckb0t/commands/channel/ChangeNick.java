package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ChangeNick extends BaseChannelCommand<MessageEvent>
{
	public static boolean isInUse = false;
	
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException, MalformedURLException, IOException, InterruptedException
	{
		String channel = event.getChannel().getName();
		
		if(args.length == 1)
		{
			if(args[0].equals(Core.bot.getNick()))
				Utilities.sendMessage(channel, L10N.getString("changenick.same", channel));

			if(!args[0].equalsIgnoreCase("d"))
				Core.bot.sendIRC().changeNick(args[0]);
			else
				Core.bot.sendIRC().changeNick("bl4ckb0t");
			
			Thread.sleep(1000);

			if(isInUse)
			{
				isInUse = false;
				Utilities.sendMessage(channel, L10N.getString("changenick.inUse", channel));
			}
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"changenick", "cn"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-changenick <" + L10N.getString("changenick.newName", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-changenick <" + L10N.getString("changenick.newName", channel) + "> ||" + L10N.getString("changenick.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("changenick.notes", channel);
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
}
