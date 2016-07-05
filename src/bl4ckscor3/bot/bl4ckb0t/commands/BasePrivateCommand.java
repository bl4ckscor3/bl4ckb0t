package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;

public abstract class BasePrivateCommand implements ICommand<PrivateMessageEvent>
{
	/**
	 * The prefix which triggers the command
	 * Note: A space should be appended if neccessary
	 */
	public abstract String getMainAlias();
	
	/**
	 * Defines what the command is represented by in the config, instead of using the alias there to be more readable
	 */
	public abstract String getConfigEntry();

	@Override
	public final boolean isEnabled()
	{
		return Core.bot.getConfig().isEnabled(getConfigEntry());
	}
}
