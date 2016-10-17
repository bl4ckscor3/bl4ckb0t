package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.Event;

public interface ICommand<E extends Event>
{
	/**
	 * What happens when the command gets executed
	 * @param event The event from which this command got executed
	 * @param cmdName The command itself
	 * @param args The arguments of the command
	 */
	public void exe(E event, String cmdName, String[] args) throws Exception;

	/**
	 * How the command can be used, gets shown in the help menu
	 */
	public String getSyntax(String channel);
	
	/**
	 * The prefix which triggers the command
	 */
	public String getMainAlias();
}
