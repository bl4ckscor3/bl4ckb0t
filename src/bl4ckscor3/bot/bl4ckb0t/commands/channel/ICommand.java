package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.Event;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;

public interface ICommand<E extends Event<Bot>>
{
	public void exe(E event) throws Exception;

	public String getAlias();

	public String getSyntax();
	
	public String[] getUsage();
	
	public String getNotes();
}
