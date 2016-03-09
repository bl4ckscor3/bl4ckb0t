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
		if(args.length == 2)
		{
			if(args[1].equals(Core.bot.getNick()))
				Utilities.chanMsg(event, L10N.getString("changenick.same", event));

			if(!args[1].equalsIgnoreCase("d"))
				Core.bot.sendIRC().changeNick(args[1]);
			else
				Core.bot.sendIRC().changeNick("bl4ckb0t");
			
			Thread.sleep(1000);

			if(isInUse)
			{
				isInUse = false;
				Utilities.chanMsg(event, L10N.getString("changenick.inUse", event));
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
	public String getSyntax(MessageEvent event)
	{
		return "-changenick <" + L10N.getString("changenick.newName", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{"-changenick <" + L10N.getString("changenick.newName", event) + "> ||" + L10N.getString("changenick.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent event)
	{
		return L10N.getString("changenick.notes", event);
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
}
