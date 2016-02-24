package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class Disable extends BaseCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{	
		if(args.length > 2)
			throw new IncorrectCommandExecutionException(getMainAlias());

		if(args.length == 1)
		{
			if(Core.bot.isEnabled())
			{
				Core.bot.disable();
				Utilities.chanMsg(event, L10N.getString("disable.success", event));
				Logging.info("Disabled bot globally.");
				Core.bot.sendRaw().rawLine("AWAY :Disabled");
				Logging.disable();
			}
			else
				Utilities.chanMsg(event, L10N.getString("disable.alreadyDisabled", event));
		}
		else 
		{
			ArrayMap<String,Boolean> states = Core.bot.getChannelStates();
			
			if(!Core.bot.getChannelStates().containsKey(args[1].replace("#", "")))
			{
				states.put(args[1].replace("#", ""), false);
				Utilities.chanMsg(event, L10N.getString("disable.success", event));
				Logging.info("Disabled bot in " + args[1]);
			}
			else
			{
				if(states.get(args[1].replace("#", "")))
				{
					states.put(args[1].replace("#", ""), false);
					Core.bot.sendCustomMessage(args[1], L10N.getString("disable.success", event));
					Logging.info("Disabled bot in " + args[1]);
				}
				else
					Core.bot.sendCustomMessage(args[1], L10N.getString("disable.alreadyDisabled", event));
			}
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"disable"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-disable [" + L10N.getString("cmd.help.channel", event) + "]";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{
				"-disable || " + L10N.getString("disable.explanation.1", event),
				"-disable <" + L10N.getString("cmd.help.channel", event) + "> || " + L10N.getString("disable.explanation.2", event)
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
