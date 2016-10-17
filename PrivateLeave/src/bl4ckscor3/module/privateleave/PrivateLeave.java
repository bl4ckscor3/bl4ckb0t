package bl4ckscor3.module.privateleave;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;

public class PrivateLeave extends Module
{
	private L10N l10n;
	
	public PrivateLeave(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerPrivateCommand(this, new PrivateCommand());
		l10n = new L10N(this, loader);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	public class PrivateCommand extends BasePrivateCommand
	{
		@Override
		public void exe(PrivateMessageEvent event, String cmdName, String[] args) throws Exception
		{
			Core.bot.leaveChannel(args[0].startsWith("#") ? args[0] : "#" + args[0]);
		}

		@Override
		public String getMainAlias()
		{
			return "leave";
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}