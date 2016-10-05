package bl4ckscor3.bot.bl4ckb0t.modules;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Source extends Module
{
	public Source(String name)
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
		return new String[]{Core.l10n.translate("source.explanation", channel)};
	}
	
	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			Utilities.sendMessage(event.getChannel().getName(), "https://github.com/bl4ckscor3/bl4ckb0t");
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"source"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return Core.l10n.translate("source.syntax", channel);
		}
	}
}
