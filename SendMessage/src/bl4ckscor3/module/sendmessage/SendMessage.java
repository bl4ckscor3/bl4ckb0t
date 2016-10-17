package bl4ckscor3.module.sendmessage;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class SendMessage extends Module
{
	private L10N l10n;
	
	public SendMessage(String name)
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
			String msg = "";
			
			for(int i = 1; i < args.length; i++)
			{
				msg += args[i] + " ";
			}

			msg = msg.substring(0, msg.length() - 1);
			Utilities.sendMessage(args[0], msg);
		}

		@Override
		public String getMainAlias()
		{
			return "send";
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}