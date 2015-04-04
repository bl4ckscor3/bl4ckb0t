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

public class Enable implements ICommand<MessageEvent<Bot>>
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
				if(!Listener.enabled)
				{
					Listener.enabled = true;
					Utilities.chanMsg(event, L10N.strings.getString("enable.success"));

					for(String s : Utilities.getJoinedChannels())
					{
						if(!s.equalsIgnoreCase(event.getChannel().getName()))
							Core.bot.sendCustomMessage(s, L10N.strings.getString("enable.notify"));
					}
				}
				else
					Utilities.chanMsg(event, L10N.strings.getString("enable.alreadyEnabled"));
			}
			else
			{
				if(!Listener.channelStates.containsKey(args[1]))
				{
					Listener.channelStates.put(args[1], true);
					Utilities.chanMsg(event, L10N.strings.getString("enable.success"));
				}
				else
				{
					if(!Listener.channelStates.get(args[1]))
					{
						Listener.channelStates.put(args[1], true);
						Core.bot.sendCustomMessage(args[1], L10N.strings.getString("enable.success"));
					}
					else
						Core.bot.sendCustomMessage(args[1], L10N.strings.getString("enable.alreadyEnabled"));
				}
			}
		}
		else
			Utilities.sorry(event);
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
		return new String[]{"-enable || " + L10N.strings.getString("enable.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("notes.onlyOp");
	}
}
