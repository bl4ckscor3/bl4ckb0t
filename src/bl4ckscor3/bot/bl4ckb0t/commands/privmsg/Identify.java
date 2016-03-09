package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;

public class Identify extends BasePrivateCommand<PrivateMessageEvent>
{
	@Override
	public void exe(PrivateMessageEvent event, String[] args) throws Exception
	{
		Core.bot.sendIRC().identify(Passwords.NICKSERV.getPassword());
	}

	@Override
	public String getMainAlias()
	{
		return "identify";
	}

	@Override
	public String getConfigEntry()
	{
		return "identify";
	}
}
