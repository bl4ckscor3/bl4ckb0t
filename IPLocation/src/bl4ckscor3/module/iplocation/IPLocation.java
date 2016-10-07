package bl4ckscor3.module.iplocation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class IPLocation extends Module
{
	private L10N l10n;

	public IPLocation(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command(this));
		l10n = new L10N(this, loader);
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}

	@Override
	public String getNotes(String channel)
	{
		return l10n.translate("notes", channel);
	}

	public class Command extends BaseChannelCommand
	{
		public Module module;

		public Command(Module m)
		{
			module = m;
		}

		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String channel = event.getChannel().getName();

			if(args.length == 1)
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://freegeoip.net/xml/" + args[0]).openStream()));
				String s = "";
				String country = "";
				String region = "";
				String city = "";

				if(reader.readLine().contains("404"))
				{
					Utilities.sendMessage(channel, l10n.translate("404", channel));
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

				Utilities.sendStarMsg(channel, l10n.translate("country", channel).replace("#", country),
						l10n.translate("region", channel).replace("#",region),
						l10n.translate("city", channel).replace("#", city),
						l10n.translate("credit", channel).replace("#", "http://freegeoip.net"));
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"iplocation", "iploc", "ip", "loc"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}