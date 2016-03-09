package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Ranks extends BaseChannelCommand<MessageEvent>
{
	private int highest;
	
	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		Utilities.chanMsg(event, L10N.getString("ranks.wait", event));
		
		WebDriver driver = new HtmlUnitDriver(true);
		String[] data = new String[18];
		double highestValue = 0.0D;
		
		driver.get("https://csgosquad.com/ranks");
		
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
			catch(Exception e){}
		}
		
		driver.quit();
		Utilities.sendStarMsg(event,
				Colors.BOLD + Colors.LIGHT_GRAY + underline(1) + "Silver 1" + Colors.NORMAL + underline(1) + Colors.BOLD + ": " + Colors.NORMAL + underline(1) + data[0] + Colors.NORMAL,
				Colors.BOLD + Colors.LIGHT_GRAY + underline(2) + "Silver 2" + Colors.NORMAL + underline(2) + Colors.BOLD + ": " + Colors.NORMAL + underline(2) + data[1] + Colors.NORMAL,
				Colors.BOLD + Colors.LIGHT_GRAY + underline(3) + "Silver 3" + Colors.NORMAL + underline(3) + Colors.BOLD + ": " + Colors.NORMAL + underline(3) + data[2] + Colors.NORMAL,
				Colors.BOLD + Colors.LIGHT_GRAY + underline(4) + "Silver 4" + Colors.NORMAL + underline(4) + Colors.BOLD + ": " + Colors.NORMAL + underline(4) + data[3] + Colors.NORMAL,
				Colors.BOLD + Colors.LIGHT_GRAY + underline(5) + "Silver Elite" + Colors.NORMAL + underline(5) + Colors.BOLD + ": " + Colors.NORMAL + underline(5) + data[4] + Colors.NORMAL,
				Colors.BOLD + Colors.LIGHT_GRAY + underline(6) + "Silver Elite Master" + Colors.NORMAL + underline(6) + Colors.BOLD + ": " + Colors.NORMAL + underline(6) + data[5] + Colors.NORMAL,
				Colors.BOLD + Colors.CYAN + underline(7) + "Gold Nova 1" + Colors.NORMAL + underline(7) + Colors.BOLD + ": " + Colors.NORMAL + underline(7) + data[6] + Colors.NORMAL,
				Colors.BOLD + Colors.CYAN + underline(8) + "Gold Nova 2" + Colors.NORMAL + underline(8) + Colors.BOLD + ": " + Colors.NORMAL + underline(8) + data[7] + Colors.NORMAL,
				Colors.BOLD + Colors.CYAN + underline(9) + "Gold Nova 3" + Colors.NORMAL + underline(9) + Colors.BOLD + ": " + Colors.NORMAL + underline(9) + data[8] + Colors.NORMAL,
				Colors.BOLD + Colors.CYAN + underline(10) + "Gold Nova Master" + Colors.NORMAL + underline(10) + Colors.BOLD + ": " + Colors.NORMAL + underline(10) + data[9] + Colors.NORMAL,
				Colors.BOLD + Colors.TEAL + underline(11) + "Master Guardian 1" + Colors.NORMAL + underline(11) + Colors.BOLD + ": " + Colors.NORMAL + underline(11) + data[10] + Colors.NORMAL,
				Colors.BOLD + Colors.TEAL + underline(12) + "Master Guardian 2" + Colors.NORMAL + underline(12) + Colors.BOLD + ": " + Colors.NORMAL + underline(12) + data[11]);
		Utilities.sendStarMsg(event,
				Colors.BOLD + Colors.TEAL + underline(13) + "Master Guardian Elite" + Colors.NORMAL + underline(13) + Colors.BOLD + ": " + Colors.NORMAL + underline(13) + data[12] + Colors.NORMAL,
				Colors.BOLD + Colors.TEAL + underline(14) + "Distinguished Master Guardian" + Colors.NORMAL + underline(14) + Colors.BOLD + ": " + Colors.NORMAL + underline(14) + data[13] + Colors.NORMAL,
				Colors.BOLD + Colors.BROWN + underline(15) + "Legendary Eagle" + Colors.NORMAL + underline(15) + Colors.BOLD + ": " + Colors.NORMAL + underline(15) + data[14] + Colors.NORMAL,
				Colors.BOLD + Colors.BROWN + underline(16) + "Legendary Eagle Master" + Colors.NORMAL + underline(16) + Colors.BOLD + ": " + Colors.NORMAL + underline(16) + data[15] + Colors.NORMAL,
				Colors.BOLD + Colors.RED + underline(17) + "Supreme Master First Class" + Colors.NORMAL + underline(17) + Colors.BOLD + ": " + Colors.NORMAL + underline(17) + data[16] + Colors.NORMAL,
				Colors.BOLD + Colors.RED + underline(18) + "The Global Elite" + Colors.NORMAL + underline(18) + Colors.BOLD + ": " + Colors.NORMAL + underline(18) + data[17] + Colors.NORMAL,
				Colors.BOLD + Colors.NORMAL + "https://csgosquad.com/ranks");
	}

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
	public String getSyntax(MessageEvent event)
	{
		return "-ranks";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{"-ranks || " + L10N.getString("ranks.explanation", event)};
	}
	
	@Override
	public String getNotes(MessageEvent event)
	{
		return L10N.getString("ranks.notes", event);
	}
}
