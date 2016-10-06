package bl4ckscor3.module.trello;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Trello extends Module
{
	private L10N l10n;
	
	public Trello(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command());
		l10n = new L10N(this, loader);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			Utilities.sendMessage(event.getChannel().getName(), "https://trello.com/b/039j1jFa/bl4ckb0t");
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"trello", "tr"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}