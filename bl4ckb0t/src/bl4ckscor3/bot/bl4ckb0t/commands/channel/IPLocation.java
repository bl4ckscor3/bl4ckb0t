package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class IPLocation extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		String channel = event.getChannel().getName();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://freegeoip.net/xml/" + args[0]).openStream()));
		String s = "";
		String country = "";
		String region = "";
		String city = "";
		
		if(reader.readLine().contains("404"))
		{
			Utilities.sendMessage(channel, L10N.getString("iplocation.404", channel));
			return;
		}
		
		while((s = reader.readLine()) != null)
		{
			if(s.contains("CountryName"))
				country = s.split(">")[1].split("<")[0];
			else if(s.contains("RegionName"))
				region = s.split(">")[1].split("<")[0];
			else if(s.contains("City"))
				city = s.split(">")[1].split("<")[0];
		}
		
		Utilities.sendStarMsg(channel, L10N.getString("iplocation.country", channel).replace("#", Colors.NORMAL + country),
				L10N.getString("iplocation.region", channel).replace("#", Colors.NORMAL + region),
				L10N.getString("iplocation.city", channel).replace("#", Colors.NORMAL + city),
				L10N.getString("iplocation.credit", channel).replace("#", Colors.NORMAL + "http://freegeoip.net"));
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"iplocation", "iploc", "ip", "loc"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-iplocation <ip>";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-iplocation <ip> || " + L10N.getString("iplocation.explanation", channel)};
	}
	
	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("iplocation.notes", channel);
	}
}
