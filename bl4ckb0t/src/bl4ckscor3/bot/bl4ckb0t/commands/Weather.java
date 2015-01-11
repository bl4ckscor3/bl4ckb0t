package bl4ckscor3.bot.bl4ckb0t.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Weather implements ICommand<MessageEvent>
{
	private boolean error = false;

	@Override
	public void exe(MessageEvent event) throws Exception 
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] data = new String[19];//1 = name | 3 = country | 6 = temperature | 7 = humidity | 8 = pressure | 10 = wind speed | 11 = wind direction | 13 = clouds

		if(args.length == 2)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + args[1] + "&mode=xml").openStream()));	
			
			if(reader.readLine().equalsIgnoreCase("{\"message\":\"Error: Not found city\",\"cod\":\"404\"}"))
				Utilities.chanMsg(event, "Sorry, I couldn't find a city named \"" + args[1] + "\" :/");
			else
			{
				for(int i = 0; i < 19; i++)
				{
					data[i] = reader.readLine();
				}
				
				filter(data);
				
				if(!error)
					Utilities.chanMsg(event, "** " + data[1] + ", " + data[3] + " ** Conditions: " + data[13] + " ** Temperature: " + data[6] + " ** Humidity: " + data[7] + " ** Pressure: " + data[8] + " ** Wind: " + data[11] + ", with " + data[10] + " ** Powered by OpenWeatherMap - http://openweathermap.org/city/" + data[1] + "/ **");
				else
					Utilities.chanMsg(event, "** " + data[1] + ", " + data[3] + " ** Conditions: " + data[13] + " ** Temperature: " + data[6] + " ** Humidity: " + data[7] + " ** Pressure: " + data[8] + " ** Wind: " + data[11] + " ** Powered by OpenWeatherMap - http://openweathermap.org/city/" + data[1] + "/ **");
			}
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}

	private void filter(String[] data)
	{
		String[] temp = data;
		double kentucky;
		double fried;
		double chicken;
		
		data[1] = temp[1].split("\"")[3];
		data[3] = temp[3].split(">")[1].split("<")[0];
		kentucky = Double.parseDouble(temp[6].split("\"")[1]);
		fried = Math.round((9D / 5D * (kentucky - 273.15D) + 32D) * 100D) / 100D;
		chicken = Math.round((5D / 9D * (fried - 32D)) * 100D) / 100D;
		data[6] = chicken + "°C | " + fried + "°F | " + kentucky + "K";	
		data[7] = temp[7].split("\"")[1] + "%";
		data[8] = temp[8].split("\"")[1] + "hPa";
		
		try
		{
			data[10] = temp[10].split("\"")[1] + "m/s (" + temp[10].split("\"")[3] + ")";
			data[11] = temp[11].split("\"")[5];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			temp = data;
			data[10] = temp[10].split("\"")[1] + "m/s";
			error = true;
		}
		
		data[13] = temp[13].split("\"")[3];
	}
	
	@Override
	public String getAlias() 
	{
		return "w";
	}

	@Override
	public String getSyntax()
	{
		return "-w <city>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-w <city> || Shows you an up-to-date weather report for the given city."};
	}

	@Override
	public String getNotes()
	{
		return "Some cities, such as Amsterdam, exist multiple times. Please use the country code to specify the city you want: -w amsterdam,nl (This will give the Amsterdam in the Netherlands). If your city contains a space, use an underscore (_) instead.";
	}
}
