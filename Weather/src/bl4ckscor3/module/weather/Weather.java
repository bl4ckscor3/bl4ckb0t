package bl4ckscor3.module.weather;

import java.net.URLClassLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Weather extends Module
{
	private L10N l10n;
	
	public Weather(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command(this));
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
		private Module module;
		
		public Command(Module m)
		{
			module = m;
		}
		
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String city = "";
			String channel = event.getChannel().getName();
			
			for(String s : args)
			{
				city += s + " ";
			}
			
			if(city.equals(""))
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
			
			try
			{
				Document doc = Jsoup.connect("http://api.openweathermap.org/data/2.5/weather?q=" + city.trim() + "&mode=xml&APPID=" + Passwords.WEATHERAPIKEY.getPassword()).ignoreContentType(true).get();

				Utilities.sendStarMsg(channel, doc.select("city").attr("name") + ", " + doc.select("country").text(),
						l10n.translate("temperature", channel).replace("#temperature", getTemperature(doc)),
						l10n.translate("humidity", channel).replace("#humidity", doc.select("humidity").attr("value") + doc.select("humidity").attr("unit")),
						l10n.translate("pressure", channel).replace("#pressure", doc.select("pressure").attr("value") + doc.select("pressure").attr("unit")),
						l10n.translate("wind", channel).replace("#wind", getWindSpeed(doc)),
						l10n.translate("updated", channel).replace("#date", doc.select("lastupdate").attr("value").replace("T", " ") + " GMT"),
						l10n.translate("credit", channel).replace("#link", "http://openweathermap.org/city/" + doc.select("city").attr("id")));
			}
			catch(Exception e)
			{
				Logging.stackTrace(e);
				Utilities.sendMessage(channel, l10n.translate("cityNotFound", channel).replace("#city", city.trim()).replace("#smiley", ":/"));
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
			return new String[]{"weather", "w"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}