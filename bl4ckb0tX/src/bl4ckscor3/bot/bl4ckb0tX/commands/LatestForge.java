package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class LatestForge implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws MalformedURLException, IOException
	{
		String buffer = "x";
		String[] args = Utilities.toArgs(event.getMessage());
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://files.minecraftforge.net/").openStream()));

		for(int i = 0; i < args.length; i++)
		{
			System.out.println(i + "   " + args[i]);
		}

		if(args.length == 2)
		{
			switch(args[1])
			{
				case "version":
					Utilities.respond(event, "the latest version is: " + getLatestForgeVersion(buffer, builder, reader), true);
					break;
				case "changelog":
					String latestForge = getLatestForgeVersion(buffer, builder, reader);
					String changelogUrl = "http://files.minecraftforge.net/maven/net/minecraftforge/forge/" + latestForge + "/forge-" + latestForge + "-changelog.txt";

					Utilities.respond(event, "here is the changelog for Forge build " + latestForge + ": " + changelogUrl, true);
					break;
				case "dlsrc":
					while(reader.readLine() != null)
					{
						buffer = reader.readLine();

						if(buffer.contains("<td>1.7.2-Latest</td>"))
						{
							while(reader.readLine() != null)
							{
								buffer = reader.readLine();

								if(buffer.endsWith("Src</a>)"))
								{
									String[] filter1 = buffer.split("\"");
									String[] filter2 = filter1[1].split("y/");
									String dl = filter2[1].substring(7);
									Utilities.respond(event, "this is the download to the latest source of Forge: " + dl, true);
									break;
								}
							}
						}
					}

					Utilities.respond(event, "I couldn't find a proper version, I'm sorry :(", true);
					break;
				case "dlmain":
					while(reader.readLine() != null)
					{
						buffer = reader.readLine();

						if(buffer.contains("<td>1.7.2-Latest</td>"))
						{
							while(reader.readLine() != null)
							{
								buffer = reader.readLine();

								if(buffer.endsWith("Installer</a>)"))
								{
									String[] filter1 = buffer.split("\"");
									String[] filter2 = filter1[1].split("y/");
									String dl = filter2[1].substring(7);
									Utilities.respond(event, "this is the download to the latest installer of Forge: " + dl, true);
									break;
								}
							}
						}
					}

					Utilities.respond(event, "I couldn't find a proper version, I'm sorry :(", true);
					break;
				default:
					Utilities.userMsg(event, "Available commands: 'version' | 'changelog' | 'dlsrc' | 'dlmain' - Usage: -latestforge <command>");
			}
		}
		else
			Utilities.userMsg(event, "Available commands: 'version' | 'changelog' | 'dlsrc' | 'dlmain' - Usage: -latestforge <command>");
	
		reader.close();
	}

	private String getLatestForgeVersion(String buffer, StringBuilder builder, BufferedReader reader) throws IOException
	{
		while(reader.readLine() != null)
		{
			buffer = reader.readLine();

			if(buffer.contains("<td>1.7.2-Latest</td>"))
			{
				String[] mcVersion = buffer.split(">");

				builder.append(mcVersion[1].substring(0, 5)).append("-");
				buffer = reader.readLine();

				String[] version = buffer.split(">");

				builder.append(version[1].substring(0, 12));
				return builder.toString();
			}
		}
		return "nullnullnull";
	}

	@Override
	public String getAlias()
	{
		return "latestforge";
	}
}