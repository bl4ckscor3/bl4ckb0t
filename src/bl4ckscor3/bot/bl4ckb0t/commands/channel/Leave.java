package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Leave extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		if(args.length == 1)
			Core.bot.leaveChannel(event.getChannel().getName());
		else if(args.length == 2)
		{
			if(args[1].equals("d"))
			{
				for(String s : Lists.getDefaultChans())
				{	
					Core.bot.leaveChannel(s);
				}
			}

			if(!args[1].startsWith("#"))
				args[1] = "#" + args[1];

			if(Utilities.hasJoinedChannel(args[1]))
				Core.bot.leaveChannel(args[1]);
			else
				Utilities.chanMsg(event, L10N.getString("leave.notJoined", event));
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"leave"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-leave [" + L10N.getString("cmd.help.channel", event) + "]";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{
				"-leave || " + L10N.getString("leave.explanation.1", event),
				"-leave <" + L10N.getString("cmd.help.channel", event) + "> || " + L10N.getString("leave.explanation.2", event)
		};
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
