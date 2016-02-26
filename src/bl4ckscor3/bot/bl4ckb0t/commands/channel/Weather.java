package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Weather extends BaseCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IOException, IncorrectCommandExecutionException
	{
		String city = "";

		for(String s : args)
		{
			if(isValidAlias(s)) //if it's the first argument, don't add it to the city string
				continue;

			city += s + " ";
		}
		
		if(city.equals(""))
			throw new IncorrectCommandExecutionException(getMainAlias());
		
		try
		{
			Document doc = Jsoup.connect("http://api.openweathermap.org/data/2.5/weather?q=" + city.trim() + "&mode=html&APPID=" + Passwords.WEATHERAPIKEY.getPassword()).get();

			Utilities.sendStarMsg(event, 
					Colors.BOLD + doc.select("body > div:nth-child(1)").text() + Colors.NORMAL,
					Colors.BOLD + L10N.getString("w.temperature", event).replace("#temperature", Colors.BOLD + getTemperature(doc)),
					Colors.BOLD + L10N.getString("w.humidity", event).replace("#humidity", Colors.BOLD + doc.select("body > div:nth-child(2) > div:nth-child(3)").text().split(" ")[1]),
					Colors.BOLD + L10N.getString("w.pressure", event).replace("#pressure", Colors.BOLD + doc.select("body > div:nth-child(2) > div:nth-child(5)").text().split(" ")[1]),
					Colors.BOLD + L10N.getString("w.wind", event).replace("#wind", Colors.BOLD + getWindSpeed(doc)),
					Colors.BOLD + L10N.getString("w.credit", event).replace("#link", doc.select("body > div:nth-child(3) > a:nth-child(1)").attr("href")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Utilities.chanMsg(event, L10N.getString("w.cityNotFound", event).replace("#city", city).replace("#smiley", ":/"));
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
		
		return ms + " m/s | " + Utilities.formatDouble(ms * 2.2369362920544) + " mp/h";
	}
	
	@Override
	public String[] getAliases()
	{
		return new String[]{"w", "weather"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-w <" + L10N.getString("w.help.city", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{"-w <" + L10N.getString("w.help.city", event) + "> || " + L10N.getString("w.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent event)
	{
		return L10N.getString("w.notes", event);
	}
}
