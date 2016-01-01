package bl4ckscor3.bot.bl4ckb0t.core;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.channel.BaseCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Calculate;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Caps;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.ChangeNick;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Changelog;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.CurseForgeWidget;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Decide;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Disable;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Draw;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Enable;
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
import bl4ckscor3.bot.bl4ckb0t.commands.channel.RandomLetter;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.RandomNumber;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Reverse;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Scramble;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Select;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Source;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Stop;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Trello;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Twitch;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Twitter;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Update;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Vowels;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Weather;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.XColor;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.YouTube;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.Action;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.ChanMsg;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.IPrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateJoin;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateLeave;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateStop;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.UserMsg;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.CustomArrayList;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class CMDListener extends ListenerAdapter<Bot>
{
	public static final String cmdPrefix = "-";
	public static boolean enabled = true;
	public static final ArrayMap<String, Boolean> channelStates = new ArrayMap<String, Boolean>(); //false = disabled | true = enabled
	public boolean isCounting = false;
	public static final CustomArrayList<BaseCommand<MessageEvent<Bot>>> commands = new CustomArrayList<BaseCommand<MessageEvent<Bot>>>();
	public static final CustomArrayList<IPrivateCommand<PrivateMessageEvent<Bot>>> privCommands = new CustomArrayList<IPrivateCommand<PrivateMessageEvent<Bot>>>();

	@SuppressWarnings("unchecked")
	public CMDListener()
	{
		commands.addEverything(
				new Calculate(),
				new Caps(),
				new CurseForgeWidget(),
				new Changelog(),
				new ChangeNick(),
				new Decide(),
				new Disable(),
				new Draw(),
				new Enable(),
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
				new RandomLetter(),
				new RandomNumber(),
				new Reverse(),
				new Scramble(),
				new Select(),
				new Source(),
				new Stop(),
				new Trello(),
				new Twitch(),
				new Twitter(),
				new Update(),
				new Vowels(),
				new Weather(),
				new XColor(),
				new YouTube());
		Logging.info("Registered command classes for channel messages...");
		Help.setupHelpMenu(commands);
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

		if(Lists.getIgnoredUsers().contains(event.getUser().getNick()))
		{
			Logging.warn("Ignoring user " + event.getUser().getNick());
			return;
		}

		if(enabled && channelStates.get(event.getChannel().getName()))
		{
			for(BaseCommand<MessageEvent<Bot>> cmd : commands)
			{
				if(cmdName.equalsIgnoreCase(cmdPrefix + cmd.getAlias()))
				{
					if(cmd.getPermissionLevel() > permissionLevel)
					{
						Utilities.noPermission(event);
						return;
					}

					try
					{
						cmd.exe(event);
						return;
					}
					catch(IncorrectCommandExecutionException e)
					{
						Utilities.sendHelp(event.getUser().getNick(), cmd.getSyntax(event), cmd.getUsage(event), cmd.getNotes(event), event);
					}
				}
			}
		}
		else
		{
			for(BaseCommand<MessageEvent<Bot>> cmd : commands)
			{
				if((cmd instanceof Enable || cmd instanceof Disable) && event.getMessage().startsWith(cmdPrefix + cmd.getAlias()))
				{
					try
					{
						cmd.exe(event);
						return;
					}
					catch(IncorrectCommandExecutionException e)
					{
						Utilities.sendHelp(event.getUser().getNick(), cmd.getSyntax(event), cmd.getUsage(event), cmd.getNotes(event), event);
					}
				}
			}
		}
	}	

	@Override
	public void onPrivateMessage(PrivateMessageEvent<Bot> event) throws Exception
	{
		if(Lists.getIgnoredUsers().contains(event.getUser().getNick()))
		{
			Logging.warn("Ignoring user " + event.getUser().getNick());
			return;
		}

		if(enabled)
		{
			if(Utilities.isValidUser(event))
			{
				for(IPrivateCommand<PrivateMessageEvent<Bot>> cmd : privCommands)
				{
					if(event.getMessage().startsWith(cmd.getAlias()))
					{
						cmd.exe(event);
						return;
					}
				}
			}
		}
	}
}