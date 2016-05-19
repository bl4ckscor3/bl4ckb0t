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
		String channel = event.getChannel().getName();
		
		if(args.length == 1)
		{
			if(!versions.containsKey(args[0]))
			{
				Utilities.sendMessage(channel, L10N.getString("changelog.versionNotFound", channel));
				return;
			}

			for(String s : versions.get(args[0]))
			{
				Utilities.sendMessage(channel, s);
			}

			return;
		}

		if(!Core.bot.isDevelopment())
		{
			for(String s : versions.get(Core.bot.getConfiguration().getVersion()))
			{
				Utilities.sendMessage(channel, s);
			}
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"changelog", "cl"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-changelog [" + L10N.getString("changelog.versionNumber", channel) + "]";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-changelog || " + L10N.getString("changelog.explanation.1", channel),
				"-changelog <" + L10N.getString("changelog.versionNumber", channel) + "> || " + L10N.getString("changelog.explanation.2", channel)
		};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("changelog.notes", channel);
	}
}
