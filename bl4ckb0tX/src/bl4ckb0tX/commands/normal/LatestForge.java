package bl4ckb0tX.commands.normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class LatestForge
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event) throws MalformedURLException, IOException
	{
		try
		{
			String buffer = "x";
			String action = Stuffz.seperate(event);
			StringBuilder builder = new StringBuilder();
			BufferedReader mainReader = new BufferedReader(new InputStreamReader(new URL("http://files.minecraftforge.net/").openStream()));

			if(action.equals("version"))
			{
				Stuffz.respond(event, "the latest version is: " + getLatestForgeVersion(buffer, builder, mainReader), true);
			}
			else if(action.equals("changelog"))
			{
				String latestForge = getLatestForgeVersion(buffer, builder, mainReader);
				String changelogUrl = "http://files.minecraftforge.net/maven/net/minecraftforge/forge/" + latestForge + "/forge-" + latestForge + "-changelog.txt";

				Stuffz.respond(event, "here is the changelog for Forge build " + latestForge + ": " + changelogUrl, true);
			}
			else if(action.equals("dlsrc"))
			{
				while(mainReader.readLine() != null)
				{
					buffer = mainReader.readLine();

					if(buffer.contains("<td>1.7.2-Latest</td>"))
					{
						while(mainReader.readLine() != null)
						{
							buffer = mainReader.readLine();

							if(buffer.endsWith("Src</a>)"))
							{
								String[] filter1 = buffer.split("\"");
								String[] filter2 = filter1[1].split("y/");
								String dl = filter2[1].substring(7);
								Stuffz.respond(event, "this is the download to the latest source of Forge: " + dl, true);
								break;
							}
						}
					}
				}

				Stuffz.respond(event, "I couldn't find a proper version, I'm sorry :(", true);
			}
			else if(action.equals("dlmain"))
			{
				while(mainReader.readLine() != null)
				{
					buffer = mainReader.readLine();

					if(buffer.contains("<td>1.7.2-Latest</td>"))
					{
						while(mainReader.readLine() != null)
						{
							buffer = mainReader.readLine();

							if(buffer.endsWith("Installer</a>)"))
							{
								String[] filter1 = buffer.split("\"");
								String[] filter2 = filter1[1].split("y/");
								String dl = filter2[1].substring(7);
								Stuffz.respond(event, "this is the download to the latest installer of Forge: " + dl, true);
								break;
							}
						}
					}
				}

				Stuffz.respond(event, "I couldn't find a proper version, I'm sorry :(", true);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			Stuffz.chanMsg(event, "Available commands: 'version' | 'changelog' | 'dlsrc' | 'dlmain' - Usage: -latestforge <command>");
		}
	}

	private static String getLatestForgeVersion(String buffer, StringBuilder builder, BufferedReader mainReader) throws IOException
	{
		while(mainReader.readLine() != null)
		{
			buffer = mainReader.readLine();

			if(buffer.contains("<td>1.7.2-Latest</td>"))
			{
				String[] mcVersion = buffer.split(">");

				builder.append(mcVersion[1].substring(0, 5) + "-");
				buffer = mainReader.readLine();

				String[] version = buffer.split(">");

				builder.append(version[1].substring(0, 12));
				return builder.toString();
			}
		}
		return "nullnullnull";
	}
}