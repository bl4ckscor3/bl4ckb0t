package bl4ckscor3.bot.bl4ckb0t.misc;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ShowTweet
{
	public static void show(MessageEvent<Bot> event, String link)
	{
		WebDriver driver = new HtmlUnitDriver(false);
		String name = "";
		String account = "";
		String tweet = "";

		if(link.startsWith("www."))
			link = "http://" + link;

		if(link.split("/").length < 6) //it's not a tweet
			return;

		driver.get(link);

		try
		{
			name = driver.findElement(By.cssSelector("strong.fullname")).getText();
		}
		catch(NoSuchElementException e)
		{
			Logging.warn("Couldn't find \"name\" element.");
		}

		if(name.endsWith("Verified Account"))
		{
			name = name.substring(0, name.lastIndexOf("Verified Account"));
			name = name.substring(0, name.length() - 1);
		}
		
		try
		{
			account = driver.findElement(By.cssSelector("span.username:nth-child(4)")).getText();
		}
		catch(NoSuchElementException e)
		{
			Logging.warn("Couldn't find \"account\" element.");
		}

		try
		{
			tweet = driver.findElement(By.cssSelector("p.TweetTextSize:nth-child(2)")).getText();
		}
		catch(NoSuchElementException e)
		{
			Logging.warn("Couldn't find \"tweet\" element.");
		}

		driver.close();
		Utilities.chanMsg(event, Colors.BOLD + name + " (" + account + ") - " + Colors.BOLD + tweet);
	}
}
