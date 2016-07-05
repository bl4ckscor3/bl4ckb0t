package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Startup;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Update extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		String channel = event.getChannel().getName();
		
		Lists.clearAll();
		Changelog.versions.clear();
		Startup.callMethods();
		Utilities.sendMessage(channel, L10N.getString("update.success", channel));
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"update"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-update";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-update || " + L10N.getString("update.usage", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("notes.onlyOp", channel);
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
}
