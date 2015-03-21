package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.core.Listener;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Disable implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{	
		String[] args = Utilities.toArgs(event.getMessage());

		if(Utilities.isValidUser(event))
		{
			if(args.length > 2)
				throw new IncorrectCommandExecutionException(getAlias());

			if(args.length == 1)
			{
				if(Listener.enabled)
				{
					Listener.enabled = false;
					Utilities.chanMsg(event, L10N.strings.getString("disable.success"));
					System.out.println(Utilities.getJoinedChannels().length);
					if(Utilities.getJoinedChannels().length == 1)
						return;

					for(String s : Utilities.getJoinedChannels())
					{
						if(!s.equalsIgnoreCase(event.getChannel().getName()))
							Core.bot.sendIRC().message(s, L10N.strings.getString("disable.notify"));
					}
				}
				else
					Utilities.chanMsg(event, L10N.strings.getString("disable.alreadyDisabled"));
			}
			else 
			{
				if(!Listener.channelStates.containsKey(args[1]))
				{
					Listener.channelStates.put(args[1], false);
					Utilities.chanMsg(event, L10N.strings.getString("disable.success"));
				}
				else
				{
					if(Listener.channelStates.get(args[1]))
					{
						Listener.channelStates.put(args[1], false);
						Core.bot.sendIRC().message(args[1], L10N.strings.getString("disable.success"));
					}
					else
						Core.bot.sendIRC().message(args[1], L10N.strings.getString("disable.alreadyDisabled"));
				}
			}
		}
		else
			Utilities.sorry(event);
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
		return new String[]{"-disable || " + L10N.strings.getString("disable.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("notes.onlyOp");
	}
}
