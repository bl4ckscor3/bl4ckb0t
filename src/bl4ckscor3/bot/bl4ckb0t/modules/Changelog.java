package bl4ckscor3.bot.bl4ckb0t.modules;

import java.util.ArrayList;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class Changelog extends Module
{
	public static final ArrayMap<String, ArrayList<String>> versions = new ArrayMap<String, ArrayList<String>>();
	
	public Changelog(String name)
	{
		super(name);
	}
	
	@Override
	public void setup()
	{
		getBuilder().registerChannelCommand(this, new Command());
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"Changelog command explanation", "Bla 1", "Bla 2"}; //TODO: L10N
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
					event.respondChannel("This version could not be found."); //TODO: L10N
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
		public String getSyntax()
		{
			return Core.bot.getCmdPrefix() + "changelog [version]";
		}
	}
}
