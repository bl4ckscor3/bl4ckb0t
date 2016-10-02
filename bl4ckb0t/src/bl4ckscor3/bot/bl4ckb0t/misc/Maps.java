package bl4ckscor3.bot.bl4ckb0t.misc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.ConfigurationFile;
import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

/**
 * Checks for and sends the map of the aG-Community Skill Surf server incl. player amount
 * @author bl4ckscor3, initial idea by Vauff
 */
public class Maps implements Runnable
{
	/**The map played before the current one*/
	private String previousMap;
	private int previousPlayers;

	public Maps()
	{
		previousMap = "";
		previousPlayers = 0;
	}

	//No translations because it's just for one channel. The wording should be universal enough, though...
	@Override
	public void run()
	{
		ConfigurationFile config = Core.bot.getConfig();
		
		if(!Core.bot.isEnabled() || !config.isEnabled("queryMaps"))
			return;

		try
		{
			Document doc = Jsoup.connect("http://cache.www.gametracker.com/components/html0/?host=" + config.getString("mapsHost") + "&bgColor=333333&fontColor=CCCCCC&titleBgColor=222222&titleColor=FF9900&borderColor=555555&linkColor=FFCC00&borderLinkColor=222222&showMap=0&showCurrPlayers=0&showTopPlayers=0&showBlogs=0&width=240").userAgent("Mozilla").get();
			String map = doc.select(".info_line_right > a:nth-child(1)").text();
			String players = doc.select("div.info_line:nth-child(5) > div:nth-child(2)").text().split("/")[0];
			String channel = config.getString("mapsChannel");

			if(!previousMap.equals(map) && !map.equals(""))
			{
				String message = "Now playing " + Colors.BOLD + Colors.DARK_GREEN + map;

				if(config.isEnabled("showPlayerCount") && !players.equals(""))
					message += Colors.NORMAL + " with " + Colors.BOLD + Colors.OLIVE + players + " players " + Colors.NORMAL + "online.";
				else
					message += Colors.NORMAL + ".";
				
				Utilities.sendMessage(channel, message);
				previousMap = map;
				previousPlayers = Integer.parseInt(players);
			}
			else if(config.isEnabled("showPlayerCount") && (previousPlayers != Integer.parseInt(players) && !players.equals("")))
			{
				Utilities.sendMessage(channel, "There are " + Colors.BOLD + Colors.OLIVE + players + " players " + Colors.NORMAL + "online.");
				previousPlayers = Integer.parseInt(players);
			}
		}
		catch(Exception e)
		{
			Logging.stackTrace(e);
		}
	}
}
