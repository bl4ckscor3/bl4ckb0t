package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Distance extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		String channel = event.getChannel().getName();
		Document doc = Jsoup.connect("http://www.distance.to/" + args[0] + "/" + args[1]).get();
		Logging.debug(doc.html());
		String directMI = doc.select(".info > li:nth-child(1) > span:nth-child(1)").get(0).text().replace(",", "");
		String directKM = "" + String.format("%.2f", Double.parseDouble(directMI.split(" ")[0]) * 1.621371).replace(",", ".");

		Utilities.sendStarMsg(channel,
					Colors.NORMAL + directKM + "km" + Colors.ITALICS + " (" + directMI + ")",
					L10N.getString("distance.credit", channel).replace("#link", "http://www.distance.to/"));
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"distance", "dist"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-distance <" + L10N.getString("w.help.city", channel) + "> <" + L10N.getString("w.help.city", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-distance <" + L10N.getString("w.help.city", channel) + "> <" + L10N.getString("w.help.city", channel) + "> || " + L10N.getString("distance.explanation", channel)};
	}
	
	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("distance.notes", channel);
	}
}
