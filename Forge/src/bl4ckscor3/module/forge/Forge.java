package bl4ckscor3.module.forge;

import java.net.URLClassLoader;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Forge extends Module
{
	private L10N l10n;

	public Forge(String name)
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
				l10n.translate("explanation.1", channel),
				l10n.translate("explanation.2", channel),
				l10n.translate("explanation.3", channel),
				l10n.translate("explanation.4", channel),
				l10n.translate("explanation.5", channel)
		};
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
			
			if(args.length == 3)
			{
				Document doc;
				
				try
				{
					doc = Jsoup.connect("http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_" + args[0] + ".html").get();
				}
				catch(HttpStatusException e)
				{
					Utilities.sendMessage(channel, l10n.translate("incorrectMcVersion", channel));
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
									msg = l10n.translate("versionNotFound", channel);
								}
								break;
							case "changelog":
								try
								{
									msg = "http://files.minecraftforge.net" + doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").get(0).toString().split("href=\"")[1].split("\"")[0];
								}
								catch(Exception e)
								{
									msg = l10n.translate("changelogNotFound", channel);
								}
								break;
							case "dlmain":
								try
								{
									msg = doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0];
								}
								catch(Exception e)
								{
									msg = l10n.translate("mainNotFound", channel);
								}
								break;
							case "dlsrc": case "dlmdk":
								try
								{
									msg = doc.select("div.download:nth-child(1) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0];
								}
								catch(Exception e)
								{
									msg = l10n.translate("srcNotFound", channel);
								}
								break;
							default:
								Utilities.sendHelp(module, event.getUser().getNick(), channel);
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
									msg = l10n.translate("versionNotFound", channel);
								}
								break;
							case "changelog":
								try
								{
									msg = "http://files.minecraftforge.net" + doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").get(0).toString().split("href=\"")[1].split("\"")[0];
								}
								catch(Exception e)
								{
									msg = l10n.translate("changelogNotFound", channel);
								}
								break;
							case "dlmain":
								try
								{
									msg = doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0];
								}
								catch(Exception e)
								{
									msg = l10n.translate("mainNotFound", channel);
								}
								break;
							case "dlsrc": case "dlmdk":
								try
								{
									msg = doc.select("div.download:nth-child(2) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)").get(0).toString().split("url=")[1].split("\"")[0];
								}
								catch(Exception e)
								{
									msg = l10n.translate("srcNotFound", channel);
								}
								break;
							default:
								Utilities.sendHelp(module, event.getUser().getNick(), channel);
						}
						break;
					default:
						Utilities.sendHelp(module, event.getUser().getNick(), channel);
				}
				
				Utilities.sendMessage(channel, (args[2].equals("version") ? Colors.BOLD : "") + msg.replace("\n", Colors.NORMAL).replace("<!-- b", "(B").replace(" --", ")"));
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"forge"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}