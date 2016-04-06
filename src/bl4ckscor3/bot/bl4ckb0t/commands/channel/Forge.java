package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Forge extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IOException, IncorrectCommandExecutionException
	{
		if(args.length == 4)
		{
			Document doc;

			try
			{
				doc = Jsoup.connect("http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_" + args[1] + ".html").get();
			}
			catch(HttpStatusException e)
			{
				Utilities.chanMsg(event, L10N.getString("forge.incorrectMcVersion", event));
				return;
			}

			switch(args[2])
			{
				case "latest":
					switch(args[3])
					{
						case "version":
							try
							{
								Utilities.chanMsg(event, doc.select("div.download:nth-child(1) > div:nth-child(1) > small:nth-child(3)").get(0).toString().split(">")[1]);
							}
							catch(Exception e)
							{
								Utilities.chanMsg(event, L10N.getString("forge.versionNotFound", event));
							}
							break;
						case "changelog":
							try
							{
								Utilities.chanMsg(event, doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").get(0).toString().split("href=\"")[1].split("\"")[0]);
							}
							catch(Exception e)
							{
								Utilities.chanMsg(event, L10N.getString("forge.changelogNotFound", event));
							}
							break;
						case "dlmain":
							try
							{
								Utilities.chanMsg(event, doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0]);
							}
							catch(Exception e)
							{
								Utilities.chanMsg(event, L10N.getString("forge.mainNotFound", event));
							}
							break;
						case "dlsrc": case "dlmdk":
							try
							{
								Utilities.chanMsg(event, doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0]);
							}
							catch(Exception e)
							{
								Utilities.chanMsg(event, L10N.getString("forge.srcNotFound", event));
							}
							break;
						default:
							throw new IncorrectCommandExecutionException(getMainAlias());
					}
					break;
				case "rec": case "recommended":
					switch(args[3])
					{
						case "version":
							try
							{
								Utilities.chanMsg(event, doc.select("div.download:nth-child(2) > div:nth-child(1) > small:nth-child(3)").get(0).toString().split(">")[1]);
							}
							catch(Exception e)
							{
								Utilities.chanMsg(event, L10N.getString("forge.versionNotFound", event));
							}
							break;
						case "changelog":
							try
							{
								Utilities.chanMsg(event, doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").get(0).toString().split("href=\"")[1].split("\"")[0]);
							}
							catch(Exception e)
							{
								Utilities.chanMsg(event, L10N.getString("forge.changelogNotFound", event));
							}
							break;
						case "dlmain":
							try
							{
								Utilities.chanMsg(event, doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0]);
							}
							catch(Exception e)
							{
								Utilities.chanMsg(event, L10N.getString("forge.mainNotFound", event));
							}
							break;
						case "dlsrc": case "dlmdk":
							try
							{
								Utilities.chanMsg(event, doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0]);
							}
							catch(Exception e)
							{
								Utilities.chanMsg(event, L10N.getString("forge.srcNotFound", event));
							}
							break;
						default:
							throw new IncorrectCommandExecutionException(getMainAlias());
					}
					break;
				default:
					throw new IncorrectCommandExecutionException(getMainAlias());
			}
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
	public String getSyntax(MessageEvent event)
	{
		return "-forge <" + L10N.getString("forge.version", event) + "> <latest|rec|recommended> <version|changelog|dlmain|dlsrc>";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{
				L10N.getString("forge.explanation.1", event),
				L10N.getString("forge.explanation.2", event),
				L10N.getString("forge.explanation.3", event),
				L10N.getString("forge.explanation.4", event),
				L10N.getString("forge.explanation.5", event),
		};
	}
}
