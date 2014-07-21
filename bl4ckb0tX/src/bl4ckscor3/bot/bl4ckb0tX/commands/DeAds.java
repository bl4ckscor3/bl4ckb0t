package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;


public class DeAds implements Command<MessageEvent>
{
	@Override
	public void exe(final MessageEvent event) throws Exception
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String link;
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 60L);
		WebElement element;
		WebElement element2 = null;

		driver.get("http://de-ads.net");
		element = driver.findElement(By.id("url"));
		element.sendKeys(args[1]);
		element.submit();
		element2 = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#results a")));
		Utilities.chanMsg(event, "This link resolves to: " + element2.getAttribute("href").toString());
		driver.quit();
	}

	@Override
	public String getAlias()
	{
		return "deads";
	}	
}