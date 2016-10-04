package bl4ckscor3.module.kick;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Kick extends Module
{
	private L10N l10n;
	
	public Kick(String name)
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
				l10n.translate("explanation.2", channel)
		};
	}

	@Override
	public int getPermissionLevel()
	{
		return 2;
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
			String nick = event.getUser().getNick();
			
			if(!(Lists.isLvl3User(nick) || Lists.isLvl2User(nick)))
			{
				Utilities.sendMessage(channel, l10n.translate("notAuthorized", channel).replace("#user", nick));
				Logging.info("Denying command access to " + nick);
				return;
			}

			if(args.length >= 1)
			{
				for(String userNotToKick : Lists.getLvl3Users())
				{
					if(args[0].equalsIgnoreCase(userNotToKick))
					{
						Utilities.sendMessage(channel, l10n.translate("cannotKick", channel));
						return;
					}
				}

				if(args[0].equalsIgnoreCase(Core.bot.getNick()))
					Utilities.sendMessage(channel, l10n.translate("cannotKick", channel));
				else
				{
					String result = "";

					for(int i = (args.length == 1 ? 0 : 1); i < args.length; i++)
					{
						result += args[i] + " ";
					}

					Core.bot.kick(channel, args[0], result.trim());
				}
			}
			else
				Utilities.sendHelp(module, nick, channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"kick"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}