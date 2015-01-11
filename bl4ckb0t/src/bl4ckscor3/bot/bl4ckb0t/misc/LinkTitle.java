package bl4ckscor3.bot.bl4ckb0t.misc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class LinkTitle
{
	public static void checkForLinkAndSendTitle(MessageEvent event)
	{
		String[] args = Utilities.toArgs(event.getMessage());

		for(String s : args)
		{
			if(s.contains("www.") || s.contains("http://") || s.contains("https://"))
			{
				WebDriver driver = new HtmlUnitDriver();
				String title = null;

				if(s.startsWith("www."))
					s = "http://" + s;

				driver.get(s);
				title = driver.getTitle();
				Utilities.chanMsg(event, L10N.strings.getString("linkTitle.available") + s + " - " + Colors.BOLD + title);
			}
		}
	}
}
