package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Forge extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IOException, IncorrectCommandExecutionException
	{
		if(args.length == 3)
		{
			Document doc;
			String channel = event.getChannel().getName();
			
			try
			{
				doc = Jsoup.connect("http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_" + args[0] + ".html").get();
			}
			catch(HttpStatusException e)
			{
				Utilities.sendMessage(channel, L10N.getString("forge.incorrectMcVersion", channel));
				return;
			}

			String msg = "";
			
			switch(args[1])
			{
				case "latest":
					switch(args[2])
					{
						case "version":
							try
							{
								msg = doc.select("div.download:nth-child(1) > div:nth-child(1) > small:nth-child(3)").get(0).toString().split(">")[1];
							}
							catch(Exception e)
							{
								msg = L10N.getString("forge.versionNotFound", channel);
							}
							break;
						case "changelog":
							try
							{
								msg = doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").get(0).toString().split("href=\"")[1].split("\"")[0];
							}
							catch(Exception e)
							{
								msg = L10N.getString("forge.changelogNotFound", channel);
							}
							break;
						case "dlmain":
							try
							{
								msg = doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0];
							}
							catch(Exception e)
							{
								msg = L10N.getString("forge.mainNotFound", channel);
							}
							break;
						case "dlsrc": case "dlmdk":
							try
							{
								msg = doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0];
							}
							catch(Exception e)
							{
								msg = L10N.getString("forge.srcNotFound", channel);
							}
							break;
						default:
							throw new IncorrectCommandExecutionException(getMainAlias());
					}
					break;
				case "rec": case "recommended":
					switch(args[2])
					{
						case "version":
							try
							{
								msg = doc.select("div.download:nth-child(2) > div:nth-child(1) > small:nth-child(3)").get(0).toString().split(">")[1];
							}
							catch(Exception e)
							{
								msg = L10N.getString("forge.versionNotFound", channel);
							}
							break;
						case "changelog":
							try
							{
								msg = doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").get(0).toString().split("href=\"")[1].split("\"")[0];
							}
							catch(Exception e)
							{
								msg = L10N.getString("forge.changelogNotFound", channel);
							}
							break;
						case "dlmain":
							try
							{
								msg = doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0];
							}
							catch(Exception e)
							{
								msg = L10N.getString("forge.mainNotFound", channel);
							}
							break;
						case "dlsrc": case "dlmdk":
							try
							{
								msg = doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0];
							}
							catch(Exception e)
							{
								msg = L10N.getString("forge.srcNotFound", channel);
							}
							break;
						default:
							throw new IncorrectCommandExecutionException(getMainAlias());
					}
					break;
				default:
					throw new IncorrectCommandExecutionException(getMainAlias());
			}
			
			Utilities.sendMessage(channel, Colors.BOLD + msg.replace("\n", Colors.NORMAL).replace("<!-- b", "(B").replace(" --", ")"));
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"forge"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-forge <" + L10N.getString("forge.version", channel) + "> <latest|rec|recommended> <version|changelog|dlmain|dlsrc>";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				L10N.getString("forge.explanation.1", channel),
				L10N.getString("forge.explanation.2", channel),
				L10N.getString("forge.explanation.3", channel),
				L10N.getString("forge.explanation.4", channel),
				L10N.getString("forge.explanation.5", channel),
		};
	}
}
