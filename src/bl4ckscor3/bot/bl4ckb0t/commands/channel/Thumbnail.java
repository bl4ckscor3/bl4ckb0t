package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.jsoup.Jsoup;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Thumbnail extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		String channel = event.getChannel().getName();
		
		if(!args[0].matches("(https:\\/\\/|http:\\/\\/|)www\\.youtube\\.com\\/watch\\?v=[A-Za-z0-9-_]{11}")) //"https://" or "http://" or nothing, followed by www.youtube.com/watch?v= followed by 11 interchangeable upper or lower cased letters, or numbers or - or _
		{
			Utilities.sendMessage(channel, L10N.getString("thumbnail.noVideo", channel));
			return;
		}
		else
			Utilities.sendMessage(channel, Jsoup.connect(args[0].startsWith("www") ? "https://" + args[0] : args[0]).get().select("head > meta[property=\"og:image\"]").attr("content"));
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"thumbnail", "thumb", "tn"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-thumbnail <videolink>";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-thumbnail <videolink> || " + L10N.getString("thumbnail.explanation", channel)};
	}
}
