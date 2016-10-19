package bl4ckscor3.module.linktitle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.modules.LinkManager;
import bl4ckscor3.bot.bl4ckb0t.util.LinkAction;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class LinkTitle extends Module implements LinkAction
{
	private final List<String> blacklistedWebsites = new ArrayList<String>();
	private L10N l10n;

	public LinkTitle(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader) throws MalformedURLException, IOException
	{
		getBlacklistedWebsites();
		LinkManager.registerLinkAction(this);
		l10n = new L10N(this, loader);
	}

	@Override
	public void onDisable()
	{
		blacklistedWebsites.clear();
		LinkManager.removeLinkAction(this);
	}

	@Override
	public void onUpdate()
	{
		blacklistedWebsites.clear();
		getBlacklistedWebsites();
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
	{
		HtmlUnitDriver driver = new HtmlUnitDriver();
		String title = "";

		if(link.startsWith("www."))
			link = "http://" + link;

		driver.get(link);
		title = driver.getTitle();
		driver.quit();

		if(link.startsWith("http://"))
			link = link.substring(7);
		else if(link.startsWith("https://"))
			link = link.substring(8);

		if(link.length() > 21)
		{
			link = link.substring(0, 21);
			link += "...";
		}

		if(title == null || title == "null" || title == "")
			Utilities.sendMessage(channel, l10n.translate("notFound", channel).replace("#link", link));
		else
			Utilities.sendMessage(channel, l10n.translate("available", channel).replace("#link", link).replace("#title", title));
	}

	@Override
	public boolean isValid(String channel, String link)
	{
		for(String s : blacklistedWebsites)
		{
			if(link.contains(s))
				return false;
		}
		
		return true;
	}

	@Override
	public int getPriority()
	{
		return 0;
	}

	/**
	 * Populates the blacklistedWebsites list
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private void getBlacklistedWebsites()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://akino.canopus.uberspace.de/bl4ckb0t/files/blacklistedWebsites.txt").openStream()));
			String line = "";

			while((line = reader.readLine()) != null)
			{
				blacklistedWebsites.add(line);
			}

			reader.close();
		}
		catch(Exception e)
		{
			Logging.stackTrace(e);
		}
	}
}