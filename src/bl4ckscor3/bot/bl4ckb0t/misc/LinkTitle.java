package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class LinkTitle
{
	public static void checkForLinkAndSendTitle(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		for(String s : args)
		{
			if(s.contains("www.") || s.contains("http://") || s.contains("https://"))
			{
				if(isWebsiteBlacklisted(s))
					continue;
				
				WebDriver driver = new HtmlUnitDriver();
				String title = "";

				if(s.startsWith("www."))
					s = "http://" + s;

				driver.get(s);
				title = driver.getTitle();

				if(s.startsWith("http://"))
					s = s.substring(7);
				else if(s.startsWith("https://"))
					s = s.substring(8);

				if(s.length() > 21)
				{
					s = s.substring(0, 21);
					s += "...";
				}

				if(title == null || title == "null" || title == "")
					Utilities.chanMsg(event,L10N.strings.getString("linkTitle.notFound") + " " + s);
				else
					Utilities.chanMsg(event, L10N.strings.getString("linkTitle.available") + " " + s + " - " + Colors.BOLD + title);
			}
		}
	}
	
	private static boolean isWebsiteBlacklisted(String website) throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/x95z98frbt1r2ne/blacklistedWebsites.txt?dl=1").openStream()));
		List<String> blacklistedWebsites = new ArrayList<String>();
		String line = "";
		
		while((line = reader.readLine()) != null)
		{
			blacklistedWebsites.add(line);
		}
		
		for(String s : blacklistedWebsites)
		{
			if(website.contains(s))
				return true;
		}
		
		return false;
	}
}
