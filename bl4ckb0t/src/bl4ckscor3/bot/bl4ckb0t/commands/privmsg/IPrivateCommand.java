package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.Event;

public interface IPrivateCommand<E extends Event>
{
	public void exe(E event);
	
	public String getAlias();
}
