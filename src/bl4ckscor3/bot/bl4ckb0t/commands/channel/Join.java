package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Join extends BaseCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		if(args.length == 2)
		{
			if(args[1].equals("d"))
			{
				Core.bot.joinDefaults();
				return;
			}

			if(!args[1].startsWith("#"))
				args[1] = "#" + args[1];

			if(!Utilities.hasJoinedChannel(args[1]))
				Core.bot.joinChannel(args[1]);
			else
				Utilities.chanMsg(event, L10N.getString("join.alreadyJoined", event));
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"join"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-join <" + L10N.getString("cmd.help.channel", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{"-join <" + L10N.getString("cmd.help.channel", event) + "> || " + L10N.getString("join.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent event)
	{
		return L10N.getString("notes.onlyOp", event);
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
}
