package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class IsItDown implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws Exception
	{
		String[] args = Utilities.toArgs(event.getMessage());
		WebDriver driver = new HtmlUnitDriver(true);
		ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
		Runnable task = new Runnable()
		{
			public void run()
			{
				try
				{
					driver.findElement(By.xpath("//span[@class='upicon']"));
					Utilities.chanMsg(event, args[1] + " " + L10N.strings.getString("isitdown.is") + Colors.GREEN + " UP " + Colors.BLACK + L10N.strings.getString("isitdown.reachable") + ".");
				}
				catch(NoSuchElementException e)
				{
					Utilities.chanMsg(event, args[1] + " " + L10N.strings.getString("isitdown.is") + " " + Colors.RED + "DOWN" + Colors.BLACK + ".");
				}
				
				driver.close();
			}
		};
		
		if(args.length != 2)
		{
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
			return;
		}
		
		driver.get("http://iidrn.com/" + args[1] + ".html");
		worker.schedule(task, 5, TimeUnit.SECONDS);
	}

	@Override
	public String getAlias()
	{
		return "isitdown";
	}

	@Override
	public String getSyntax()
	{
		return "-isitdown <website>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-listchans || " + L10N.strings.getString("isitdown.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("isitdown.notes");
	}
}
