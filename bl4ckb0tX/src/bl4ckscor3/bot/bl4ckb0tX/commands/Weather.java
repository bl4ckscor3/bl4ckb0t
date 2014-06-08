package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Weather implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception 
	{
		String[] toArgs = Utilities.toArgs(event.getMessage());

		if(toArgs.length == 1)
			Utilities.respond(event, "you need to specify a city!", true);
		else if(toArgs.length == 2)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + toArgs[1] + "&mode=xml").openStream()));
			//1 = id (after customization) | 2 = name | 4 = country | 7 = temp in k | 8 = humidity | 9 = pressure
			//11 = windspeed | 12 = wind direction | 14 = clouds
			String[] lines = new String[19];
			double kelvin = 0;
			double fahrenheit = 0;
			double celsius = 0;

			for(int i = 0; i <= 18; i++)
			{
				lines[i] = reader.readLine();
			}

			if(lines[0].contains("Error"))
			{
				Utilities.respond(event, "I'm afraid, but I couldn't find a city named \"" + toArgs[1] + "\" :/", true);
				return;
			}

			customizeResults(lines, kelvin, fahrenheit, celsius);
			Utilities.chanMsg(event, "** " + lines[2] + ", " + lines[4] + " ** Conditions: " + lines[16] + " ** Temperature: " + lines[7] + " ** Humidity: " + lines[8] + " ** Pressure: " + lines[9] + " ** Wind: " + lines[12] + ", with " + lines[11] + " ** Powered by OpenWeatherMap - http://openweathermap.org/city/" + lines[1] + " **");
		}
		else
			Utilities.respond(event, "I don't know what you did, but you just need to specify a city, nothing else...", true);
	}

	private void customizeResults(String[] lines, double k, double f, double c)
	{
		String[] temp = lines;

		lines[1] = temp[2].split("\"")[1];
		lines[2] = temp[2].split("\"")[3];
		lines[4] = temp[4].split(">")[1].split("<")[0];
		k = Double.parseDouble(temp[7].split("\"")[1]);
		f = Math.round((9D / 5D * (k - 273.15D) + 32D) * 100D) / 100D;
		c = Math.round((5D / 9D * (f - 32D)) * 100D) / 100D;
		lines[7] = c + "°C | " + f + "°F | " + k + "K";		
		lines[8] = temp[8].split("\"")[1] + "%";
		lines[9] = temp[9].split("\"")[1] + "hPa";
		lines[11] = temp[11].split("\"")[1] + "m/s (" + temp[11].split("\"")[3] + ")";
		lines[12] = temp[12].split("\"")[5];
		lines[16] = temp[16].split("\"")[3];
	}
	
	@Override
	public String getAlias() 
	{
		return "w";
	}
}
