package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import bl4ckscor3.bot.bl4ckb0t.util.Lists;

public class Startup
{
	public static void setAutoJoinChans() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/tishdl84z1wmcgs/bl4ckb0t%20chans.txt?dl=1").openStream()));

		if(Core.bot.getNick().equals("bl4ckb0t"))
		{
			for(String s : reader.readLine().split(","))
			{
				Lists.addDefaultChan(s);
			}
		}
		else
		{
			Lists.addDefaultChan("#bl4ckb0tTest");
			Lists.addDefaultChan("#whateveragain");
		}
	}

	public static void setAllowedUsers() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/0flrfzw3ljmw3u2/allowedUsers.txt?dl=1").openStream()));
		
		for(String s : reader.readLine().split(","))
		{
			Lists.addAllowedUser(s);
		}
	}
	
	public static void setValidUsers() throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/dyvu276rdwmbt9z/validUsers.txt?dl=1").openStream()));

		for(String s : reader.readLine().split(","))
		{
			Lists.addValidUser(s);
		}
	}
}
