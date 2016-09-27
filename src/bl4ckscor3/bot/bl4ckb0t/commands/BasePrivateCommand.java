package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.PrivateMessageEvent;

public abstract class BasePrivateCommand implements ICommand<PrivateMessageEvent>
{
	/**
	 * The prefix which triggers the command
	 * Note: A space should be appended if neccessary
	 */
	@Override
	public abstract String getMainAlias();
}
