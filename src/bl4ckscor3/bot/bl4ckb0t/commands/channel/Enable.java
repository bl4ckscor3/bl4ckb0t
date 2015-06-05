package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.CMDListener;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Enable implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length > 2)
			throw new IncorrectCommandExecutionException(getAlias());

		if(args.length == 1)
		{
			if(!CMDListener.enabled)
			{
				CMDListener.enabled = true;
				Utilities.chanMsg(event, L10N.getString("enable.success"));

				for(String s : Utilities.getJoinedChannels(true))
				{
					if(!s.equalsIgnoreCase(event.getChannel().getName()))
						Core.bot.sendCustomMessage(s, L10N.getString("enable.notify"));
				}
			}
			else
				Utilities.chanMsg(event, L10N.getString("enable.alreadyEnabled"));
		}
		else
		{
			if(!CMDListener.channelStates.containsKey(args[1]))
			{
				CMDListener.channelStates.put(args[1], true);
				Utilities.chanMsg(event, L10N.getString("enable.success"));
			}
			else
			{
				if(!CMDListener.channelStates.get(args[1]))
				{
					CMDListener.channelStates.put(args[1], true);
					Core.bot.sendCustomMessage(args[1], L10N.getString("enable.success"));
				}
				else
					Core.bot.sendCustomMessage(args[1], L10N.getString("enable.alreadyEnabled"));
			}
		}
	}

	@Override
	public String getAlias()
	{
		return "enable";
	}

	@Override
	public String getSyntax()
	{
		return "-enable";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-enable || " + L10N.getString("enable.explanation")};
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
