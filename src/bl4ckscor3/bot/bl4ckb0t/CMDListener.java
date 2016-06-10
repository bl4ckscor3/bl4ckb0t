package bl4ckscor3.bot.bl4ckb0t;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Caps;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.ChangeNick;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Changelog;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Decide;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Disable;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Enable;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Evaluate;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Forge;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Help;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Info;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Join;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Kick;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Language;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Leave;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Leet;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.ListChans;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.MCStatus;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Ping;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Ranks;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Remind;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Select;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Source;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Stop;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Trello;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Twitch;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Twitter;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Unleet;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Unshorten;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Update;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Weather;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.XColor;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.YouTube;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.Action;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.ConfigEdit;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.Identify;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateJoin;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateLeave;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateStop;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.SendMessage;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.CustomArrayList;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class CMDListener extends ListenerAdapter
{
	public static final CustomArrayList<BaseChannelCommand<MessageEvent>> commands = new CustomArrayList<BaseChannelCommand<MessageEvent>>();
	public static final CustomArrayList<BasePrivateCommand<PrivateMessageEvent>> privCommands = new CustomArrayList<BasePrivateCommand<PrivateMessageEvent>>();

	@SuppressWarnings("unchecked")
	public static void setupCMDs()
	{
		commands.clear();
		privCommands.clear();
		commands.addEverything(
				new Caps(),
				new Changelog(),
				new ChangeNick(),
				new Decide(),
				new Disable(),
				new Enable(),
				new Evaluate(),
				new Help(),
				new Info(),
				new Join(),
				new Kick(),
				new Forge(),
				new Language(),
				new Leave(),
				new Leet(),
				new ListChans(),
				new MCStatus(),
				new Ping(),
				new Ranks(),
				new Remind(),
				new Select(),
				new Source(),
				new Stop(),
				new Trello(),
				new Twitch(),
				new Twitter(),
				new Unleet(),
				new Unshorten(),
				new Update(),
				new Weather(),
				new XColor(),
				new YouTube());
		Logging.info("Registered command classes for channel messages...");
		Help.setupHelpMenu(CMDListener.commands);
		privCommands.addEverything(
				new Action(),
				new ConfigEdit(),
				new Identify(),
				new PrivateJoin(),
				new PrivateLeave(),
				new PrivateStop(),
				new SendMessage());
		Logging.info("Registered command classes for private messages...");
	}

	@Override
	public void onMessage(MessageEvent event) throws Exception
	{
		try
		{
			String cmdName = Utilities.toArgs(event.getMessage())[0];
			int permissionLevel = Utilities.getUserPermissionLevel(event.getUser());

			if(!cmdName.startsWith(Core.bot.getCmdPrefix()))
				return;

			if(Utilities.isIgnored(event.getUser().getNick()))
			{
				Logging.warn("Ignoring user " + event.getUser().getNick());
				return;
			}
			
			String channel = event.getChannel().getName();

			if(Core.bot.isEnabled() && Core.bot.getChannelStates().get(channel))
			{
				for(BaseChannelCommand<MessageEvent> cmd : commands)
				{
					if(cmd.isEnabled() && cmd.isValidAlias(cmdName))
					{
						if(cmd.getPermissionLevel() > permissionLevel)
						{
							Utilities.noPermission(event.getChannel().getName());
							return;
						}

						try
						{
							Core.bot.dispatchCommand(event, cmd, Utilities.toArgs(event.getMessage()));
						}
						catch(IncorrectCommandExecutionException e)
						{
							Utilities.sendHelp(event.getUser().getNick(), cmd.getAliases(), cmd.getMainAlias(), cmd.getSyntax(channel), cmd.getUsage(channel), cmd.getNotes(channel), channel);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						if(!cmd.isEnabled() && Core.bot.isDevelopment())
							Logging.warn("Did you add the command to the config file?");
					}
				}
			}
			else
			{
				for(BaseChannelCommand<MessageEvent> cmd : commands)
				{
					if(cmd.isEnabled() && (cmd instanceof Enable || cmd instanceof Disable) && cmd.isValidAlias(cmdName))
					{
						try
						{
							Core.bot.dispatchCommand(event, cmd, Utilities.toArgs(event.getMessage()));
							return;
						}
						catch(IncorrectCommandExecutionException e)
						{
							Utilities.sendHelp(event.getUser().getNick(), cmd.getAliases(), cmd.getMainAlias(), cmd.getSyntax(channel), cmd.getUsage(channel), cmd.getNotes(channel), channel);
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	

	@Override
	public void onPrivateMessage(PrivateMessageEvent event) throws Exception
	{
		try
		{
			if(Utilities.isIgnored(event.getUser().getNick()))
			{
				Logging.warn("Ignoring user " + event.getUser().getNick());
				return;
			}

			if(Core.bot.isEnabled())
			{
				if(Utilities.isValidUser(event.getUser()))
				{
					for(BasePrivateCommand<PrivateMessageEvent> cmd : privCommands)
					{
						if(cmd.isEnabled() && event.getMessage().startsWith(cmd.getMainAlias()))
						{
							Core.bot.dispatchCommand(event, cmd, Utilities.toArgs(event.getMessage()));
							return;
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}