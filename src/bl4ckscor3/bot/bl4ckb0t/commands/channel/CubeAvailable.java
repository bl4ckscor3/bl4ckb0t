package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class CubeAvailable implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event)
	{
		Timer t = new Timer();
		
		t.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				List<String> lines = new ArrayList<String>();
				BufferedReader reader;
				
				try
				{
					reader = new BufferedReader(new InputStreamReader(new URL("http://cubezz.com/Buy-4459-Ganspuzzle+III+57mm+3x3x3+Speed+Cube+Magic+Cube+Black+Gans3-57.html").openStream()));
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return;
				}
				
				for(int i = 0; i < 500; i++)
				{
					try
					{
						String s = reader.readLine();
						lines.add(s);
					}
					catch (IOException e)
					{
						e.printStackTrace();
						continue;
					}
					
				}
				
				if(!lines.contains("        <b>Status</b> :                  Out of stock"))
				{
					if(lines.contains("        <b>Status</b> :                  In stock"))
					{
						Utilities.chanMsg(event, "bl4ckscor3, Cube ist da!");
						t.cancel();
					}
				}
			}
		}, 0, 3600000); //1 hour
	}

	@Override
	public String getAlias()
	{
		return "cube";
	}

	@Override
	public String getSyntax()
	{
		return null;
	}

	@Override
	public String[] getUsage()
	{
		return null;
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
