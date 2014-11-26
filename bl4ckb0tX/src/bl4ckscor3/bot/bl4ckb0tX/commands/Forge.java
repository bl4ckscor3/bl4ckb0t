package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Forge implements ICommand<MessageEvent>
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
				{
					String result = createResult("<td>Latest</td>", "Version");

					if(result.equalsIgnoreCase(createResult("<td>Recommended</td>", "Version")))
						Utilities.chanMsg(event, result + " This is also the recommended build.");
					else
						Utilities.chanMsg(event, result);
				}
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
				{
					String result = createResult("<td>Recommended</td>", "Version");

					if(result.equalsIgnoreCase(createResult("<td>Latest</td>", "Version")))
						Utilities.chanMsg(event, result + " This is also the latest build.");
					else
						Utilities.chanMsg(event, result);
				}
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://files.minecraftforge.net/minecraftforge//1.8").openStream()));
		String line;

		if(action.equalsIgnoreCase("Version"))
		{
			while((line = reader.readLine()) != null)
			{
				if(line.contains(type))
				{
					line = reader.readLine();

					String mcVersion = (reader.readLine()).split(">")[1].split("<")[0];

					return line.split(">")[1].split("<")[0] + " (For Minecraft version " + mcVersion + ")";
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
		return "Couldn't create a result. Is there a recommended version already?";
	}

	private void usage(MessageEvent event)
	{
		Utilities.chanMsg(event, "Invalid number of arguments, or invalid argument. Usage: -forge <latest|rec|recommended> <version|changelog|dlmain|dlsrc>");
	}

	@Override
	public String getAlias()
	{
		return "forge";
	}

	@Override
	public String getSyntax()
	{
		return "-forge <latest|rec|recommended> <version|changelog|dlmain|dlsrc>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-forge latest version || Tells you the latest version for Forge inclusive the Minecraft version it is for.",
				"-forge latest changelog || Gives you a link to the changelog of the latest Forge version.",
				"-forge latest dlmain || Gives you a link to the client download of the latest Forge version.",
				"-forge latest dlsrc || Gives you a link to the development download of the latest Forge version.",
				"-forge <rec|recommended> version || Tells you the latest recommended version for Forge inclusive the Minecraft version it is for.",
				"-forge <rec|recommended> changelog || Gives you a link to the changelog of the latest recommended Forge version.",
				"-forge <rec|recommended> dlmain || Gives you a link to the client download of the latest recommended Forge version.",
				"-forge <rec|recommended> dlsrc || Gives you a link to the development download of the latest recommended Forge version."
				};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
