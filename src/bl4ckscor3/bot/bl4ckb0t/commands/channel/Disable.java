package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class Disable extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{	
		if(args.length > 2)
			throw new IncorrectCommandExecutionException(getMainAlias());

		String channel = event.getChannel().getName();
		
		if(args.length == 1)
		{
			if(Core.bot.isEnabled())
			{
				Core.bot.disable();
				Utilities.sendMessage(channel, L10N.getString("disable.success", channel));
				Logging.info("Disabled bot globally.");
				Core.bot.sendRaw().rawLine("AWAY :Disabled");
				Logging.disable();
			}
			else
				Utilities.sendMessage(channel, L10N.getString("disable.alreadyDisabled", channel));
		}
		else 
		{
			ArrayMap<String,Boolean> states = Core.bot.getChannelStates();
			
			if(!Core.bot.getChannelStates().containsKey(args[1].replace("#", "")))
			{
				states.put(args[1].replace("#", ""), false);
				Utilities.sendMessage(channel, L10N.getString("disable.success", channel));
				Logging.info("Disabled bot in " + args[1]);
			}
			else
			{
				if(states.get(args[1].replace("#", "")))
				{
					states.put(args[1].replace("#", ""), false);
					Utilities.sendMessage(args[1], L10N.getString("disable.success", channel));
					Logging.info("Disabled bot in " + args[1]);
				}
				else
					Utilities.sendMessage(args[1], L10N.getString("disable.alreadyDisabled", channel));
			}
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"disable"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-disable [" + L10N.getString("cmd.help.channel", channel) + "]";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-disable || " + L10N.getString("disable.explanation.1", channel),
				"-disable <" + L10N.getString("cmd.help.channel", channel) + "> || " + L10N.getString("disable.explanation.2", channel)
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
