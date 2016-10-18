package bl4ckscor3.module.thumbnail;

import java.net.URLClassLoader;

import org.jsoup.Jsoup;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Thumbnail extends Module
{
	private L10N l10n;
	
	public Thumbnail(String name)
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
			String channel = event.getChannel().getName();

			if(!args[0].matches("(https:\\/\\/|http:\\/\\/|)www\\.youtube\\.com\\/watch\\?v=[A-Za-z0-9-_]{11}(&[A-Za-z0-9=&]*|)") && !args[0].matches("[A-Za-z0-9-_]{11}")) //"https://" or "http://" or nothing, followed by www.youtube.com/watch?v= followed by 11 interchangeable upper or lower cased letters, or numbers or - or _
			{
				Utilities.sendMessage(channel, l10n.translate("noVideo", channel));
				return;
			}
			else
			{
				String link = args[0].matches("[A-Za-z0-9-_]{11}") ? "https://www.youtube.com/watch?v=" + args[0] : args[0];
				
				Utilities.sendMessage(channel, Jsoup.connect(link.startsWith("www") ? "https://" + link : link).get().select("head > meta[property=\"og:image\"]").attr("content"));
			}
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"thumbnail", "thumb", "tn"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}