package bl4ckscor3.bot.bl4ckb0t.modules;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.Modules;
import bl4ckscor3.bot.bl4ckb0t.Startup;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Update extends Module
{
	public Update(String name)
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
		return new String[]{Core.l10n.translate("update.explanation", channel)};
	}
	
	@Override
	public int getPermissionLevel()
	{
		return 3;
	}
	
	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			Lists.clearAll();
			Changelog.versions.clear();
			Startup.callMethods();
			
			for(Module m : Modules.modules)
			{
				m.onUpdate();
			}
				
			Utilities.sendMessage(event.getChannel().getName(), Core.l10n.translate("update.success", event.getChannel().getName()));
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"update"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return Core.l10n.translate("update.syntax", channel);
		}
	}
}
