package bl4ckscor3.module.distance;

import java.net.URLClassLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Distance extends Module
{
	private L10N l10n;

	public Distance(String name)
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

			if(args.length == 2)
			{
				Document doc = Jsoup.connect("http://www.distance.to/" + args[0] + "/" + args[1]).get();
				String directMI = doc.select(".info > li:nth-child(1) > span:nth-child(1)").get(0).text().replace(",", "");
				String directKM = String.format("%.2f", Double.parseDouble(directMI.split(" ")[0]) * 1.621371).replace(",", ".");

				Utilities.sendStarMsg(channel,
						Colors.NORMAL + directKM + "km" + Colors.ITALICS + " (" + directMI + ")",
						l10n.translate("credit", channel).replace("#link", Colors.NORMAL + "http://www.distance.to/"));
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"distance", "dist"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}