package bl4ckscor3.bot.bl4ckb0t;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.channel.BaseCommand;
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
import bl4ckscor3.bot.bl4ckb0t.commands.channel.LongURL;
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
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Update;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Weather;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.XColor;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.YouTube;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.Action;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.ChanMsg;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateJoin;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateLeave;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateStop;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.UserMsg;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.CustomArrayList;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class CMDListener extends ListenerAdapter<Bot>
{
	public static final String cmdPrefix = "-";
	public static final CustomArrayList<BaseCommand<MessageEvent<Bot>>> commands = new CustomArrayList<BaseCommand<MessageEvent<Bot>>>();
	public static final CustomArrayList<BasePrivateCommand<PrivateMessageEvent<Bot>>> privCommands = new CustomArrayList<BasePrivateCommand<PrivateMessageEvent<Bot>>>();

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
				new LongURL(),
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
				new Update(),
				new Weather(),
				new XColor(),
				new YouTube());
		Logging.info("Registered command classes for channel messages...");
		Help.setupHelpMenu(CMDListener.commands);
		privCommands.addEverything(
				new PrivateJoin(),
				new PrivateLeave(),
				new Action(),
				new ChanMsg(),
				new UserMsg(),
				new PrivateStop());
		Logging.info("Registered command classes for private messages...");
	}

	@Override
	public void onMessage(MessageEvent<Bot> event) throws Exception
	{
		String cmdName = Utilities.toArgs(event.getMessage())[0];
		int permissionLevel = Utilities.getUserPermissionLevel(event);

		if(!cmdName.startsWith(cmdPrefix))
			return;

		if(Utilities.isIgnored(event.getUser().getNick()))
		{
			Logging.warn("Ignoring user " + event.getUser().getNick());
			return;
		}
		
		if(Core.bot.isEnabled() && Core.bot.getChannelStates().get(event.getChannel().getName()))
		{
			for(BaseCommand<MessageEvent<Bot>> cmd : commands)
			{
				if(cmd.isEnabled() && cmd.isValidAlias(cmdName))
				{
					if(cmd.getPermissionLevel() > permissionLevel)
					{
						Utilities.noPermission(event);
						return;
					}

					try
					{
						cmd.exe(event, Utilities.toArgs(event.getMessage()));
						return;
					}
					catch(IncorrectCommandExecutionException e)
					{
						Utilities.sendHelp(event.getUser().getNick(), cmd.getAliases(), cmd.getMainAlias(), cmd.getSyntax(event), cmd.getUsage(event), cmd.getNotes(event), event);
					}
				}
			}
		}
		else
		{
			for(BaseCommand<MessageEvent<Bot>> cmd : commands)
			{
				if(cmd.isEnabled() && (cmd instanceof Enable || cmd instanceof Disable) && cmd.isValidAlias(cmdName))
				{
					try
					{
						cmd.exe(event, Utilities.toArgs(event.getMessage()));
						return;
					}
					catch(IncorrectCommandExecutionException e)
					{
						Utilities.sendHelp(event.getUser().getNick(), cmd.getAliases(), cmd.getMainAlias(), cmd.getSyntax(event), cmd.getUsage(event), cmd.getNotes(event), event);
					}
				}
			}
		}
	}	

	@Override
	public void onPrivateMessage(PrivateMessageEvent<Bot> event) throws Exception
	{
		if(Utilities.isIgnored(event.getUser().getNick()))
		{
			Logging.warn("Ignoring user " + event.getUser().getNick());
			return;
		}

		if(Core.bot.isEnabled())
		{
			if(Utilities.isValidUser(event))
			{
				for(BasePrivateCommand<PrivateMessageEvent<Bot>> cmd : privCommands)
				{
					if(cmd.isEnabled() && event.getMessage().startsWith(cmd.getAlias()))
					{
						cmd.exe(event, Utilities.toArgs(event.getMessage()));
						return;
					}
				}
			}
		}
	}
}