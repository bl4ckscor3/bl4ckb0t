package bl4ckscor3.bot.bl4ckb0t.commands.modules;

import java.util.ArrayList;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class Changelog extends Module
{
	public static final ArrayMap<String, ArrayList<String>> versions = new ArrayMap<String, ArrayList<String>>();
	
	@Override
	public void setup()
	{
		getBuilder().registerChannelCommand(this, new Command());
	}

	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args)
		{
			if(args.length == 1)
			{
				if(!versions.containsKey(args[0]))
				{
					event.respondChannel("This version could not be found.");
					return;
				}

				for(String s : versions.get(args[0]))
				{
					event.respondChannel(s);
				}

				return;
			}

			if(!Core.bot.isDevelopment())
			{
				for(String s : versions.get(Core.bot.getConfiguration().getVersion()))
				{
					event.respondChannel(s);
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
			return null;
		}

		@Override
		public String[] getUsage(String channel)
		{
			return null;
		}
	}
}
