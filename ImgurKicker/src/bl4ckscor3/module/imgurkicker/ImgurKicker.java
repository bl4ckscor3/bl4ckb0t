package bl4ckscor3.module.imgurkicker;

import java.net.URLClassLoader;

import org.jsoup.Jsoup;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.modules.LinkManager;
import bl4ckscor3.bot.bl4ckb0t.util.LinkAction;

public class ImgurKicker extends Module implements LinkAction
{
	private L10N l10n;
	
	public ImgurKicker(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		LinkManager.registerLinkAction(this);
		l10n = new L10N(this, loader);
	}
	
	@Override
	public void onDisable()
	{
		LinkManager.removeLinkAction(this);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	@Override
	public void handle(String channel, String user, String link) throws Exception
	{//code courtesy of Vauff
		int images = 0;

		for(String s : Jsoup.connect(link).get().select("div[class=post-images]").html().split(" "))
		{
			if(s.equals("class=\"post-image\">"))
			{
				images++;

				if(images == 2)
					break;
			}
		}

		if(images == 1)
			Core.bot.kick(channel, user, "Only use i.imgur.com links.");
	}

	@Override
	public boolean isValid(String channel, String link)
	{
		return channel.equals("#bl4ckscor3") && link.contains("imgur.com") && !link.contains("i.imgur.com");
	}

	@Override
	public int getPriority()
	{
		return 50;
	}
}