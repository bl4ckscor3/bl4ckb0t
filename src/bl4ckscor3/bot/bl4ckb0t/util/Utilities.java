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
}
