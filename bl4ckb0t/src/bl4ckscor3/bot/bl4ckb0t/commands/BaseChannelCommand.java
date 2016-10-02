package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;

public abstract class BaseChannelCommand implements ICommand<MessageEvent>
{
	/**
	 * The lines that can be used to trigger the command exclusive the prefix. The first alias is the main one.
	 */
	public abstract String[] getAliases();

	/**
	 * How the command can be used. Gets shown in the help menu.
	 * @param event The channel the help command was used in
	 */
	public abstract String getSyntax(String channel);
	
	/**
	 * Explanation of the command. Gets shown in the help menu.
	 * @param event The channel the help command was used in
	 */
	public abstract String[] getUsage(String channel);
	
	/**
	 * Anything special the user needs to know about the command. Gets shown in the help menu.
	 * @param event The channel the help command was used in
	 */
	public String getNotes(String channel)
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
	@Override
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
		if(!Core.bot.getConfig().isEnabled("allowCommandAliases"))
			return check.replace("-", "").equals(getMainAlias());
		
		for(String s : getAliases())
		{
			if(check.replace("-", "").equals(s))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks wether the command is enabled in the config file
	 */
	@Override
	public final boolean isEnabled()
	{
		return Core.bot.getConfig().isEnabled(getMainAlias());
	}
}
