package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;

public abstract class BaseCommand<E extends Event<Bot>>
{
	/**
	 * What happens when the command gets executed
	 */
	public abstract void exe(E event) throws Exception;

	/**
	 * The command exclusive the prefix
	 */
	public abstract String getAlias();

	/**
	 * How the command can be used. Gets shown in the help menu.
	 * @param event The MessageEvent which contains the channel the help command was used in
	 */
	public abstract String getSyntax(MessageEvent<Bot> event);
	
	/**
	 * Explanation of the command. Gets shown in the help menu.
	 * @param event The MessageEvent which contains the channel the help command was used in
	 */
	public abstract String[] getUsage(MessageEvent<Bot> event);
	
	/**
	 * Anything special the user needs to know about the command. Gets shown in the help menu.
	 * @param event The MessageEvent which contains the channel the help command was used in
	 */
	public String getNotes(MessageEvent<Bot> event)
	{
		return null;
	}
	
	/**
	 * Which user can issue this command? 3 = Only valid users | 2 = Valid and allowed users | 1 = Everybody
	 */
	public int getPermissionLevel()
	{
		return 1;
	}
}