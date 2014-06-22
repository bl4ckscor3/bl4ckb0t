package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Forge implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 3)
		{
			if(args[1].equalsIgnoreCase("latest") || args[1].equalsIgnoreCase("latets"))
			{
				if(args[2].equalsIgnoreCase("version"))
					Utilities.chanMsg(event, createResult("<td>Latest</td>", "Version"));
				else if(args[2].equalsIgnoreCase("changelog"))
					Utilities.chanMsg(event, createResult("<td>Latest</td>", "Changelog"));
				else if(args[2].equalsIgnoreCase("dlmain"))
					Utilities.chanMsg(event, createResult("<td>Latest</td>", "Installer"));
				else if(args[2].equalsIgnoreCase("dlsrc"))
					Utilities.chanMsg(event, createResult("<td>Latest</td>", "Src"));
				else
					usage(event);
			}
			else if(args[1].equalsIgnoreCase("rec") || args[1].equalsIgnoreCase("recommended"))
			{
				if(args[2].equalsIgnoreCase("version"))
					Utilities.chanMsg(event, createResult("<td>Recommended</td>", "Version"));
				else if(args[2].equalsIgnoreCase("changelog"))
					Utilities.chanMsg(event, createResult("<td>Recommended</td>", "Changelog"));
				else if(args[2].equalsIgnoreCase("dlmain"))
					Utilities.chanMsg(event, createResult("<td>Recommended</td>", "Installer"));
				else if(args[2].equalsIgnoreCase("dlsrc"))
					Utilities.chanMsg(event, createResult("<td>Recommended</td>", "Src"));
				else
					usage(event);
			}
			else
				usage(event);
		}
		else
			usage(event);
	}

	private String createResult(String type, String action) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://files.minecraftforge.net").openStream()));
		String line;

		if(action.equalsIgnoreCase("Version"))
		{
			while((line = reader.readLine()) != null)
			{
				if(line.contains(type))
				{
					line = reader.readLine();
					return line.split(">")[1].split("<")[0];
				}
			}
		}
		else if(action.equalsIgnoreCase("Changelog"))
		{
			while((line = reader.readLine()) != null)
			{
				if(line.contains(type))
				{
					while((line = reader.readLine()) != null)
					{
						if(line.contains(action))
							return line.split("\"")[1];
					}
				}
			}
		}
		else
		{
			while((line = reader.readLine()) != null)
			{
				if(line.contains(type))
				{
					while((line = reader.readLine()) != null)
					{
						if(line.contains(action))
						{
							line = reader.readLine();
							return line.split("\"")[1];
						}
					}
				}
			}
		}
		return "Couldn't create a result.";
	}

	private void usage(MessageEvent event)
	{
		Utilities.chanMsg(event, "Usage: -forge <latest|rec|recommended> <version|changelog|dlmain|dlsrc>");
	}
	
	@Override
	public String getAlias()
	{
		return "forge";
	}
}