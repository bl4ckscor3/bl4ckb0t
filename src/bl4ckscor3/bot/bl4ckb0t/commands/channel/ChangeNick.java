package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;

public class ChangeNick extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws IncorrectCommandExecutionException, MalformedURLException, IOException
	{
		if(args.length == 2)
		{
			if(!args[1].equalsIgnoreCase("d"))
				Core.bot.sendIRC().changeNick(args[1]);
			else
				Core.bot.sendIRC().changeNick("bl4ckb0t");
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
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-changenick <" + L10N.getString("changenick.newName", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-changenick <" + L10N.getString("changenick.newName", event) + "> ||" + L10N.getString("changenick.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("changenick.notes", event);
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
}
