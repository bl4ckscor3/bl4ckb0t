package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Forge implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IOException, IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 4)
		{
			Document doc;

			try
			{
				doc = Jsoup.connect("http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_" + args[1] + ".html").get();
			}
			catch(HttpStatusException e)
			{
				Utilities.chanMsg(event, L10N.getString("forge.incorrectMcVersion"));
				return;
			}

			switch(args[2])
			{
				case "latest":
					switch(args[3])
					{
						case "version":
							Utilities.chanMsg(event, doc.select("div.download:nth-child(1) > div:nth-child(1) > small:nth-child(3)").get(0).toString().split(">")[1]);
							break;
						case "changelog":
							Utilities.chanMsg(event, doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").get(0).toString().split("href=\"")[1].split("\"")[0]);
							break;
						case "dlmain":
							Utilities.chanMsg(event, doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0]);
							break;
						case "dlsrc":
							Utilities.chanMsg(event, doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0]);
							break;
						default:
							throw new IncorrectCommandExecutionException(getAlias());
					}
					break;
				case "rec": case "recommended":
					switch(args[3])
					{
						case "version":
							Utilities.chanMsg(event, doc.select("div.download:nth-child(2) > div:nth-child(1) > small:nth-child(3)").get(0).toString().split(">")[1]);
							break;
						case "changelog":
							Utilities.chanMsg(event, doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").get(0).toString().split("href=\"")[1].split("\"")[0]);
							break;
						case "dlmain":
							Utilities.chanMsg(event, doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0]);
							break;
						case "dlsrc":
							Utilities.chanMsg(event, doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0]);
							break;
						default:
							throw new IncorrectCommandExecutionException(getAlias());
					}
					break;
				default:
					throw new IncorrectCommandExecutionException(getAlias());
			}
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias()
	{
		return "forge";
	}

	@Override
	public String getSyntax()
	{
		return "-forge <version> <latest|rec|recommended> <version|changelog|dlmain|dlsrc>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
						L10N.getString("forge.explanation.1"),
						L10N.getString("forge.explanation.2"),
						L10N.getString("forge.explanation.3"),
						L10N.getString("forge.explanation.4"),
						L10N.getString("forge.explanation.5"),
				};
	}

	@Override
	public String getNotes()
	{
		return null;
	}

	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
