package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.Event;

import bl4ckscor3.bot.bl4ckb0t.Bot;

public interface IPrivateCommand<E extends Event<Bot>>
{
	/**
	 * What happens when the command gets executed
	 */
	public void exe(E event, String[] args) throws Exception;
	
	/**
	 * The prefix which triggers the command
	 * Note: A space should be appended if neccessary
	 */
	public String getAlias();
}
