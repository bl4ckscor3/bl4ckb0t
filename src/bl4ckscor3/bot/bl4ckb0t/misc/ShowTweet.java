package bl4ckscor3.bot.bl4ckb0t.misc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ShowTweet
{
	public static void show(MessageEvent<Bot> event, String link)
	{
		WebDriver driver = new HtmlUnitDriver(true);
		String name = "";
		String account = "";
		String tweet = "";
		
		if(link.startsWith("www."))
			link = "http://" + link;
		
		driver.get(link);
		name = driver.findElement(By.xpath("//strong[@class='fullname js-action-profile-name show-popup-with-id']")).getText();
		account = driver.findElement(By.xpath("//span[@class='username js-action-profile-name']")).getText();
		tweet = driver.findElement(By.xpath("//p[@class='TweetTextSize TweetTextSize--28px js-tweet-text tweet-text']")).getText();
		driver.close();
		Utilities.chanMsg(event, Colors.BOLD + name + " (" + account + ") - " + Colors.BOLD + tweet);
	}
}
