package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Weather extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IOException, IncorrectCommandExecutionException
	{
		String city = "";
		String channel = event.getChannel().getName();
		
		for(String s : args)
		{
			city += s + " ";
		}
		
		if(city.equals(""))
			throw new IncorrectCommandExecutionException(getMainAlias());
		
		try
		{
			Document doc = Jsoup.connect("http://api.openweathermap.org/data/2.5/weather?q=" + city.trim() + "&mode=html&APPID=" + Passwords.WEATHERAPIKEY.getPassword()).get();

			Utilities.sendStarMsg(channel, 
					doc.select("body > div:nth-child(1)").text() + Colors.NORMAL,
					L10N.getString("w.temperature", channel).replace("#temperature", Colors.BOLD + getTemperature(doc)),
					L10N.getString("w.humidity", channel).replace("#humidity", Colors.BOLD + doc.select("body > div:nth-child(2) > div:nth-child(3)").text().split(" ")[1]),
					L10N.getString("w.pressure", channel).replace("#pressure", Colors.BOLD + doc.select("body > div:nth-child(2) > div:nth-child(5)").text().split(" ")[1]),
					L10N.getString("w.wind", channel).replace("#wind", Colors.BOLD + getWindSpeed(doc)),
					L10N.getString("w.credit", channel).replace("#link", doc.select("body > div:nth-child(3) > a:nth-child(1)").attr("href")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Utilities.sendMessage(channel, L10N.getString("w.cityNotFound", channel).replace("#city", city.trim()).replace("#smiley", ":/"));
		}
	}

	private String getTemperature(Document doc)
	{
		double celsius = Double.parseDouble(doc.select("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)").text().replace("°C", ""));

		return celsius + "°C | " + Utilities.formatDouble(celsius * (9D / 5D) + 32D) + "°F | " + Utilities.formatDouble(celsius + 273.15D) + "K";
	}

	private String getWindSpeed(Document doc)
	{
		double ms = Double.parseDouble(doc.select("body > div:nth-child(2) > div:nth-child(4)").text().split(":")[1].trim().replace(" m/s", ""));
		
		return ms + " m/s | " + Utilities.formatDouble(ms * 2.2369362920544) + " mph";
	}
	
	@Override
	public String[] getAliases()
	{
		return new String[]{"w", "weather"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-w <" + L10N.getString("w.help.city", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-w <" + L10N.getString("w.help.city", channel) + "> || " + L10N.getString("w.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("w.notes", channel);
	}
}
