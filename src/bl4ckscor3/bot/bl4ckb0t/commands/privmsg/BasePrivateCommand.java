package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.Event;

import bl4ckscor3.bot.bl4ckb0t.Core;

public abstract class BasePrivateCommand<E extends Event>
{
	/**
	 * What happens when the command gets executed
	 */
	public abstract void exe(E event, String[] args) throws Exception;
	
	/**
	 * The prefix which triggers the command
	 * Note: A space should be appended if neccessary
	 */
	public abstract String getAlias();
	
	/**
	 * Defines what the command is represented by in the config, instead of using the alias there to be more readable
	 */
	public abstract String getConfigEntry();

	/**
	 * Checks wether the command is enabled in the config file
	 */
	public final boolean isEnabled()
	{
		return Core.bot.getConfig().isEnabled(getConfigEntry());
	}
}
