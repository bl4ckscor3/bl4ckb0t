package bl4ckscor3.module.sctools;

import java.io.IOException;
import java.net.URLClassLoader;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class SCTools extends Module
{
	private Listener listener;

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
		public void onJoin(JoinEvent event)
		{
			String user = event.getUser().getNick();
			String channel = event.getChannel().getName();

			if(!user.startsWith("SCUser_"))
				return;

			try
			{
				Utilities.sendMessage(channel, user + ", Hi! Please note, that IRC support has stopped as of 14th April 2018. Please use our Discord server from now on: https://discord.gg/U8DvBAW");
				Core.bot.kick(channel, user, "https://discord.gg/U8DvBAW");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

		}
	}
}