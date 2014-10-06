package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class ETS2MPUpdate implements ICommand<MessageEvent>
{
	private boolean checking = false;
	private String currentVersion;

	@Override
	public void exe(MessageEvent event) throws Exception
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(checking)
		{
			if(args.length < 2 || args.length > 2)
			{
				Utilities.chanMsg(event, "Invalid number of arguments. Usage: -ets2mp stop");
				return;
			}

			if(args[1].equals("start"))
			{
				Utilities.chanMsg(event, "You cannot start again.");
				return;
			}
		}
		else 
		{
			if(args.length < 3)
			{
				Utilities.chanMsg(event, "Invalid number of arguments. Usage: -ets2mp start <current version>");
				return;
			}

			if(args[1].equalsIgnoreCase("stop"))
			{
				Utilities.chanMsg(event, "You cannot stop what hasn't been started.");
				return;
			}
		}

		switch(args[1])
		{
			case "start":
				for(int i = 2; i < args.length; i++)
				{
					currentVersion += args[i];
				}
				
				checking = true;
				checkForUpdates(event);
				break;
			case "stop":
				checking = false;
		}
	}

	private void checkForUpdates(MessageEvent event) throws InterruptedException
	{
		WebDriver driver = new HtmlUnitDriver();
		WebElement element;

		while(checking)
		{
			driver.get("http://ets2mp.com");
			element = driver.findElement(By.xpath("//div[@class='red_haze']"));

			if(!element.getText().equals(currentVersion))
			{
				Utilities.chanMsg(event, "New version (" + element.getText() + ") of ETS2MP is out! Download: http://alpha.ets2mp.com");
				checking = false;
			}

			Thread.sleep(60000);//wait one minute before checking again
		}
	}

	@Override
	public String getAlias()
	{
		return "ets2mp";
	}

	@Override
	public String getSyntax()
	{
		return "-ets2mp <start|stop> [currentVersion]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-ets2mp start <currentVersion> || Starts a query every minute to check if there's an update for ETS2MP.",
				"-ets2mp stop || Stops the query."
				};
	}

	@Override
	public String getNotes()
	{
		return "Check if you wrote the current version correctly. If you got the slightest typo, it will not work!";
	}

}
