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

public class Disable implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{	
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length > 2)
			throw new IncorrectCommandExecutionException(getAlias());

		if(args.length == 1)
		{
			if(CMDListener.enabled)
			{
				CMDListener.enabled = false;
				Utilities.chanMsg(event, L10N.getString("disable.success"));

				if(Utilities.getJoinedChannels(true).length == 1)
					return;

				for(String s : Utilities.getJoinedChannels(true))
				{
					if(!s.equalsIgnoreCase(event.getChannel().getName()))
						Core.bot.sendCustomMessage(s, L10N.getString("disable.notify"));
				}
			}
			else
				Utilities.chanMsg(event, L10N.getString("disable.alreadyDisabled"));
		}
		else 
		{
			if(!CMDListener.channelStates.containsKey(args[1]))
			{
				CMDListener.channelStates.put(args[1], false);
				Utilities.chanMsg(event, L10N.getString("disable.success"));
			}
			else
			{
				if(CMDListener.channelStates.get(args[1]))
				{
					CMDListener.channelStates.put(args[1], false);
					Core.bot.sendCustomMessage(args[1], L10N.getString("disable.success"));
				}
				else
					Core.bot.sendCustomMessage(args[1], L10N.getString("disable.alreadyDisabled"));
			}
		}
	}

	@Override
	public String getAlias()
	{
		return "disable";
	}

	@Override
	public String getSyntax()
	{
		return "-disable [channel]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-disable || " + L10N.getString("disable.explanation")};
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
