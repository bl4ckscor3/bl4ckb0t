package bl4ckscor3.module.ranks;

import java.net.URLClassLoader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Ranks extends Module
{
	private L10N l10n;
	
	public Ranks(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command());
		l10n = new L10N(this, loader);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	@Override
	public String getNotes(String channel)
	{
		return l10n.translate("notes", channel);
	}
	
	public class Command extends BaseChannelCommand
	{
		private int highest;
		
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String channel = event.getChannel().getName();
			
			Utilities.sendMessage(channel, l10n.translate("wait", channel));

			WebDriver driver = new HtmlUnitDriver(true);
			String[] data = new String[18];
			double highestValue = 0.0D;

			driver.get("https://csgosquad.com/ranks");

			try
			{
				driver.findElement(By.cssSelector(".maintenance-text"));
				Utilities.sendMessage(channel, l10n.translate("maintenance", channel));
				driver.quit();
			}
			catch(Exception e)
			{
				for(int i = 0; i < data.length; i++)
				{
					data[i] = driver.findElement(By.cssSelector("#rank-" + (i + 1))).getText();

					try
					{
						double d = Double.parseDouble(data[i].replace("%", ""));

						if(d > highestValue)
						{
							highest = i + 1;
							highestValue = d;
						}
					}
					catch(Exception ex){}
				}

				driver.quit();
				Utilities.sendStarMsg(channel,
						Colors.LIGHT_GRAY + underline(1) + "Silver 1" + Colors.NORMAL + underline(1) + Colors.BOLD + ": " + Colors.NORMAL + underline(1) + data[0],
						Colors.LIGHT_GRAY + underline(2) + "Silver 2" + Colors.NORMAL + underline(2) + Colors.BOLD + ": " + Colors.NORMAL + underline(2) + data[1],
						Colors.LIGHT_GRAY + underline(3) + "Silver 3" + Colors.NORMAL + underline(3) + Colors.BOLD + ": " + Colors.NORMAL + underline(3) + data[2],
						Colors.LIGHT_GRAY + underline(4) + "Silver 4" + Colors.NORMAL + underline(4) + Colors.BOLD + ": " + Colors.NORMAL + underline(4) + data[3],
						Colors.LIGHT_GRAY + underline(5) + "Silver Elite" + Colors.NORMAL + underline(5) + Colors.BOLD + ": " + Colors.NORMAL + underline(5) + data[4],
						Colors.LIGHT_GRAY + underline(6) + "Silver Elite Master" + Colors.NORMAL + underline(6) + Colors.BOLD + ": " + Colors.NORMAL + underline(6) + data[5],
						Colors.CYAN + underline(7) + "Gold Nova 1" + Colors.NORMAL + underline(7) + Colors.BOLD + ": " + Colors.NORMAL + underline(7) + data[6],
						Colors.CYAN + underline(8) + "Gold Nova 2" + Colors.NORMAL + underline(8) + Colors.BOLD + ": " + Colors.NORMAL + underline(8) + data[7],
						Colors.CYAN + underline(9) + "Gold Nova 3" + Colors.NORMAL + underline(9) + Colors.BOLD + ": " + Colors.NORMAL + underline(9) + data[8],
						Colors.CYAN + underline(10) + "Gold Nova Master" + Colors.NORMAL + underline(10) + Colors.BOLD + ": " + Colors.NORMAL + underline(10) + data[9],
						Colors.TEAL + underline(11) + "Master Guardian 1" + Colors.NORMAL + underline(11) + Colors.BOLD + ": " + Colors.NORMAL + underline(11) + data[10],
						Colors.TEAL + underline(12) + "Master Guardian 2" + Colors.NORMAL + underline(12) + Colors.BOLD + ": " + Colors.NORMAL + underline(12) + data[11]);
				Utilities.sendStarMsg(channel,
						Colors.TEAL + underline(13) + "Master Guardian Elite" + Colors.NORMAL + underline(13) + Colors.BOLD + ": " + Colors.NORMAL + underline(13) + data[12],
						Colors.TEAL + underline(14) + "Distinguished Master Guardian" + Colors.NORMAL + underline(14) + Colors.BOLD + ": " + Colors.NORMAL + underline(14) + data[13],
						Colors.BROWN + underline(15) + "Legendary Eagle" + Colors.NORMAL + underline(15) + Colors.BOLD + ": " + Colors.NORMAL + underline(15) + data[14],
						Colors.BROWN + underline(16) + "Legendary Eagle Master" + Colors.NORMAL + underline(16) + Colors.BOLD + ": " + Colors.NORMAL + underline(16) + data[15],
						Colors.RED + underline(17) + "Supreme Master First Class" + Colors.NORMAL + underline(17) + Colors.BOLD + ": " + Colors.NORMAL + underline(17) + data[16],
						Colors.RED + underline(18) + "The Global Elite" + Colors.NORMAL + underline(18) + Colors.BOLD + ": " + Colors.NORMAL + underline(18) + data[17],
						Colors.NORMAL + "https://csgosquad.com/ranks");
			}
		}

		/**
		 * Checks if the given Integer is equal to the "highest" Integer, and returns a String based on the result
		 * @param checkFor The Integer to compare to "highest"
		 * @return Colors.UNDERLINE if checkFor == highest, null otherwise
		 */
		private String underline(int checkFor)
		{
			return highest == checkFor ? Colors.UNDERLINE : "";
		}
		
		@Override
		public String[] getAliases()
		{
			return new String[]{"ranks", "rank"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}