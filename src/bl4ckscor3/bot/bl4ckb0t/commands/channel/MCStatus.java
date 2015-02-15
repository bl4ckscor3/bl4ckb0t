package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class MCStatus implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception
	{
		WebDriver driver = new HtmlUnitDriver(true);
		WebElement login;
		WebElement session;
		WebElement website;
		WebElement skins;
		WebElement realms;
		StringBuilder builder = new StringBuilder();

		driver.get("http://xpaw.ru/mcstatus");
		login = driver.findElement(By.cssSelector("div#login h2.status"));
		session = driver.findElement(By.cssSelector("div#session h2.status"));
		website = driver.findElement(By.cssSelector("div#website h2.status"));
		skins = driver.findElement(By.cssSelector("div#skins h2.status"));
		realms = driver.findElement(By.cssSelector("div#realms h2.status"));
		builder.append("** Mojang Server Status ");

		//login
		if(login.getText().equals("Online"))
			builder.append("** Login: " + Colors.DARK_GREEN + login.getText() + Colors.BLACK);
		else if(login.getText().equals("Timed Out") || login.getText().equals("HTTP Error 503"))
			builder.append(" ** Login: " + Colors.RED + login.getText() + Colors.BLACK);
		else	
			builder.append(" ** Login: " + Colors.PURPLE + login.getText() + Colors.BLACK);

		//session
		if(session.getText().equals("Online"))
			builder.append(" ** Session: " + Colors.DARK_GREEN + session.getText() + Colors.BLACK);
		else if(session.getText().equals("Timed Out") || session.getText().equals("HTTP Error 503"))
			builder.append(" ** Session: " + Colors.RED + session.getText() + Colors.BLACK);
		else	
			builder.append(" ** Session: " + Colors.PURPLE + session.getText() + Colors.BLACK);

		//website
		if(website.getText().equals("Online"))
			builder.append(" ** Website: " + Colors.DARK_GREEN + website.getText() + Colors.BLACK);
		else if(website.getText().equals("Timed Out") || website.getText().equals("HTTP Error 503"))
			builder.append(" ** Website: " + Colors.RED + website.getText() + Colors.BLACK);
		else	
			builder.append(" ** Website: " + Colors.PURPLE + website.getText() + Colors.BLACK);

		//skins
		if(skins.getText().equals("Online"))
			builder.append(" ** Skins: " + Colors.DARK_GREEN + skins.getText() + Colors.BLACK);
		else if(skins.getText().equals("Timed Out") || skins.getText().equals("HTTP Error 503"))
			builder.append(" ** Skins: " + Colors.RED + skins.getText() + Colors.BLACK);
		else	
			builder.append(" ** Skins: " + Colors.PURPLE + skins.getText() + Colors.BLACK);

		//realms
		if(realms.getText().equals("Online"))
			builder.append(" ** Realms: " + Colors.DARK_GREEN + realms.getText() + Colors.BLACK);
		else if(realms.getText().equals("Timed Out") || realms.getText().equals("HTTP Error 503"))
			builder.append(" ** Realms: " + Colors.RED + realms.getText() + Colors.BLACK);
		else	
			builder.append(" ** Realms: " + Colors.PURPLE + realms.getText() + Colors.BLACK);
		
		builder.append(" ** Powered by xpaw - http://xpaw.ru/mcstatus **");
		Utilities.chanMsg(event, builder.toString());
		driver.close();
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
		return new String[]{"-mcstatus || Tells you the current status of \"Mojang\"'s servers."};
	}

	@Override
	public String getNotes()
	{
		return "Currently not working.";
	}
}
