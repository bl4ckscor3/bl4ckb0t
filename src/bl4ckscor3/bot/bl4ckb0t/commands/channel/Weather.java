package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
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
			Document doc = Jsoup.connect("http://api.openweathermap.org/data/2.5/weather?q=" + city.trim() + "&mode=xml&APPID=" + Passwords.WEATHERAPIKEY.getPassword()).ignoreContentType(true).get();

			Utilities.sendStarMsg(channel, doc.select("city").attr("name") + ", " + doc.select("country").text(),
					L10N.getString("w.temperature", channel).replace("#temperature", Colors.NORMAL + getTemperature(doc)),
					L10N.getString("w.humidity", channel).replace("#humidity", Colors.NORMAL + doc.select("humidity").attr("value") + doc.select("humidity").attr("unit")),
					L10N.getString("w.pressure", channel).replace("#pressure", Colors.NORMAL + doc.select("pressure").attr("value") + doc.select("pressure").attr("unit")),
					L10N.getString("w.wind", channel).replace("#wind", Colors.NORMAL + getWindSpeed(doc)),
					L10N.getString("w.updated", channel).replace("#date", Colors.NORMAL + doc.select("lastupdate").attr("value").replace("T", " ") + " GMT"),
					L10N.getString("w.credit", channel).replace("#link", Colors.NORMAL + "http://openweathermap.org/city/" + doc.select("city").attr("id")));
		}
		catch(Exception e)
		{
			Logging.stackTrace(e);
			Utilities.sendMessage(channel, L10N.getString("w.cityNotFound", channel).replace("#city", city.trim()).replace("#smiley", ":/"));
		}
	}

	private String getTemperature(Document doc)
	{
		double kelvin = Double.parseDouble(doc.select("temperature").attr("value"));
		String celsius = Utilities.formatDouble(kelvin - 273.15D);
		
		return celsius + "°C | " + Utilities.formatDouble(Double.parseDouble(celsius) * (9D / 5D) + 32D) + "°F | " + kelvin + "K";
	}

	private String getWindSpeed(Document doc)
	{
		double ms = Double.parseDouble(doc.select("speed").attr("value"));
		
		return ms + " m/s | " + Utilities.formatDouble(ms * 2.2369362920544) + " mph " + (doc.select("direction").attr("code"));
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
