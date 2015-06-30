package bl4ckscor3.bot.bl4ckb0t.misc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ShowTweet
{
	public static void show(MessageEvent<Bot> event, String link)
	{
		WebDriver driver = new FirefoxDriver();
		String name = "";
		String account = "";
		String tweet = "";

		if(link.startsWith("www."))
			link = "http://" + link;

		if(link.split("/").length < 6) //it's not a tweet
			return;

		driver.get(link);
		name = driver.findElement(By.xpath("//strong[@class='fullname js-action-profile-name show-popup-with-id']")).getText();

		if(name.endsWith("Verified Account"))
		{
			name = name.substring(0, name.lastIndexOf("Verified Account"));
			name = name.substring(0, name.length() - 1);
		}

		account = driver.findElement(By.xpath("//span[@class='username js-action-profile-name']")).getText();
		tweet = driver.findElement(By.xpath("//p[@class='TweetTextSize TweetTextSize--28px js-tweet-text tweet-text']")).getText();
		driver.close();
		Utilities.chanMsg(event, Colors.BOLD + name + " (" + account + ") - " + Colors.BOLD + tweet);
	}
}
