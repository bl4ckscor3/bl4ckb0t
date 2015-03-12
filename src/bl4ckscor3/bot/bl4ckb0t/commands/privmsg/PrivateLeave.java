package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class PrivateLeave implements IPrivateCommand<PrivateMessageEvent<Bot>>
{
	@Override
	public void exe(PrivateMessageEvent<Bot> event)
	{
		String[] args = Utilities.toArgs(event.getMessage());
		
		Core.bot.sendRaw().rawLine("PART " + (args[1].startsWith("#") ? args[1] : "#" + args[1]) + " :" + L10N.strings.getString("channel.part"));
	}

	@Override
	public String getAlias()
	{
		return "leave ";
	}
}
