package bl4ckb0tX.commands.normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

@SuppressWarnings("rawtypes")
public class Bukkit
{
	public static void exe(MessageEvent event) throws IOException
	{
		String buffer = "x";
		String[] args = Utilities.toArgs(event.getMessage());
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://dl.bukkit.org/downloads/bukkit/").openStream()));

		if(args.length == 2)
		{
			if(args[1].equals("rec"))
			{
				for(int i = 0; i < 1301; i++)
				{
					buffer = reader.readLine();

					if(buffer.contains("downloadButton chan-rb  mini"))
					{
						while(!buffer.contains("href"))
						{
							buffer = reader.readLine();
						}

						String[] link = buffer.split("\"");

						while(!buffer.contains("title"))
						{
							buffer = reader.readLine();
						}

						String[] version = buffer.split("n");

						builder.append(version[2]);
						builder.deleteCharAt(0);
						builder.delete(version[2].length() - 3, version[2].length());

						Utilities.respond(event, Colors.GREEN + "the version of the latest recommended Bukkit build is " + builder + ". Download it here: http://dl.bukkit.org" + link[1], true);
						return;
					}
				}
			}
			else if(args[1].equals("beta"))
			{
				for(int i = 0; i < 1301; i++)
				{
					buffer = reader.readLine();

					if(buffer.contains("downloadButton chan-beta  mini"))
					{
						while(!buffer.contains("href"))
						{
							buffer = reader.readLine();
						}

						String[] link = buffer.split("\"");

						while(!buffer.contains("title"))
						{
							buffer = reader.readLine();
						}

						String[] version = buffer.split("n");

						builder.append(version[2]);
						builder.deleteCharAt(0);
						builder.delete(version[2].length() - 3, version[2].length());

						Utilities.respond(event, Colors.YELLOW + "the version of the latest Bukkit Beta build is " + builder + ". Download it here: http://dl.bukkit.org" + link[1], true);
						return;
					}
				}
			}
			else if(args[1].equals("dev"))
			{
				for(int i = 0; i < 1301; i++)
				{
					buffer = reader.readLine();

					if(buffer.contains("downloadButton chan-dev  mini"))
					{
						while(!buffer.contains("href"))
						{
							buffer = reader.readLine();
						}

						String[] link = buffer.split("\"");

						while(!buffer.contains("title"))
						{
							buffer = reader.readLine();
						}

						String[] version = buffer.split("n");

						builder.append(version[2]);
						builder.deleteCharAt(0);
						builder.delete(version[2].length() - 3, version[2].length());

						Utilities.respond(event, Colors.RED + "the version of the latest Bukkit Development build is " + builder + ". Download it here: http://dl.bukkit.org" + link[1], true);
						return;
					}
				}
			}
			else			
				Utilities.chanMsg(event, "Available commands: 'rec' | 'beta' | 'dev' - Usage: -bukkit <command>");
		}
		else
			Utilities.chanMsg(event, "Available commands: 'rec' | 'beta' | 'dev' - Usage: -bukkit <command>");
	}
}
