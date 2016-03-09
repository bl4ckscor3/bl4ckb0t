package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class Changelog extends BaseChannelCommand<MessageEvent>
{
	public static final ArrayMap<String, ArrayList<String>> versions = new ArrayMap<String, ArrayList<String>>();

	@Override
	public void exe(MessageEvent event, String[] args) throws MalformedURLException, IOException
	{
		if(args.length == 2)
		{
			if(!versions.containsKey(args[1]))
			{
				Utilities.chanMsg(event, L10N.getString("changelog.versionNotFound", event));
				return;
			}

			for(String s : versions.get(args[1]))
			{
				Utilities.chanMsg(event, s);
			}

			return;
		}

		if(!Core.bot.isDevelopment())
		{
			for(String s : versions.get(Core.bot.getConfiguration().getVersion()))
			{
				Utilities.chanMsg(event, s);
			}
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"changelog", "cl"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-changelog [" + L10N.getString("changelog.versionNumber", event) + "]";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{
				"-changelog || " + L10N.getString("changelog.explanation.1", event),
				"-changelog <" + L10N.getString("changelog.versionNumber", event) + "> || " + L10N.getString("changelog.explanation.2", event)
		};
	}

	@Override
	public String getNotes(MessageEvent event)
	{
		return L10N.getString("changelog.notes", event);
	}
}
