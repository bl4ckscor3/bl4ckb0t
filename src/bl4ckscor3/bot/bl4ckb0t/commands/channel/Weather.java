package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Weather extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IOException, IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		Document doc;
		String city = "";

		for(String s : args)
		{
			if(s.equals("-w")) //if it's the first argument, don't add it to the city string
				continue;

			city += s + " ";
		}
		
		if(city.equals(""))
			throw new IncorrectCommandExecutionException(getAlias());
		
		try
		{
			doc = Jsoup.connect("http://api.openweathermap.org/data/2.5/weather?q=" + city.trim() + "&mode=html&APPID=" + Passwords.WEATHERAPIKEY.getPassword()).get();

			Utilities.chanMsg(event, Colors.BOLD + "** " + Colors.BOLD + doc.select("body > div:nth-child(1)").text() +
					Colors.BOLD + " ** " + L10N.getString("w.temperature", event) + ": " + Colors.BOLD + getTemperature(doc) +
					Colors.BOLD + " ** " + L10N.getString("w.humidity", event) + ": " + Colors.BOLD + doc.select("body > div:nth-child(2) > div:nth-child(3)").text().split(" ")[1] +
					Colors.BOLD + " ** " + L10N.getString("w.pressure", event) + ": " + Colors.BOLD + doc.select("body > div:nth-child(2) > div:nth-child(5)").text().split(" ")[1] +
					Colors.BOLD + " ** " + L10N.getString("w.wind", event) + ": " + Colors.BOLD + doc.select("body > div:nth-child(2) > div:nth-child(4)").text().split(":")[1].trim() +
					Colors.BOLD + " ** " + L10N.getString("w.credit", event) + doc.select("body > div:nth-child(1)").text() + "/ **");
		}
		catch(Exception e)
		{
			Utilities.chanMsg(event, L10N.getString("w.cityNotFound", event) + " \"" + city + "\" :/");
		}
	}

	private String getTemperature(Document doc)
	{
		double celsius = Double.parseDouble(doc.select("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)").text().replace("°", ""));

		return celsius + "°C | " + (celsius * (9D / 5D) + 32D) + "°F | " + (celsius + 273.15D) + "K";
	}

	@Override
	public String getAlias() 
	{
		return "w";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-w <" + L10N.getString("w.help.city", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-w " + L10N.getString("w.help.city", event) + " || " + L10N.getString("w.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("w.notes", event);
	}

	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
