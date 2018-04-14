package bl4ckscor3.module.sctools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class SCTools extends Module
{
	private Listener listener;
	private final HashMap<String, Boolean> scUsers = new HashMap<String, Boolean>();

	public SCTools(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader) throws IOException
	{
		getBuilder().addListener(listener = new Listener());
	}

	@Override
	public void onDisable()
	{
		getBuilder().removeListener(listener);
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"None."};
	}

	public class Listener extends ListenerAdapter
	{
		@Override
		public void onMessage(MessageEvent event)
		{
			String user = event.getUser().getNick();
			String channel = event.getChannel().getName();

			if(user.startsWith("SCUser_") && event.getMessage().startsWith("SecurityCraft version: "))
				Utilities.sendMessage(channel, user + ", Hi! Please note, that IRC support has stopped as of 14th April 2018. Please use our Discord server from now on: https://discord.gg/U8DvBAW");
		}

		@Override
		public void onJoin(JoinEvent event) throws Exception
		{
			if(!event.getUser().getNick().startsWith("SCUser_"))
				return;

			String name = event.getUser().getNick().split("_")[1];
			BufferedReader reader;
			String status;

			if(scUsers.containsKey(name))
			{
				if(!scUsers.get(name))
					Core.bot.kick(event.getChannel().getName(), " SCUser_" + name, "Cracked accounts will not receive support.");
			}
			else
			{
				reader = new BufferedReader(new InputStreamReader(new URL("http://axis.iaero.me/accstatus?username=" + name + "&format=plain").openStream()));
				status = reader.readLine();
				reader.close();

				if(status.equalsIgnoreCase("free"))
				{
					Core.bot.kick(event.getChannel().getName(), " SCUser_" + name, "Cracked accounts will not receive support.");
					scUsers.put(name, false);
				}
				else if(status.equalsIgnoreCase("premium"))
					scUsers.put(name, true);
			}
		}
	}
}