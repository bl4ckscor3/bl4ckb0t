package bl4ckscor3.module.ping;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Ping extends Module
{
	private L10N l10n;
	
	public Ping(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new ChannelCommand());
		getBuilder().registerPrivateCommand(this, new PrivateCommand());
		l10n = new L10N(this, loader);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation.1", channel),
				l10n.translate("explanation.2", channel)
		};
	}
	
	public class ChannelCommand extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			if(cmdName.equals(getMainAlias()))
				Utilities.sendMessage(event.getChannel().getName(), "Pong!");
			else
				Utilities.sendMessage(event.getChannel().getName(), "Ping!");
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"ping", "pong"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax.1", channel);
		}
	}
	
	public class PrivateCommand extends BasePrivateCommand
	{
		@Override
		public void exe(PrivateMessageEvent event, String cmdName, String[] args) throws Exception
		{
			Utilities.sendMessage(event.getUser().getNick(), "Pong!");
		}

		@Override
		public String getMainAlias()
		{
			return "ping";
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax.2", channel);
		}
	}
}