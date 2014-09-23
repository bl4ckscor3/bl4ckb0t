package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.Event;

public interface ICommand<E extends Event>
{
	public void exe(E event) throws Exception;

	public String getAlias();

	public String getSyntax();
	
	public String[] getUsage();
	
	public String getNotes();
}
