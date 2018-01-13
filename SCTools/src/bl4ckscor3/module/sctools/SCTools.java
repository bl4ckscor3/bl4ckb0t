package bl4ckscor3.module.sctools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

import com.google.gson.GsonBuilder;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class SCTools extends Module
{
	private SecurityCraftUpdate latestVersion;
	private Listener listener;
	private final String CF_PAGE = "https://minecraft.curseforge.com/projects/security-craft";
	private final HashMap<String, Boolean> scUsers = new HashMap<String, Boolean>();
	
	public SCTools(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.github.com/Geforce132/SecurityCraft/raw/master/Updates/Geffy.json").openStream()));

		latestVersion = new GsonBuilder().create().fromJson(new File(""), SecurityCraftUpdate.class);
		reader.close();
		getBuilder().addListener(listener = new Listener());
	}

	@Override
	public void onUpdate()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.github.com/Geforce132/SecurityCraft/raw/master/Updates/Geffy.json").openStream()));
	
			latestVersion = new GsonBuilder().create().fromJson(reader, SecurityCraftUpdate.class);
			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
			{
				String userVersion = event.getMessage().split(": ")[1];
				int vc = compareVersions(userVersion.replace("v", "").replace("-hotfix", ""), latestVersion.getVersion().replace("v", "").replace("-hotfix", ""));
				
				if(!userVersion.contains("-beta"))
				{
					if(vc == -1)
						Utilities.sendMessage(channel, String.format("%s, Your version of SecurityCraft (%s) is outdated. The latest version (%s) contains important bugfixes and new features you can't miss out on! - %s", user, userVersion, latestVersion.getVersion(), CF_PAGE));
					else if(vc == 0 && !userVersion.contains("hotfix") && latestVersion.getVersion().contains("hotfix"))
						Utilities.sendMessage(channel, String.format("%s, Your version of SecurityCraft (%s) is outdated. The latest version (%s) contains important bugfixes and new features you can't miss out on! - %s", user, userVersion, latestVersion.getVersion(), CF_PAGE));
				}
				//i'm just gonna not care with beta versions because we're not using that feature anyways
			}
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

		public int compareVersions(String str1, String str2)
		{
			String[] vals1 = str1.split("\\.");
			String[] vals2 = str2.split("\\.");

			int i = 0;

			while(i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i]))
			{
				i++;
			}

			if(i < vals1.length && i < vals2.length)
			{
				int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
				return Integer.signum(diff);
			}
			else
				return Integer.signum(vals1.length - vals2.length);
		}
	}
}