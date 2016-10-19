package bl4ckscor3.bot.bl4ckb0t.modules;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.commons.io.FilenameUtils;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.Modules;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ModuleManagement extends Module
{
	public ModuleManagement(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command(this));
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				Core.l10n.translate("moduleManagement.explanation.1", channel),
				Core.l10n.translate("moduleManagement.explanation.2", channel),
				Core.l10n.translate("moduleManagement.explanation.3", channel),
				Core.l10n.translate("moduleManagement.explanation.4", channel)
		};
	}

	@Override
	public int getPermissionLevel()
	{
		return 3;
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
				File folder = new File(Utilities.getJarLocation() + "/modules");

				switch(args[0])
				{
					case "disable":
						for(File f : folder.listFiles())
						{
							String name = f.getName().split("\\.")[0];

							if(name.equalsIgnoreCase(args[1]))
							{
								if(f.getName().endsWith(".disabled"))
								{
									Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.alreadyDisabled", channel));
									return;
								}

								if(!f.renameTo(new File(f.getAbsolutePath() + ".disabled")))
								{
									Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.problemDisabling", channel));
									Logging.warn("Renaming the file did not work!");
									return;
								}

								inner:
								for(Module m : Modules.modules)
								{
									if(m.getName().equalsIgnoreCase(name))
									{
										Modules.modules.remove(m);
										m.onDisable();
										break inner;
									}
								}
								
								Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.disabled", channel));
								return;
							}
						}

						Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.private", channel));
						break;
					case "enable":
						for(File f : folder.listFiles())
						{
							String name = f.getName().split("\\.")[0];

							if(name.equalsIgnoreCase(args[1]))
							{
								if(!f.getName().endsWith(".disabled") && Modules.isLoaded(name))
								{
									Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.alreadyEnabled", channel));
									return;
								}

								if(!f.renameTo(new File(f.getAbsolutePath().replace(".disabled", ""))))
								{
									Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.problemEnabling", channel));
									Logging.warn("Renaming the file did not work!");
									return;
								}

								int loadState = Core.modules.loadModule(new URL(f.toURI().toURL().toString().replace(".disabled", "")), name);
								
								if(loadState == 1)
									Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.enabled", channel));
								else if(loadState == 0)
									Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.alreadyEnabled", channel));
								else
									Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.errorEnabling", channel));

								return;
							}
						}

						Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.private", channel));
						break;
					case "reload":
						exe(event, cmdName, new String[]{"disable", args[1]});
						exe(event, cmdName, new String[]{"enable", args[1]});
						break;
					case "load":
						try
						{
							String name = FilenameUtils.getName(args[1].contains("?") ? args[1].substring(0, args[1].indexOf('?')) : args[1]);
							URL link = new URL(args[1]);
							ReadableByteChannel rbc = Channels.newChannel(link.openStream());
							FileOutputStream stream = new FileOutputStream(Utilities.getJarLocation() + "/modules/" + name); //the substring call removes all parameters of the link

							stream.getChannel().transferFrom(rbc, 0, Integer.MAX_VALUE); //maximum download of a 2gb file
							stream.close();
							rbc.close();

							int loadState = Core.modules.loadModule(new URL("file:" + Utilities.getJarLocation() + "/modules/" + name), name.substring(0, name.lastIndexOf('.')));
							
							if(loadState == 1)
								Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.loaded", channel));
							else if(loadState == 0)
								exe(event, cmdName, new String[]{"reload", name.substring(0, name.lastIndexOf('.'))});
							else
								Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.errorLoading", channel));
						}
						catch(MalformedURLException e)
						{
							Utilities.sendMessage(channel, Core.l10n.translate("moduleManagement.invalidURL", channel));
						}
						
						break;
					default:
						Utilities.sendHelp(module, event.getUser().getNick(), channel);
				}
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"module"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return Core.l10n.translate("moduleManagement.syntax", channel);
		}
	}
}
