package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class Enable extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		if(args.length > 2)
			throw new IncorrectCommandExecutionException(getMainAlias());

		if(args.length == 1)
		{
			if(!Core.bot.isEnabled())
			{
				Core.bot.enable();
				Utilities.chanMsg(event, L10N.getString("enable.success", event));
				Logging.info("Enabled bot globally.");
				Core.bot.sendRaw().rawLine("AWAY");
				Logging.enable();
			}
			else
				Utilities.chanMsg(event, L10N.getString("enable.alreadyEnabled", event));
		}
		else
		{
			ArrayMap<String,Boolean> states = Core.bot.getChannelStates();
			
			if(!states.containsKey(args[1].replace("#", "")))
			{
				states.put(args[1].replace("#", ""), true);
				Utilities.chanMsg(event, L10N.getString("enable.success", event));
				Logging.info("Enabled bot in " + args[1]);
			}
			else
			{
				if(!states.get(args[1].replace("#", "")))
				{
					states.put(args[1].replace("#", ""), true);
					Core.bot.sendCustomMessage(args[1], L10N.getString("enable.success", event));
					Logging.info("Enabled bot in " + args[1]);
				}
				else
					Core.bot.sendCustomMessage(args[1], L10N.getString("enable.alreadyEnabled", event));
			}
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"enable"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-enable [" + L10N.getString("cmd.help.channel", event) + "]";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{
				"-enable || " + L10N.getString("enable.explanation.1", event),
				"-enable <" + L10N.getString("cmd.help.channel", event) + ">|| " + L10N.getString("enable.explanation.2", event)
				};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("notes.onlyOp", event);
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
}
