package bl4ckscor3.bot.bl4ckb0t.modules;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
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
	public void setup()
	{
		getBuilder().registerChannelCommand(this, new Command());
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"Update usage"}; //TODO: L10N
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
			Utilities.sendMessage(event.getChannel().getName(), "The lists were updated successfully."); //TODO: L10N
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"update"};
		}

		@Override
		public String getSyntax()
		{
			return Core.bot.getCmdPrefix() + "update";
		}
	}
}
