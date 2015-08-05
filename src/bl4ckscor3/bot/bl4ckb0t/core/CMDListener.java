package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.channel.Calculate;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.ChangeNick;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Changelog;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Decide;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Disable;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Draw;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.ETS2MPUpdate;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Enable;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Forge;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Help;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.ICommand;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.IsItDown;
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
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Version;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Vowels;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Weather;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.XColor;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.YouTube;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.Action;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.ChanMsg;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.IPrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateJoin;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.PrivateLeave;
import bl4ckscor3.bot.bl4ckb0t.commands.privmsg.UserMsg;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public class CMDListener extends ListenerAdapter<Bot>
{
	public static final String cmdPrefix = "-";
	public static boolean enabled = true;
	public static final ArrayMap<String, Boolean> channelStates = new ArrayMap<String, Boolean>(); //false = disabled | true = enabled
	public boolean isCounting = false;
	public static final LinkedList<ICommand<MessageEvent<Bot>>> commands = new LinkedList<ICommand<MessageEvent<Bot>>>();
	public static final LinkedList<IPrivateCommand<PrivateMessageEvent<Bot>>> privCommands = new LinkedList<IPrivateCommand<PrivateMessageEvent<Bot>>>();

	public CMDListener()
	{
		channelStates.put("#whatever", false); //disabling the bot in the channel #whatever by default
		Logging.info("Disabled bot in channel #whatever...");
		commands.add(new Calculate());
		commands.add(new Changelog());
		commands.add(new ChangeNick());
		commands.add(new Decide());
		commands.add(new Disable());
		commands.add(new Draw());
		commands.add(new Enable());
		commands.add(new ETS2MPUpdate());
		commands.add(new Help());
		commands.add(new IsItDown());
		commands.add(new Join());
		commands.add(new Kick());
		commands.add(new Forge());
		commands.add(new Language());
		commands.add(new Leave());
		commands.add(new Leet());
		commands.add(new ListChans());
		commands.add(new LongURL());
		commands.add(new MCStatus());
		commands.add(new Ping());
		commands.add(new RandomLetter());
		commands.add(new RandomNumber());
		commands.add(new Reverse());
		commands.add(new Scramble());
		commands.add(new Select());
		commands.add(new Source());
		commands.add(new Stop());
		commands.add(new Trello());
		commands.add(new Twitch());
		commands.add(new Twitter());
		commands.add(new Update());
		commands.add(new Version());
		commands.add(new Vowels());
		commands.add(new Weather());
		commands.add(new XColor());
		commands.add(new YouTube());
		Logging.info("Registered command classes for channel messages...");
		Help.setupHelpMenu(commands);
		privCommands.add(new PrivateJoin());
		privCommands.add(new PrivateLeave());
		privCommands.add(new Action());
		privCommands.add(new ChanMsg());
		privCommands.add(new UserMsg());
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
			for(ICommand<MessageEvent<Bot>> cmd : commands)
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
						Utilities.sendHelp(event.getUser().getNick(), cmd.getSyntax(), cmd.getUsage(), cmd.getNotes());
					}
				}
			}
		}
		else
		{
			for(ICommand<MessageEvent<Bot>> cmd : commands)
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
						Utilities.sendHelp(event.getUser().getNick(), cmd.getSyntax(), cmd.getUsage(), cmd.getNotes());
					}
				}
			}
		}
	}	

	@Override
	public void onPrivateMessage(PrivateMessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		if(Lists.getIgnoredUsers().contains(event.getUser().getNick()))
			return;
		
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
			else
			{
				for(String user : Lists.getValidUsers())
				{
					Core.bot.sendCustomMessage(user, event.getUser().getNick() + ": " + event.getMessage());
				}
			}
		}
	}
}