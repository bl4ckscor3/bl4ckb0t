package bl4ckscor3.bot.bl4ckb0t;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.modules.Changelog;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Startup
{
	/**
	 * Calls all methods in this class
	 */
	public static void callMethods() throws MalformedURLException, IOException
	{
		Logging.info("Getting changelog");
		getChangelog();
		Logging.info("Getting allowed users");
		setLvl2Users();
		Logging.info("Getting valid users");
		setLvl3Users();
		Logging.info("Getting users to ignore");
		setIgnoredUsers();
	}

	/**
	 * Retrieves the changelog of the bot and saves it in the list Changelog.versions
	 */
	private static void getChangelog()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/bl4ckscor3/bl4ckb0t/master/CHANGELOG.md").openStream()));
			String line = "";
			String currentVersion = "";
			boolean wip = false;

			while((line = reader.readLine()) != null)
			{
				line = line.replace("#", "").replace("*", "");

				if(Utilities.startsWithNumber(line))
				{
					if(line.endsWith("_WIP"))
					{
						wip = true;
						continue;
					}

					wip = false;
					currentVersion = line;
					Changelog.versions.put(currentVersion, new ArrayList<String>());
				}

				if(line.startsWith("-") && !line.startsWith("---") && !wip)
					Changelog.versions.get(currentVersion).add(line);
			}

			reader.close();
			Logging.info("All versions added to changelog list.");
		}
		catch(IOException e)
		{
			Logging.severe("Changelog could not be loaded!");
			Logging.stackTrace(e);
		}
	}

	/**
	 * Sets the default channels of the bot
	 */
	public static void setDefaultChans() throws MalformedURLException, IOException
	{
		if(Core.wasStartedAsWIP)
		{
			Lists.addDefaultChan("#bl4ckb0tTest");
			return;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://akino.canopus.uberspace.de/bl4ckb0t/files/defaultChannels.txt").openStream()));
		String line = "";

		while((line = reader.readLine()) != null)
		{
			Lists.addDefaultChan(line);
		}

		reader.close();
	}

	/**
	 * Sets the users with a permission level of 2
	 */
	private static void setLvl2Users() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://akino.canopus.uberspace.de/bl4ckb0t/files/allowedUsers.txt").openStream()));
		String line = "";

		while((line = reader.readLine()) != null)
		{
			Lists.addLvl2User(line);
		}

		reader.close();
	}

	/**
	 * Sets the users with a permission level of 3
	 */
	private static void setLvl3Users() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://akino.canopus.uberspace.de/bl4ckb0t/files/validUsers.txt").openStream()));
		String line = "";

		while((line = reader.readLine()) != null)
		{
			Lists.addLvl3User(line);
		}

		reader.close();
	}

	/**
	 * Sets the user the bot ignores
	 */
	private static void setIgnoredUsers() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://akino.canopus.uberspace.de/bl4ckb0t/files/ignoredUsers.txt").openStream()));
		String line = "";

		while((line = reader.readLine()) != null)
		{
			Lists.addIgnoredUser(line);
		}

		reader.close();
	}
}
