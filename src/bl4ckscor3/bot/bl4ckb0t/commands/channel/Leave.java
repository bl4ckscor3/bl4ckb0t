package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Leave implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());	

		if(args.length == 1)
			Core.bot.leaveChannel(event.getChannel().getName());
		else if(args.length == 2)
		{
			if(args[1].equals("d"))
			{
				Utilities.chanMsg(event, L10N.getString("leave.success.default"));

				for(String s : Lists.getDefaultChans())
				{	
					Core.bot.leaveChannel(s);
				}
			}

			if(!args[1].startsWith("#"))
				args[1] = "#" + args[1];

			if(Utilities.hasJoinedChannel(args[1]))
			{
				Utilities.chanMsg(event, L10N.getString("leave.success.normal"));
				Core.bot.leaveChannel(args[1]);
			}
			else
				Utilities.chanMsg(event, L10N.getString("leave.notJoined"));
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias()
	{
		return "leave";
	}

	@Override
	public String getSyntax()
	{
		return "-leave [" + L10N.getString("cmd.help.channel") + "]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-leave || " + L10N.getString("leave.explanation.1"),
				"-leave <" + L10N.getString("cmd.help.channel") + "> || " + L10N.getString("leave.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return L10N.getString("notes.onlyOp");
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
}
