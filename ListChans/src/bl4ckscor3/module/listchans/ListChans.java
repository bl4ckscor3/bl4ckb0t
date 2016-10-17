package bl4ckscor3.module.listchans;

import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.hooks.events.MessageEvent;

import com.google.common.collect.ImmutableSortedSet;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ListChans extends Module
{
	private L10N l10n;
	
	public ListChans(String name)
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
			String[] chans = getJoinedChannels(false);
			List<String> secretChans = getSecretChannels();
			String result = "";
			String channel = event.getChannel().getName();
			
			for(String s : chans)
			{
				if(s != null)
					result += (s + " | ");
			}

			if(result.lastIndexOf(" | ") != -1)
				result = result.substring(0, result.lastIndexOf(" | "));

			if(secretChans.size() != 0)
				result += l10n.translate("secret", channel).replace("#secretChannelAmount", "" + secretChans.size());

			Utilities.sendMessage(channel, l10n.translate("list", channel) + ": " + result);

			if(args.length == 1 && args[0].equalsIgnoreCase("show"))
			{
				if(Lists.isLvl3User(event.getUser().getNick()))
				{
					Utilities.sendMessage(event.getUser().getNick(), l10n.translate("secret.show", channel));

					for(String s : secretChans)
					{
						Utilities.sendMessage(event.getUser().getNick(), s);
					}
				}
				else
					Utilities.sendMessage(channel, l10n.translate("secret.show.fail", channel));
			}			
		}
		
		/**
		 * Gets the channels with mode +s the bot has joined
		 */
		public List<String> getSecretChannels()
		{
			ImmutableSortedSet<Channel> list = Core.bot.getUserBot().getChannels();
			Object[] x = list.toArray();
			List<String> chans = new ArrayList<String>();

			for(Object o : x)
			{
				//if the channel has the flag +s, it does not get shown
				if(o.toString().contains("secret=true"))
					chans.add(o.toString().split(",")[0].split("=")[1]);
			}

			return chans;
		}

		/**
		 * Gets the channels the bot has joined
		 * @param listSecretChannels Wether to list secret channels
		 */
		public String[] getJoinedChannels(boolean listSecretChannels)
		{
			ImmutableSortedSet<Channel> list = Core.bot.getUserBot().getChannels();
			Object[] x = list.toArray();
			String[] chans = new String[x.length];
			int i = 0;

			for(Object o : x)
			{
				if(!listSecretChannels)
				{
					//if the channel has the flag +s, it does not get shown
					if(!o.toString().contains("secret=true"))
						chans[i] = o.toString().split(",")[0].split("=")[1];
				}
				else
					chans[i] = o.toString().split(",")[0].split("=")[1];
				i++;
			}

			return chans;
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"listchans", "lc"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}