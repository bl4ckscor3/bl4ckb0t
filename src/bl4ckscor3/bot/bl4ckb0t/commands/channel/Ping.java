package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;

public class Ping implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws Exception{} //placeholder class to be able to set the help menu...

	@Override
	public String getAlias()
	{
		return "ping";
	}

	@Override
	public String getSyntax()
	{
		return "-ping";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{
				"-ping || " + L10N.getString("ping.explanation")
		};
	}

	@Override
	public String getNotes()
	{
		return L10N.getString("ping.notes");
	}

	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
