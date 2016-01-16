package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;

public abstract class BaseCommand<E extends Event<Bot>>
{
	/**
	 * What happens when the command gets executed
	 */
	public abstract void exe(E event, String[] args) throws Exception;

	/**
	 * The lines that can be used to trigger the command exclusive the prefix. The first alias is the main one.
	 */
	public abstract String[] getAliases();

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
	 * Which user can issue this command?
	 * 3 = Only valid users
	 * 2 = Valid and allowed users
	 * 1 = Everybody (Default)
	 */
	public int getPermissionLevel()
	{
		return 1;
	}
	
	/*Helpful Methods*/
	
	/**
	 * Gets the main alias of the command
	 */
	public final String getMainAlias()
	{
		return getAliases()[0];
	}
	
	/**
	 * Checks if the String is a valid alias
	 * @param check The String to check
	 * @return Wether the checked String is a valid alias or not
	 */
	public final boolean isValidAlias(String check)
	{
		for(String s : getAliases())
		{
			if(s.equals(check.replace("-", "")))
				return true;
		}
		
		return false;
	}
}
