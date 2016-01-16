package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

public class PrivateStop implements IPrivateCommand<PrivateMessageEvent<Bot>>
{
	@Override
	public void exe(PrivateMessageEvent<Bot> event, String[] args) throws Exception
	{
		if(args.length == 2)
		{
			switch(args[1])
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
					throw new IncorrectCommandExecutionException(getAlias());
			}
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias()
	{
		return "stop";
	}
}
