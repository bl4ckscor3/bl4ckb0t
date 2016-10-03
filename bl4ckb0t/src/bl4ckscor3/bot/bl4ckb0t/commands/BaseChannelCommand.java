package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.MessageEvent;

public abstract class BaseChannelCommand implements ICommand<MessageEvent>
{
	/**
	 * The lines that can be used to trigger the command exclusive the prefix. The first alias is the main one
	 */
	public abstract String[] getAliases();

	@Override
	public abstract String getSyntax(String channel);
	
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
	 * @param check The String to check, WITHOUT the command prefix
	 * @return Wether the checked String is a valid alias or not
	 */
	public final boolean isValidAlias(String check)
	{
		for(String s : getAliases())
		{
			if(check.equals(s))
				return true;
		}
		
		return false;
	}
}
