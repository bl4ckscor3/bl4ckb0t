package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.Event;

public interface Command<E extends Event>
{
	public void exe(E event) throws Exception;

	public String getAlias();
}
