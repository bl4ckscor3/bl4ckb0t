package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class MCStatus extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args)
	{
		WebDriver driver = new HtmlUnitDriver(true);

		driver.get("http://xpaw.ru/mcstatus");
		Executors.newSingleThreadScheduledExecutor().schedule(() -> {
			WebElement login = driver.findElement(By.cssSelector("div#login h2.status"));
			WebElement session = driver.findElement(By.cssSelector("div#session h2.status"));
			WebElement website = driver.findElement(By.cssSelector("div#website h2.status"));
			WebElement skins = driver.findElement(By.cssSelector("div#skins h2.status"));
			WebElement realms = driver.findElement(By.cssSelector("div#realms h2.status"));
			String result = "";

			result += Colors.BOLD + "** Mojang Server Status ";

			//login
			if(login.getText().equals("Online"))
				result += "** Login: " + Colors.BOLD + Colors.DARK_GREEN + login.getText() + Colors.NORMAL;
			else if(login.getText().equals("Timed Out") || login.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Login: " + Colors.BOLD + Colors.RED + login.getText() + Colors.NORMAL;
			else	
				result += Colors.BOLD + " ** Login: " + Colors.BOLD + Colors.PURPLE + login.getText() + Colors.NORMAL;

			//session
			if(session.getText().equals("Online"))
				result += Colors.BOLD + " ** Session: " + Colors.BOLD + Colors.DARK_GREEN + session.getText() + Colors.NORMAL;
			else if(session.getText().equals("Timed Out") || session.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Session: " + Colors.BOLD + Colors.RED + session.getText() + Colors.NORMAL;
			else	
				result += Colors.BOLD + " ** Session: " + Colors.BOLD + Colors.PURPLE + session.getText() + Colors.NORMAL;

			//website
			if(website.getText().equals("Online"))
				result += Colors.BOLD + " ** Website: " + Colors.BOLD + Colors.DARK_GREEN + website.getText() + Colors.NORMAL;
			else if(website.getText().equals("Timed Out") || website.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Website: " + Colors.BOLD + Colors.RED + website.getText() + Colors.NORMAL;
			else	
				result += Colors.BOLD + " ** Website: " + Colors.BOLD + Colors.PURPLE + website.getText() + Colors.NORMAL;

			//skins
			if(skins.getText().equals("Online"))
				result += Colors.BOLD + " ** Skins: " + Colors.BOLD + Colors.DARK_GREEN + skins.getText() + Colors.NORMAL;
			else if(skins.getText().equals("Timed Out") || skins.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Skins: " + Colors.BOLD + Colors.RED + skins.getText() + Colors.NORMAL;
			else	
				result += Colors.BOLD + " ** Skins: " + Colors.BOLD + Colors.PURPLE + skins.getText() + Colors.NORMAL;

			//realms
			if(realms.getText().equals("Online"))
				result += Colors.BOLD + " ** Realms: " + Colors.BOLD + Colors.DARK_GREEN + realms.getText() + Colors.NORMAL;
			else if(realms.getText().equals("Timed Out") || realms.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Realms: " + Colors.BOLD + Colors.RED + realms.getText() + Colors.NORMAL;
			else	
				result += Colors.BOLD + " ** Realms: " + Colors.BOLD + Colors.PURPLE + realms.getText() + Colors.NORMAL;

			result += Colors.BOLD + " ** Powered by xpaw - http://xpaw.ru/mcstatus **";
			Utilities.chanMsg(event, result);
			driver.close();
		}, 1, TimeUnit.SECONDS);
	}		

	@Override
	public String[] getAliases()
	{
		return new String[]{"mcstatus"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-mcstatus";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-mcstatus || " + L10N.getString("mcstatus.explanation", event)};
	}
}
