package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Join extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		if(args.length == 1)
		{
			if(args[0].equals("d"))
			{
				Core.bot.joinDefaults();
				return;
			}

			if(!args[0].startsWith("#"))
				args[0] = "#" + args[0];

			if(!Utilities.hasJoinedChannel(args[1]))
				Core.bot.joinChannel(args[1]);
			else
			{
				String channel = event.getChannel().getName();
				
				Utilities.sendMessage(channel, L10N.getString("join.alreadyJoined", channel));
			}
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
	public String getSyntax(String channel)
	{
		return "-join <" + L10N.getString("cmd.help.channel", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-join <" + L10N.getString("cmd.help.channel", channel) + "> || " + L10N.getString("join.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("notes.onlyOp", channel);
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
}
