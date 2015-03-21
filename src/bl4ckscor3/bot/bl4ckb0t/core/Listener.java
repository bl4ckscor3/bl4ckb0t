package bl4ckscor3.bot.bl4ckb0t.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedList;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.channel.Calculate;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.ChangeNick;
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
import bl4ckscor3.bot.bl4ckb0t.commands.channel.RandomLetter;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.RandomNumber;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Scramble;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Select;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Source;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Stop;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Twitch;
import bl4ckscor3.bot.bl4ckb0t.commands.channel.Twitter;
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
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.misc.LinkTitle;
import bl4ckscor3.bot.bl4ckb0t.misc.SpellingCorrection;
import bl4ckscor3.bot.bl4ckb0t.misc.YouTubeStats;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Listener extends ListenerAdapter<Bot>
{
	private final String p = "-";
	public static boolean enabled = true;
	public static final HashMap<String, Boolean> channelStates = new HashMap<String, Boolean>(); //false = disabled | true = enabled
	public boolean isCounting = false;
	public static final LinkedList<ICommand<MessageEvent<Bot>>> commands = new LinkedList<ICommand<MessageEvent<Bot>>>();
	public static final LinkedList<IPrivateCommand<PrivateMessageEvent<Bot>>> privCommands = new LinkedList<IPrivateCommand<PrivateMessageEvent<Bot>>>();

	public Listener()
	{
		commands.add(new Calculate());
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
		commands.add(new RandomLetter());
		commands.add(new RandomNumber());
		commands.add(new Scramble());
		commands.add(new Select());
		commands.add(new Source());
		commands.add(new Stop());
		commands.add(new Twitch());
		commands.add(new Twitter());
		commands.add(new Version());
		commands.add(new Vowels());
		commands.add(new Weather());
		commands.add(new XColor());
		commands.add(new YouTube());
		Help.setupHelpMenu(commands);
		privCommands.add(new PrivateJoin());
		privCommands.add(new PrivateLeave());
		privCommands.add(new Action());
		privCommands.add(new ChanMsg());
		privCommands.add(new UserMsg());
	}

	@Override
	public void onMessage(MessageEvent<Bot> event) throws MalformedURLException, IOException, InterruptedException, IrcException
	{
		String cmdName = Utilities.toArgs(event.getMessage())[0];

		L10N.changeLocalization(L10N.parseLangCode(L10N.chanLangs.get(event.getChannel().getName()), 0), L10N.parseLangCode(L10N.chanLangs.get(event.getChannel().getName()), 1), event.getChannel().getName());
		misc(event);

		if(!cmdName.startsWith(p))
			return;

		if(enabled && channelStates.get(event.getChannel().getName()))
		{
			for(ICommand<MessageEvent<Bot>> cmd : commands)
			{
				if(cmdName.equalsIgnoreCase(p + cmd.getAlias()))
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
		else
		{
			for(ICommand<MessageEvent<Bot>> cmd : commands)
			{
				if((cmd instanceof Enable || cmd instanceof Disable) && event.getMessage().startsWith(p + cmd.getAlias()))
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

	public void misc(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		String message = event.getMessage();

		if(message.startsWith(p))
			return;

		if(message.startsWith("?enabled"))
		{
			if(message.startsWith("?enabled #") && Listener.enabled)
				Utilities.chanMsg(event, message.split(" ")[1] + ": " + Listener.channelStates.get(message.split(" ")[1]));
			else
				Utilities.chanMsg(event, "global: " + Listener.enabled);
			return;
		}

		if(enabled)
		{
			SpellingCorrection.checkForSpellingCorrection(event, message);

			//making sure the above messages dont get added as a latest message
			if(!SpellingCorrection.corrected)
				SpellingCorrection.updateLatestMessage(event.getChannel().getName(), event.getMessage(), event.getUser().getNick());
			else
				SpellingCorrection.corrected = false;

			if(!Utilities.isUserInChannel("bl4ckscor3", event.getChannel()) && !event.getChannel().getName().equals("#whatever"))
			{
				Core.bot.sendIRC().message("#whatever", "[" + event.getChannel().getName() + "] " + event.getUser().getNick() + ": " + event.getMessage());
				return;
			}

			//sending a welcome back message
			if(message.toLowerCase().startsWith("re ") || message.toLowerCase().equals("re"))
			{
				Utilities.chanMsg(event, "wb, " + event.getUser().getNick());
				return;
			}

			//youtube recognition
			if(message.contains("www.youtube.com/watch") || message.contains("youtu.be/"))
			{
				YouTubeStats.sendVideoStats(event);
				return;
			}

			//checking for urls and sending the title if available
			LinkTitle.checkForLinkAndSendTitle(event);
		}
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent<Bot> event) throws MalformedURLException, IOException
	{
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
					Core.bot.sendIRC().message(user, event.getUser().getNick() + ": " + event.getMessage());
				}
			}
		}
	}

	@Override
	public void onConnect(ConnectEvent<Bot> event) throws MalformedURLException, IOException
	{
		Startup.setAutoJoinChans();
		Startup.setValidUsers();
		Utilities.joinDefaults();
	}
}