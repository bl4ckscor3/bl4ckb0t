package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class MCStatus extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args)
	{
		WebDriver driver = new HtmlUnitDriver(true);

		driver.get("http://xpaw.ru/mcstatus");
		Executors.newSingleThreadScheduledExecutor().schedule(() -> {
			WebElement login = driver.findElement(By.cssSelector("div#login h2.status"));
			WebElement session = driver.findElement(By.cssSelector("div#session h2.status"));
			WebElement website = driver.findElement(By.cssSelector("div#website h2.status"));
			WebElement skins = driver.findElement(By.cssSelector("div#skins h2.status"));
			WebElement realms = driver.findElement(By.cssSelector("div#realms h2.status"));
			String result = Colors.BOLD + "** Mojang Server Status ** Login: " + Colors.BOLD;

			//login
			if(login.getText().equals("Online"))
				result += Colors.DARK_GREEN + login.getText();
			else if(login.getText().equals("Timed Out") || login.getText().equals("HTTP Error 503"))
				result += Colors.RED + login.getText();
			else	
				result += Colors.PURPLE + login.getText();

			result += Colors.NORMAL + Colors.BOLD + " ** Session: " + Colors.BOLD;

			//session
			if(session.getText().equals("Online"))
				result += Colors.DARK_GREEN + session.getText();
			else if(session.getText().equals("Timed Out") || session.getText().equals("HTTP Error 503"))
				result += Colors.RED + session.getText();
			else	
				result += Colors.PURPLE + session.getText();

			result += Colors.NORMAL + Colors.BOLD + " ** Website: " + Colors.BOLD;

			//website
			if(website.getText().equals("Online"))
				result += Colors.DARK_GREEN + website.getText();
			else if(website.getText().equals("Timed Out") || website.getText().equals("HTTP Error 503"))
				result += Colors.RED + website.getText();
			else	
				result += Colors.PURPLE + website.getText();

			result += Colors.NORMAL + Colors.BOLD + " ** Skins: " + Colors.BOLD;

			//skins
			if(skins.getText().equals("Online"))
				result += Colors.DARK_GREEN + skins.getText();
			else if(skins.getText().equals("Timed Out") || skins.getText().equals("HTTP Error 503"))
				result += Colors.RED + skins.getText();
			else	
				result += Colors.PURPLE + skins.getText();

			result += Colors.NORMAL + Colors.BOLD + " ** Realms: " + Colors.BOLD;

			//realms
			if(realms.getText().equals("Online"))
				result += Colors.DARK_GREEN + realms.getText();
			else if(realms.getText().equals("Timed Out") || realms.getText().equals("HTTP Error 503"))
				result += Colors.RED + realms.getText();
			else	
				result += Colors.PURPLE + realms.getText();

			Utilities.sendMessage(event.getChannel().getName(), result + Colors.NORMAL + Colors.BOLD + " ** Powered by xpaw - http://xpaw.ru/mcstatus **");
			driver.quit();
		}, 1, TimeUnit.SECONDS);
	}		

	@Override
	public String[] getAliases()
	{
		return new String[]{"mcstatus"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-mcstatus";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-mcstatus || " + L10N.getString("mcstatus.explanation", channel)};
	}
}
