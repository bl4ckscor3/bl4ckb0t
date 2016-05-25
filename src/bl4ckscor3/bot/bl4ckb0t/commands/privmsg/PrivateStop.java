package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

public class PrivateStop extends BasePrivateCommand<PrivateMessageEvent>
{
	@Override
	public void exe(PrivateMessageEvent event, String[] args) throws Exception
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
	public String getMainAlias()
	{
		return "stop";
	}
	
	@Override
	public String getConfigEntry()
	{
		return "privatestop";
	}
}
