package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Changelog implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/bl4ckscor3/bl4ckb0t/master/CHANGELOG.md").openStream()));
		HashMap<String, ArrayList<String>> versions = new HashMap<String, ArrayList<String>>();
		String line = "";
		String currentVersion = "";
		
		while((line = reader.readLine()) != null)
		{
			if(startsWithNumber(line))
			{
				currentVersion = line;
				versions.put(currentVersion, new ArrayList<String>());
			}
			
			if(line.startsWith("-"))
				versions.get(currentVersion).add(line);
		}
		
		if(args.length == 2)
		{
			if(!versions.containsKey(args[1]))
			{
				Utilities.chanMsg(event, L10N.strings.getString("changelog.versionNotFound"));
				return;
			}
			
			for(String s : versions.get(args[1]))
			{
				Utilities.chanMsg(event, s);
			}
			
			return;
		}
		
		for(String s : versions.get(Core.bot.getConfiguration().getVersion()))
		{
			Utilities.chanMsg(event, s);
		}
	}

	private void printHashmap(HashMap<String, ArrayList<String>> versions)
	{
		Iterator<Entry<String, ArrayList<String>>> iterator = versions.entrySet().iterator();
		
		while(iterator.hasNext())
		{
			Entry<String, ArrayList<String>> pair = iterator.next();
			
			System.out.println(pair.getKey() + "=" + pair.getValue());
		}
	}
	
	private boolean startsWithNumber(String line)
	{
		return line.startsWith("0") || line.startsWith("1") || line.startsWith("2") || line.startsWith("3") || line.startsWith("4") || line.startsWith("5") || line.startsWith("6") || line.startsWith("7") || line.startsWith("8") || line.startsWith("9");
	}
	
	@Override
	public String getAlias()
	{
		return "changelog";
	}

	@Override
	public String getSyntax()
	{
		return "-changelog [" + L10N.strings.getString("changelog.versionNumber") + "]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-changelog || " + L10N.strings.getString("changelog.explanation.1"),
				"-changelog <" + L10N.strings.getString("changelog.versionNumber") + "> || " + L10N.strings.getString("changelog.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("changelog.notes");
	}
}
