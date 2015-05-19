package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.pircbotx.hooks.events.JoinEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;

public class CrackedKicker
{
	private static final HashMap<String, Boolean> scUsers = new HashMap<String, Boolean>(); //name, is premium

	public static void check(JoinEvent<Bot> event) throws MalformedURLException, IOException
	{
		String name = event.getUser().getNick().split("_")[1];
		BufferedReader reader;
		String status;

		if(scUsers.containsKey(name))
		{
			if(!scUsers.get(name))
				Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " SCUser_" + name + " :We don't need cracked accounts here.");
		}
		else
		{
			reader = new BufferedReader(new InputStreamReader(new URL("http://axis.iaero.me/accstatus?username=" + name + "&format=plain").openStream()));
			status = reader.readLine();
			reader.close();

			if(status.equalsIgnoreCase("free"))
			{
				Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " SCUser_" + name + " :We don't need cracked accounts here.");
				scUsers.put(name, false);
			}
			else if(status.equalsIgnoreCase("premium"))
				scUsers.put(name, true);
		}
	}
}
