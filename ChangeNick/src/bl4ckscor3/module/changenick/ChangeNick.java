package bl4ckscor3.module.changenick;

import java.net.URLClassLoader;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.NickAlreadyInUseEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ChangeNick extends Module
{
	private boolean isInUse = false;
	private L10N l10n;
	private Listener listener;
	
	public ChangeNick(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command(this));
		getBuilder().addListener(listener = new Listener());
		l10n = new L10N(this, loader);
	}
	
	@Override
	public void onDisable()
	{
		getBuilder().removeListener(listener);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation.1", channel),
				l10n.translate("explanation.2", channel)
		};
	}
	
	@Override
	public int getPermissionLevel()
	{
		return 2;
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
			String channel = event.getChannel().getName();
			
			if(args.length == 1)
			{
				if(args[0].equals(Core.bot.getNick()))
					Utilities.sendMessage(channel, l10n.translate("same", channel));

				if(!args[0].equalsIgnoreCase("d"))
					Core.bot.sendIRC().changeNick(args[0]);
				else
					Core.bot.sendIRC().changeNick("bl4ckb0t");
				
				Thread.sleep(2000);

				if(isInUse)
				{
					isInUse = false;
					Utilities.sendMessage(channel, l10n.translate("inUse", channel));
				}
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"changenick", "cn"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
	
	public class Listener extends ListenerAdapter
	{
		@Override
		public void onNickAlreadyInUse(NickAlreadyInUseEvent event) throws Exception
		{
			try
			{
				isInUse = true;
			}
			catch(Exception e)
			{
				Logging.stackTrace(e);
			}
		}
	}
}