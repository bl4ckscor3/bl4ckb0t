package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class CraftBukkit implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws IOException
	{
		String buffer = "x";
		String[] args = Utilities.toArgs(event.getMessage());
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://dl.bukkit.org/downloads/craftbukkit/").openStream()));

		if(args.length == 2)
		{
			switch(args[1])
			{
				case "rec":
					for(int i = 0; i < 1301; i++)
					{
						buffer = reader.readLine();

						if(buffer.contains("downloadButton chan-rb  mini"))
						{
							//Code for the DL-Link
							while(!buffer.contains("href"))
							{
								buffer = reader.readLine();
							}

							String[] link = buffer.split("\"");

							//Code for the Build version
							String newUrl = "http://dl.bukkit.org" + link[1].replace("get", "view").replace("craftbukkit.jar", "");
							BufferedReader versionReader = new BufferedReader(new InputStreamReader(new URL(newUrl).openStream()));

							while(!buffer.contains("Version:"))
							{
								buffer = versionReader.readLine();
							}

							buffer = versionReader.readLine();
							buffer = versionReader.readLine();

							String version = buffer.substring(12);

							//Output
							Utilities.respond(event, Colors.DARK_GREEN + "the version of the latest recommended Craftbukkit build is " + Colors.BOLD + version + Colors.BOLD + ". Download it here: " + Colors.BOLD + "http://dl.bukkit.org" + link[1], true);
							versionReader.close();
							break;
						}
					}
					break;
				case "beta":
					for(int i = 0; i < 1301; i++)
					{
						buffer = reader.readLine();

						if(buffer.contains("downloadButton chan-beta  mini"))
						{
							//Code for the DL-Link
							while(!buffer.contains("href"))
							{
								buffer = reader.readLine();
							}

							String[] link = buffer.split("\"");

							//Code for the Build version
							String newUrl = "http://dl.bukkit.org" + link[1].replace("get", "view").replace("craftbukkit-beta.jar", "");
							BufferedReader versionReader = new BufferedReader(new InputStreamReader(new URL(newUrl).openStream()));

							while(!buffer.contains("Version:"))
							{
								buffer = versionReader.readLine();
							}

							buffer = versionReader.readLine();
							buffer = versionReader.readLine();

							String version = buffer.substring(12);

							//Output
							Utilities.respond(event, Colors.PURPLE + "the version of the latest Craftbukkit Betabuild is " + Colors.BOLD + version + Colors.BOLD + ". Download it here: " + Colors.BOLD + "http://dl.bukkit.org" + link[1], true);
							versionReader.close();
							break;
						}
					}
					break;
				case "dev":
					for(int i = 0; i < 1301; i++)
					{
						buffer = reader.readLine();

						if(buffer.contains("downloadButton chan-dev  mini"))
						{
							//Code for the DL-Link
							while(!buffer.contains("href"))
							{
								buffer = reader.readLine();
							}

							String[] link = buffer.split("\"");

							//Code for the Build version
							String newUrl = "http://dl.bukkit.org" + link[1].replace("get", "view").replace("craftbukkit-dev.jar", "");
							BufferedReader versionReader = new BufferedReader(new InputStreamReader(new URL(newUrl).openStream()));

							while(!buffer.contains("Version:"))
							{
								buffer = versionReader.readLine();
							}

							buffer = versionReader.readLine();
							buffer = versionReader.readLine();

							String version = buffer.substring(12);

							//Output
							Utilities.respond(event, Colors.RED + "the version of the latest Craftbukkit Devbuild is " + Colors.BOLD + version + Colors.BOLD + ". Download it here: " + Colors.BOLD + "http://dl.bukkit.org" + link[1], true);
							versionReader.close();
							break;
						}
					}
					break;
				default:	
					Utilities.notice(event, "Available commands: 'rec' | 'beta' | 'dev' - Usage: -bukkit <command>");
			}
		}
		else
			Utilities.notice(event, "Available commands: 'rec' | 'beta' | 'dev' - Usage: -bukkit <command>");
		
		reader.close();
	}
	
	@Override
	public String getAlias()
	{
		return "cbukkit";
	}

	@Override
	public String getSyntax() 
	{
		return "-cbukkit <rec|beta|dev>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-cbukkit <rec> || Gives you the link to the latest recommended craftbukkit version.",
				"-cbukkit <beta> || Gives you the link to the latest craftbukkit beta version.",
				"-cbukkit <dev> || Gives you the link to the latest craftbukkit development version."
				};
	}

	@Override
	public String getNotes()
	{
		return "This may currently not work due to Bukkit having issues.";
	}
}
