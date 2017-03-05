package bl4ckscor3.module.cookie;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Cookie extends Module
{
	private L10N l10n;

	public Cookie(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader) throws URISyntaxException
	{
		getBuilder().registerChannelCommand(this, new CommandCookie(this));
		getBuilder().registerChannelCommand(this, new CommandCookies(this));
		l10n = new L10N(this, loader);
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation.1", channel),
				l10n.translate("explanation.2", channel),
				l10n.translate("explanation.3", channel),
				l10n.translate("explanation.4", channel)
		};
	}

	public class CommandCookie extends BaseChannelCommand
	{
		public Module module;

		public CommandCookie(Module m)
		{
			module = m;
		}

		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String nick = event.getUser().getNick();
			String channel = event.getChannel().getName();

			if(args.length == 0)
			{
				Utilities.sendHelp(module, nick, channel);
				return;
			}

			if(nick.equalsIgnoreCase(args[0]))
			{
				Utilities.sendMessage(channel, l10n.translate("givingSelf", channel));
				return;
			}

			File f = new File(Utilities.getJarLocation() + "/cookies.txt");

			if(!f.exists())
				f.createNewFile();

			List<String> contents = FileUtils.readLines(f, Charset.defaultCharset());
			boolean senderModified = false;
			boolean receiverModified = false;

			for(int i = 0; i < contents.size(); i++)
			{
				try
				{
					String name = contents.get(i).split("#")[0];
					int amount = Integer.parseInt(contents.get(i).split("#")[1]);
					int given = Integer.parseInt(contents.get(i).split("#")[2]);
					int received = Integer.parseInt(contents.get(i).split("#")[3]);

					if(name.equalsIgnoreCase(nick))
					{
						if(args.length == 2)
						{
							amount -= Integer.parseInt(args[1]);
							given += Integer.parseInt(args[1]);
						}
						else
						{
							amount--;
							given++;
						}

						if(amount < 0)
						{
							Utilities.sendMessage(channel, l10n.translate("notEnough", channel));
							return;
						}

						contents.remove(i);
						contents.add(i, nick + "#" + amount + "#" + given + "#" + received);
						senderModified = true;
					}
					else if(name.equalsIgnoreCase(args[0]))
					{
						if(args.length == 2)
						{
							amount += Integer.parseInt(args[1]);
							received += Integer.parseInt(args[1]);
						}
						else
						{
							amount++;
							received++;
						}

						contents.remove(i);
						contents.add(i, args[0] + "#" + amount + "#" + given + "#" + received);
						receiverModified = true;
					}
				}
				catch(NumberFormatException e)
				{
					Utilities.sendHelp(module, nick, channel);
					return;
				}
			}

			//these two cases only happen when the respective user isn't in the database
			if(!senderModified)
			{
				try
				{
					int amount = 20;
					int given = 0;

					if(args.length == 2)
					{
						amount -= Integer.parseInt(args[1]);
						given += Integer.parseInt(args[1]);
					}
					else
					{
						amount--;
						given++;
					}

					if(amount < 0)
					{
						amount = 20;
						given = 0;
						Utilities.sendMessage(channel, l10n.translate("notEnough", channel));
						return;
					}

					contents.add(nick + "#" + amount + "#" + given +"#0");
				}
				catch(NumberFormatException e)
				{
					Utilities.sendHelp(module, nick, channel);
					return;
				}
			}

			if(!receiverModified)
			{
				int amount = 20;
				int received = 0;

				if(args.length == 2)
				{
					amount += Integer.parseInt(args[1]);
					received += Integer.parseInt(args[1]);
				}
				else
				{
					amount++;
					received++;
				}

				contents.add(args[0] + "#" + amount + "#0" + "#" + received);
			}

			FileUtils.writeLines(f, contents);
			Utilities.sendMessage(channel, l10n.translate("sent", channel).replace("#sender", nick).replace("#amount", args.length == 2 ? args[1] : "" + 1).replace("#receiver", args[0]));
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"cookie"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax.1", channel);
		}
	}

	public class CommandCookies extends BaseChannelCommand
	{
		public Module module;

		public CommandCookies(Module m)
		{
			module = m;
		}

		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String nick = event.getUser().getNick();
			String channel = event.getChannel().getName();

			if(args.length > 1)
			{
				Utilities.sendHelp(module, nick, channel);
				return;
			}
			else if(args.length == 0)
				args = new String[] {nick};

			File f = new File(Utilities.getJarLocation() + "/cookies.txt");

			if(!f.exists())
				f.createNewFile();

			List<String> contents = FileUtils.readLines(f, Charset.defaultCharset());

			for(String s : contents)
			{
				String name = s.split("#")[0];
				int amount = Integer.parseInt(s.split("#")[1]);
				int given = Integer.parseInt(s.split("#")[2]);
				int received = Integer.parseInt(s.split("#")[3]);

				if(name.equalsIgnoreCase(args[0]))
				{
					Utilities.sendStarMsg(channel, 
							l10n.translate("user", channel).replace("#user", name),
							l10n.translate("amount", channel).replace("#amount", "" + amount),
							l10n.translate("given", channel).replace("#given", "" + given),
							l10n.translate("received", channel).replace("#received", "" + received));
					return;
				}
			}
			
			Utilities.sendStarMsg(channel, 
					l10n.translate("user", channel).replace("#user", args[0]),
					l10n.translate("amount", channel).replace("#amount", "" + 20),
					l10n.translate("given", channel).replace("#given", "" + 0),
					l10n.translate("received", channel).replace("#received", "" + 0));
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"cookies"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax.2", channel);
		}
	}
}