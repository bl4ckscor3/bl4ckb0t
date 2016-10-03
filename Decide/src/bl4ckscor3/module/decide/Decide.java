package bl4ckscor3.module.decide;

import java.net.URLClassLoader;
import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Decide extends Module
{
	private L10N l10n;
	
	public Decide(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command(this));
		l10n = new L10N(this, loader);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	@Override
	public String getNotes(String channel)
	{
		return l10n.translate("notes", channel);
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
			
			if(args.length >= 1 && event.getMessage().endsWith("?"))
			{
				int decision = new Random().nextInt(101);
				
				if(decision >= 0 && decision <= 49)
					Utilities.sendMessage(channel, l10n.translate("no", channel));
				else if(decision >= 50 && decision <= 100)
					Utilities.sendMessage(channel, l10n.translate("yes", channel));
				else
					Utilities.sendMessage(channel, l10n.translate("fail", channel) + ": " + decision);
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"decide"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}