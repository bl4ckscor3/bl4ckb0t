package bl4ckscor3.bot.bl4ckb0t.modules;

import java.net.URLClassLoader;
import java.util.ArrayList;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class Changelog extends Module
{
	public static final ArrayMap<String, ArrayList<String>> versions = new ArrayMap<String, ArrayList<String>>();
	
	public Changelog(String name)
	{
		super(name);
	}
	
	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command());
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				Core.l10n.translate("changelog.explanation.1", channel),
				Core.l10n.translate("changelog.explanation.2", channel)
		};
	}
	
	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args)
		{
			String channel = event.getChannel().getName();
			
			if(args.length == 1)
			{
				if(!versions.containsKey(args[0]))
				{
					Utilities.sendMessage(channel, Core.l10n.translate("changelog.notFound", channel));
					return;
				}

				for(String s : versions.get(args[0]))
				{
					Utilities.sendMessage(channel, s);
				}

				return;
			}

			if(!Core.wasStartedAsWIP)
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
			return Core.l10n.translate("changelog.syntax", channel);
		}
	}
}
