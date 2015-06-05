package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class MCStatus implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event)
	{
		WebDriver driver = new HtmlUnitDriver(true);
		ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
		Runnable task = () -> {
			WebElement login = driver.findElement(By.cssSelector("div#login h2.status"));
			WebElement session = driver.findElement(By.cssSelector("div#session h2.status"));
			WebElement website = driver.findElement(By.cssSelector("div#website h2.status"));
			WebElement skins = driver.findElement(By.cssSelector("div#skins h2.status"));
			WebElement realms = driver.findElement(By.cssSelector("div#realms h2.status"));
			String result = "";

			result += Colors.BOLD + "** Mojang Server Status ";

			//login
			if(login.getText().equals("Online"))
				result += "** Login: " + Colors.BOLD + Colors.DARK_GREEN + login.getText() + Colors.BLACK;
			else if(login.getText().equals("Timed Out") || login.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Login: " + Colors.BOLD + Colors.RED + login.getText() + Colors.BLACK;
			else	
				result += Colors.BOLD + " ** Login: " + Colors.BOLD + Colors.PURPLE + login.getText() + Colors.BLACK;

			//session
			if(session.getText().equals("Online"))
				result += Colors.BOLD + " ** Session: " + Colors.BOLD + Colors.DARK_GREEN + session.getText() + Colors.BLACK;
			else if(session.getText().equals("Timed Out") || session.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Session: " + Colors.BOLD + Colors.RED + session.getText() + Colors.BLACK;
			else	
				result += Colors.BOLD + " ** Session: " + Colors.BOLD + Colors.PURPLE + session.getText() + Colors.BLACK;

			//website
			if(website.getText().equals("Online"))
				result += Colors.BOLD + " ** Website: " + Colors.BOLD + Colors.DARK_GREEN + website.getText() + Colors.BLACK;
			else if(website.getText().equals("Timed Out") || website.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Website: " + Colors.BOLD + Colors.RED + website.getText() + Colors.BLACK;
			else	
				result += Colors.BOLD + " ** Website: " + Colors.BOLD + Colors.PURPLE + website.getText() + Colors.BLACK;

			//skins
			if(skins.getText().equals("Online"))
				result += Colors.BOLD + " ** Skins: " + Colors.BOLD + Colors.DARK_GREEN + skins.getText() + Colors.BLACK;
			else if(skins.getText().equals("Timed Out") || skins.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Skins: " + Colors.BOLD + Colors.RED + skins.getText() + Colors.BLACK;
			else	
				result += Colors.BOLD + " ** Skins: " + Colors.BOLD + Colors.PURPLE + skins.getText() + Colors.BLACK;

			//realms
			if(realms.getText().equals("Online"))
				result += Colors.BOLD + " ** Realms: " + Colors.BOLD + Colors.DARK_GREEN + realms.getText() + Colors.BLACK;
			else if(realms.getText().equals("Timed Out") || realms.getText().equals("HTTP Error 503"))
				result += Colors.BOLD + " ** Realms: " + Colors.BOLD + Colors.RED + realms.getText() + Colors.BLACK;
			else	
				result += Colors.BOLD + " ** Realms: " + Colors.BOLD + Colors.PURPLE + realms.getText() + Colors.BLACK;

			result += Colors.BOLD + " ** Powered by xpaw - http://xpaw.ru/mcstatus **";
			Utilities.chanMsg(event, result);
			driver.close();
		};

		driver.get("http://xpaw.ru/mcstatus");
		worker.schedule(task, 1, TimeUnit.SECONDS);
	}		

	@Override
	public String getAlias()
	{
		return "mcstatus";
	}

	@Override
	public String getSyntax()
	{
		return "-mcstatus";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-mcstatus || " + L10N.getString("mcsatus.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
	
	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
