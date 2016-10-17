package bl4ckscor3.module.twitter;

import java.net.URLClassLoader;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Twitter extends Module
{
	private L10N l10n;
	
	public Twitter(String name)
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
		private Module module;
		
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
				try
				{
					Jsoup.connect("http://www.twitter.com/" + args[0]).get();
					Utilities.sendMessage(channel, "http://www.twitter.com/" + args[0]);
				}
				catch(HttpStatusException e)
				{
					if(e.getStatusCode() == 404)
						Utilities.sendMessage(channel, l10n.translate("error", channel));
				}
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"twitter", "tw"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}