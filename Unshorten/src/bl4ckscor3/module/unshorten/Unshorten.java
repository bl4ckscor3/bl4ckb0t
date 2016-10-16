package bl4ckscor3.module.unshorten;

import java.net.URLClassLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Unshorten extends Module
{
	private L10N l10n;

	public Unshorten(String name)
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

			if(args.length == 1)
			{
				Document doc = Jsoup.connect("http://api.unshorten.it?shortURL=" + args[0] + "&apiKey=" + Passwords.UNSHORTENITAPIKEY.getPassword()).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0").get();
				String output = doc.text();
				
				if(output.equals("error (0)"))
					Utilities.sendMessage(channel, l10n.translate("invalidURL", channel));
				else if(output.equals("error (3)"))
					Utilities.sendMessage(channel, l10n.translate("couldntUnshort", channel));
				else
					Utilities.sendMessage(channel, doc.text());
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"unshorten", "longurl"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}