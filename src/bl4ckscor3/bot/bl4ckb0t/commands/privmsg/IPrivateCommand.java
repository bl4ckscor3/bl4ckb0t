package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.Event;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;

public interface IPrivateCommand<E extends Event<Bot>>
{
	public void exe(E event) throws Exception;
	
	public String getAlias();
}
