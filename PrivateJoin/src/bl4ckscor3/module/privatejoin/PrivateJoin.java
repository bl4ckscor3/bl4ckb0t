package bl4ckscor3.module.privatejoin;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;

public class PrivateJoin extends Module
{
	private L10N l10n;
	
	public PrivateJoin(String name)
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
				l10n.translate("explanation.1", channel),
				l10n.translate("explanation.2", channel)
		};
	}
	
	public class PrivateCommand extends BasePrivateCommand
	{
		@Override
		public void exe(PrivateMessageEvent event, String cmdName, String[] args) throws Exception
		{
			if(args.length > 1)
				Core.bot.joinChannelWithPassword(args[0], args[1]);
			else
				Core.bot.joinChannel(args[0]);
		}

		@Override
		public String getMainAlias()
		{
			return "join";
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}