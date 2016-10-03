package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.PrivateMessageEvent;

public abstract class BasePrivateCommand implements ICommand<PrivateMessageEvent>
{
	@Override
	public abstract String getMainAlias();
	
	@Override
	public abstract String getSyntax(String channel);
}
