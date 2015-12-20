package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Version extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event)
	{
		Utilities.chanMsg(event, Core.bot.getConfiguration().getVersion());
	}

	@Override
	public String getAlias()
	{
		return "version";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-version";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-version || " + L10N.getString("version.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return null;
	}
	
	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
