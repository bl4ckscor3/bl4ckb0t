package bl4ckscor3.bot.bl4ckb0t.util;

import java.net.URISyntaxException;
import java.util.Date;

import bl4ckscor3.bot.bl4ckb0t.Core;

public class Utilities
{
	/**
	 * Gets the current time from UNIX
	 */
	public static Date getCurrentDate()
	{
		return new Date(System.currentTimeMillis());
	}

	/**
	 * Gets the path of the running jar file
	 */
	public static String getJarLocation() throws URISyntaxException
	{
		String path = Core.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

		if(path.endsWith(".jar"))
			path = path.substring(0, path.lastIndexOf("/"));

		return path;
	}

	/**
	 * Splits a command into its arguments, leaving out the command itself
	 * @param line The string to split
	 * @return An array of all the arguments
	 */
	public static String[] toArgs(String line)
	{
		String[] previous = line.split(" ");
		String[] result = new String[previous.length - 1];
		
		for(int i = 1; i < previous.length; i++)
		{
			result[i - 1] = previous[0];
		}
		
		return result;
		
	}
}
