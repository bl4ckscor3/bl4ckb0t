package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.Event;

public interface ICommand<E extends Event>
{
	/**
	 * What happens when the command gets executed
	 */
	public void exe(E event, String[] args) throws Exception;
	
	/**
	 * The prefix which triggers the command
	 */
	public String getMainAlias();
	
	/**
	 * Checks wether the command is enabled in the config file
	 */
	public boolean isEnabled();
}
