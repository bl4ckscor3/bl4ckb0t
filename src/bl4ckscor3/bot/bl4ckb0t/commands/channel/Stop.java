package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Stop extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IOException, IrcException, IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
		{
			switch(args[1])
			{
				case "yes":
					Core.bot.stopBotReconnect();
					Core.bot.quit("Bye");
					Logging.info("Creating new bot...");
					Core.createBot(Core.wasStartedAsWIP); //creating another instance
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
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-stop <yes|no>";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{
				"-stop yes || " + L10N.getString("stop.explanation.1", event),
				"-stop no || " + L10N.getString("stop.explanation.2", event)
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
