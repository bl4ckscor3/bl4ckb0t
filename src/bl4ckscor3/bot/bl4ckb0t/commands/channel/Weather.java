package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Weather implements ICommand<MessageEvent<Bot>>
{
	private boolean error = false;

	@Override
	public void exe(MessageEvent<Bot> event) throws IOException, IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] data = new String[19];//1 = name | 3 = country | 6 = temperature | 7 = humidity | 8 = pressure | 10 = wind speed | 11 = wind direction | 13 = clouds

		if(args.length == 2)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + args[1] + "&mode=xml").openStream()));	
			
			if(reader.readLine().equalsIgnoreCase("{\"message\":\"Error: Not found city\",\"cod\":\"404\"}"))
				Utilities.chanMsg(event, L10N.getString("w.cityNotFound") + " \"" + args[1] + "\" :/");
			else
			{
				for(int i = 0; i < 19; i++)
				{
					data[i] = reader.readLine();
				}
				
				filter(data);
				
				if(!error)
					Utilities.chanMsg(event, Colors.BOLD + "** " + Colors.BOLD + data[1] + ", " + data[3] +
							Colors.BOLD + " ** " + L10N.getString("w.conditions") + ": " + Colors.BOLD + data[13] +
							Colors.BOLD + " ** " + L10N.getString("w.temperature") + ": " + Colors.BOLD + data[6] +
							Colors.BOLD + " ** " + L10N.getString("w.humidity") + ": " + Colors.BOLD + data[7] +
							Colors.BOLD + " ** " + L10N.getString("w.pressure") + ": " + Colors.BOLD + data[8] +
							Colors.BOLD + " ** " + L10N.getString("w.wind.1") + ": " + Colors.BOLD + data[11] + ", " + L10N.getString("w.wind.2") + " " + data[10] +
							Colors.BOLD + " ** " + L10N.getString("w.credit") + data[1] + "/ **");
				else
					Utilities.chanMsg(event, "** " + data[1] + ", " + data[3] + " ** " + L10N.getString("w.conditions") + ": " + data[13] +
							" ** " + L10N.getString("w.temperature") + ": " + data[6] +
							" ** " + L10N.getString("w.humidity") + ": " + data[7] +
							" ** " + L10N.getString("w.pressure") + ": " + data[8] +
							" ** " + L10N.getString("w.wind.1") + ": " + data[11] +
							" ** " + L10N.getString("w.credit") + data[1] + "/ **");
			}
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
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
		return "-w <" + L10N.getString("w.help.city") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-w <" + L10N.getString("w.help.city") + "> || " + L10N.getString("w.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.getString("w.notes");
	}
}
