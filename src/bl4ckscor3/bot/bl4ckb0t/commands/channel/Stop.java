package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

public class Stop extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IOException, IrcException, IncorrectCommandExecutionException
	{
		if(args.length == 1)
		{
			switch(args[0])
			{
				case "yes":
					Core.bot.stopBotReconnect();
					Core.bot.quit("Bye");
					Logging.info("Creating new bot...");
					Core.createBot(Core.bot.isDevelopment()); //creating another instance
					break;
				case "no":
					Core.bot.stopBotReconnect();
					Core.bot.quit("Bye");
					Logging.info("Bot stopped!");
					Logging.disable();
					break;
				default:
					throw new IncorrectCommandExecutionException(getMainAlias());
			}
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"stop"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-stop <yes|no>";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-stop yes || " + L10N.getString("stop.explanation.1", channel),
				"-stop no || " + L10N.getString("stop.explanation.2", channel)
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
