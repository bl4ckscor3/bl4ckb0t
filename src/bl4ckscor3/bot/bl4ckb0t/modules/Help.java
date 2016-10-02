package bl4ckscor3.bot.bl4ckb0t.modules;

import java.net.URLClassLoader;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.Modules;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Help extends Module
{
	public Help(String name)
	{
		super(name);
	}

	public void setup(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command());
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				Core.l10n.translate("help.explanation.1", channel),
				Core.l10n.translate("help.explanation.2", channel)
		};
	}

	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String nick = event.getUser().getNick();
			String channel = event.getChannel().getName();

			if(args.length == 0)
			{
				String msg = "";
				int i = 0;

				Utilities.sendMessage(nick, Core.l10n.translate("help.header.1", channel));

				for(Module m : Modules.modules)
				{
					if(Utilities.hasPermission(nick, m.getPermissionLevel()))
					{
						msg += (m.getPermissionLevel() == 3 ? Colors.RED : (m.getPermissionLevel() == 2 ? Colors.GREEN : "")) + m.getName() + Colors.NORMAL + " | ";
						i++;

						if(i % 10 == 0)
						{
							Utilities.sendMessage(nick, msg.trim());
							msg = "";
						}
					}
				}

				Utilities.sendMessage(nick, msg.substring(0, msg.lastIndexOf(" | ")));
				Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + "----------------------------------------------------------");
				Utilities.sendMessage(nick, Core.l10n.translate("help.moreInfo", channel));
				Thread.sleep(2000);
				Utilities.sendMessage(nick, Core.l10n.translate("help.credits.header", channel));
				Utilities.sendMessage(nick, Core.l10n.translate("help.credits.1", channel));
				Utilities.sendMessage(nick, Core.l10n.translate("help.credits.2", channel));
				Utilities.sendMessage(nick, Core.l10n.translate("help.credits.3", channel));
				Utilities.sendMessage(nick, Core.l10n.translate("help.credits.4", channel));
				Utilities.sendMessage(nick, Core.l10n.translate("help.credits.5", channel));
			}
			else if(args.length == 1)
			{
				for(Module m : Modules.modules)
				{
					if(m.getName().equalsIgnoreCase(args[0]))
					{
						if(Utilities.hasPermission(nick, m.getPermissionLevel()))
						{
							Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + Core.l10n.translate("help.header.2", channel).replace("#module", m.getName()));
							Utilities.sendMessage(nick, Core.l10n.translate("help.channelCommands", channel));

							if(m.getChannelCommands() != null)
							{
								for(BaseChannelCommand cmd : m.getChannelCommands())
								{
									String result = cmd.getSyntax();

									if(cmd.getAliases().length > 1) //there are aliases
									{
										result += " " + Core.l10n.translate("help.aliases", channel);
										
										for(String s : cmd.getAliases())
										{
											if(s.equals(cmd.getMainAlias()))
												continue;

											result += s + ", ";
										}

										Utilities.sendMessage(nick, "		" + result.substring(0, result.lastIndexOf(",")) + ")");
										
										break;
									}
									
									Utilities.sendMessage(nick, "		" + result);
								}
							}
							else
								Utilities.sendMessage(nick, Core.l10n.translate("help.none", channel));

							Utilities.sendMessage(nick, Core.l10n.translate("help.privateCommands", channel));

							if(m.getPrivateCommands() != null)
							{
								for(BasePrivateCommand cmd : m.getPrivateCommands())
								{
									Utilities.sendMessage(nick, "		" + cmd.getSyntax());
								}
							}
							else
								Utilities.sendMessage(nick, Core.l10n.translate("help.none", channel));

							Utilities.sendMessage(nick, Core.l10n.translate("help.usage", channel));

							for(String s : m.getUsage(channel))
							{
								Utilities.sendMessage(nick, "		" + s);
							}

							Utilities.sendMessage(nick, Core.l10n.translate("help.notes", channel));
							Utilities.sendMessage(nick, "		" + m.getNotes(channel));
						}
						else
						{
							Utilities.sendMessage(nick, Core.l10n.translate("help.noPermission", channel));
						}
					}
				}
			}
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"help"};
		}

		@Override
		public String getSyntax()
		{
			return Core.bot.getCmdPrefix() + "help <modulename>";
		}
	}
}
