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
		String channel = event.getChannel().getName();
		
		if(args.length == 0)
			Core.bot.leaveChannel(channel);
		else if(args.length == 1)
		{
			if(args[0].equals("d"))
			{
				for(String s : Lists.getDefaultChans())
				{	
					Core.bot.leaveChannel(s);
				}
			}

			if(!args[0].startsWith("#"))
				args[0] = "#" + args[0];

			if(Utilities.hasJoinedChannel(args[0]))
				Core.bot.leaveChannel(args[0]);
			else
				Utilities.sendMessage(channel, L10N.getString("leave.notJoined", channel));
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
	public String getSyntax(String channel)
	{
		return "-leave [" + L10N.getString("cmd.help.channel", channel) + "]";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-leave || " + L10N.getString("leave.explanation.1", channel),
				"-leave <" + L10N.getString("cmd.help.channel", channel) + "> || " + L10N.getString("leave.explanation.2", channel)
		};
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
