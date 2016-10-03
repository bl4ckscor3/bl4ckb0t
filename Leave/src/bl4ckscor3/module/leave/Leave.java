package bl4ckscor3.module.leave;

import java.net.URLClassLoader;

import org.pircbotx.Channel;
import org.pircbotx.hooks.events.MessageEvent;

import com.google.common.collect.ImmutableSortedSet;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Leave extends Module
{
	private L10N l10n;
	
	public Leave(String name)
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
			
			if(args.length == 1)
			{
				if(args[0].equals("d"))
				{
					for(String s : Lists.getDefaultChans())
					{	
						Core.bot.leaveChannel(s);
					}
					
					return;
				}

				if(!args[0].startsWith("#"))
					args[0] = "#" + args[0];

				if(hasJoinedChannel(args[0]))
					Core.bot.leaveChannel(args[0]);
				else
					Utilities.sendMessage(channel, l10n.translate("notJoined", channel));
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"leave"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
		
		/**
		 * Checks wether the bot has joined the given channel
		 * @param channel The channel to check
		 * @return Wether the bot has joined the given channel or not
		 */
		public boolean hasJoinedChannel(String channel)
		{
			for(String s : getJoinedChannels())
			{
				if(s != null)
				{
					if(s.equalsIgnoreCase(channel))
						return true;
				}
			}
			
			return false;
		}
		
		/**
		 * Gets the channels the bot has joined
		 */
		public String[] getJoinedChannels()
		{
			ImmutableSortedSet<Channel> list = Core.bot.getUserBot().getChannels();
			Object[] x = list.toArray();
			String[] chans = new String[x.length];
			int i = 0;

			for(Object o : x)
			{
				chans[i] = o.toString().split(",")[0].split("=")[1];
				i++;
			}

			return chans;
		}
	}
}