package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Startup;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Update extends BaseCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Lists.clearAll();
		Changelog.versions.clear();
		Startup.callMethods();
		Utilities.chanMsg(event, L10N.getString("update.success", event));
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"update"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-update";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{"-update || " + L10N.getString("update.usage", event)};
	}

	@Override
	public String getNotes(MessageEvent event)
	{
		return L10N.getString("notes.onlyOp", event);
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
}
