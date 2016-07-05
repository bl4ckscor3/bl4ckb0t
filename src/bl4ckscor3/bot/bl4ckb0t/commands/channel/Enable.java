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

public class Enable extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		if(args.length > 1)
			throw new IncorrectCommandExecutionException(getMainAlias());

		String channel = event.getChannel().getName();
		
		if(args.length == 0)
		{
			if(!Core.bot.isEnabled())
			{
				Core.bot.enable();
				Utilities.sendMessage(channel, L10N.getString("enable.success", channel));
				Logging.info("Enabled bot globally.");
				Core.bot.sendRaw().rawLine("AWAY");
				Logging.enable();
			}
			else
				Utilities.sendMessage(channel, L10N.getString("enable.alreadyEnabled", channel));
		}
		else
		{
			ArrayMap<String,Boolean> states = Core.bot.getChannelStates();
			
			if(!states.containsKey(args[0].replace("#", "")))
			{
				states.put(args[0].replace("#", ""), true);
				Utilities.sendMessage(channel, L10N.getString("enable.success", channel));
				Logging.info("Enabled bot in " + args[0]);
			}
			else
			{
				if(!states.get(args[0].replace("#", "")))
				{
					states.put(args[0].replace("#", ""), true);
					Utilities.sendMessage(args[0], L10N.getString("enable.success", channel));
					Logging.info("Enabled bot in " + args[0]);
				}
				else
					Utilities.sendMessage(args[0], L10N.getString("enable.alreadyEnabled", channel));
			}
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"enable"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-enable [" + L10N.getString("cmd.help.channel", channel) + "]";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-enable || " + L10N.getString("enable.explanation.1", channel),
				"-enable <" + L10N.getString("cmd.help.channel", channel) + ">|| " + L10N.getString("enable.explanation.2", channel)
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
