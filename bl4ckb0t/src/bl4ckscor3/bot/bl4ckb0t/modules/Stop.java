package bl4ckscor3.bot.bl4ckb0t.modules;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.Modules;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Stop extends Module
{
	public Stop(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command(this));
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				Core.l10n.translate("stop.explanation.1", channel),
				Core.l10n.translate("stop.explanation.2", channel)
		};
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
	
	public class Command extends BaseChannelCommand
	{
		public Module module;

		public Command(Module m)
		{
			module = m;
		}

		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			if(args.length == 1)
			{
				switch(args[0])
				{
					case "yes":
						Core.bot.quit("Bye");
						Lists.clearAll();
						Modules.modules.clear();
						Logging.info("Creating new bot");
						Core.createBot(Core.wasStartedAsWIP); //creating another instance
						break;
					case "no":
						Core.bot.quit("Bye");
						Logging.info("Bot stopped!");
						Logging.disable();
						System.exit(0);
						break;
					default:
						Utilities.sendHelp(module, event.getUser().getNick(), event.getChannel().getName());
				}
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), event.getChannel().getName());
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"stop"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return Core.l10n.translate("stop.syntax", channel);
		}
	}
}
