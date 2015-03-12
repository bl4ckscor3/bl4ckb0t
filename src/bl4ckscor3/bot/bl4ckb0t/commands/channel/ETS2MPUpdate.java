package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ETS2MPUpdate implements ICommand<MessageEvent<Bot>>
{
	private boolean checking = false;
	private String currentVersion;

	@Override
	public void exe(MessageEvent<Bot> event) throws Exception
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(checking)
		{
			if(args.length < 2 || args.length > 2)
			{
				Utilities.chanMsg(event, L10N.strings.getString("ets2mp.onlyStop"));
				Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
				return;
			}
		}
		else 
		{
			if(args.length < 3)
			{
				Utilities.chanMsg(event, L10N.strings.getString("ets2mp.onlyStart"));
				Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
				return;
			}
		}

		switch(args[1])
		{
			case "start":
				currentVersion = "";
				
				for(int i = 2; i < args.length; i++)
				{
					currentVersion += args[i] + " ";
				}
				
				currentVersion = currentVersion.substring(0, currentVersion.length() - 1);
				checking = true;
				Utilities.chanMsg(event, L10N.strings.getString("ets2mp.successful.start"));
				checkForUpdates(event);
				break;
			case "stop":
				Utilities.chanMsg(event, L10N.strings.getString("ets2mp.successful.start"));
				checking = false;
		}
	}

	private void checkForUpdates(MessageEvent<Bot> event) throws InterruptedException
	{
		WebDriver driver = new HtmlUnitDriver();
		WebElement element;

		while(checking)
		{
			driver.get("http://ets2mp.com");
			element = driver.findElement(By.xpath("//div[@class='version']"));

			if(!element.getText().equals("Current release: " + currentVersion + "Download latest version"))
			{
				Utilities.chanMsg(event, L10N.strings.getString("ets2mp.newVersion.1") + " (" + element.getText().substring(17).split("Download")[0] + ") " + L10N.strings.getString("ets2mp.newVersion.2"));
				checking = false;
			}

			Thread.sleep(60000); //wait one minute before checking again
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
		return "-ets2mp <start|stop> [" + L10N.strings.getString("ets2mp.help.currentVersion")+ "]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-ets2mp start <" + L10N.strings.getString("ets2mp.help.currentVersion")+ "> || " + L10N.strings.getString("ets2mp.explanation.1"),
				"-ets2mp stop || " + L10N.strings.getString("ets2mp.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("ets2mp.notes");
	}
}
