package bl4ckscor3.bot.bl4ckb0tX.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.events.QuitEvent;

import bl4ckscor3.bot.bl4ckb0tX.commands.*;
import bl4ckscor3.bot.bl4ckb0tX.misc.*;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Listener extends ListenerAdapter 
{
	private final String p = "-";
	public static boolean enabled = true;
	public boolean isCounting = false;
	public final static LinkedList<ICommand> commands = new LinkedList<>();
	
	public Listener()
	{
		commands.add(new Bukkit());
		commands.add(new Calculate());
		commands.add(new ChangeNick());
		commands.add(new CraftBukkit());
		commands.add(new Decide());
		commands.add(new Disable());
		commands.add(new Draw());
		commands.add(new Enable());
		commands.add(new ETS2MPUpdate());
		commands.add(new Help());
		commands.add(new Join());
		commands.add(new Kick());
		commands.add(new Forge());
		commands.add(new Leave());
		commands.add(new Leet());
		commands.add(new ListChans());
		commands.add(new LongURL());
		commands.add(new RandomLetter());
		commands.add(new RandomNumber());
		commands.add(new Scramble());
		commands.add(new Select());
		commands.add(new Source());
		commands.add(new Stop());
		commands.add(new Twitch());
		commands.add(new Twitter());
		commands.add(new MinusVowels());
		commands.add(new Weather());
		commands.add(new XColor());
		commands.add(new YouTube());
		
		Help.setupHelpMenu(commands);
	}

	@Override
	public void onMessage(MessageEvent event) throws Exception
	{
		String cmdName = Utilities.toArgs(event.getMessage())[0];

		misc(event);

		if(!cmdName.startsWith(p))
			return;

		if(enabled)
		{
			for(ICommand cmd : commands)
			{
				if(cmdName.equalsIgnoreCase(p + cmd.getAlias()))
				{
					cmd.exe(event);
					return;
				}
			}
		}
		else
		{
			for(ICommand cmd : commands)
			{
				if((cmd instanceof Enable || cmd instanceof Disable) && event.getMessage().equalsIgnoreCase(p + cmd.getAlias()))
				{
					cmd.exe(event);
					return;
				}
			}
		}
	}	

	public void misc(MessageEvent event) throws Exception
	{
		if(event.getMessage().equals("?enabled"))
		{
			Utilities.chanMsg(event, "" + enabled);
			return;
		}

		if(enabled)
		{
			if(event.getMessage().contains("www.youtube.com/watch") || event.getMessage().contains("http://youtu.be/"))
			{
				YouTubeStats.sendVideoStats(event);
				return;
			}

			if(!event.getMessage().startsWith(p))
				LinkTitle.checkForLinkAndSendTitle(event);

			if(event.getMessage().toLowerCase().equalsIgnoreCase("re"))
				Utilities.chanMsg(event, "wb, " + event.getUser().getNick());
		}
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent event) throws MalformedURLException, IOException
	{
		if(enabled)
		{
			if(Utilities.isValidUser(event))
			{
				String[] args = Utilities.toArgs(event.getMessage());
				StringBuilder builder = new StringBuilder();
				String msg;

				if(args[0].equalsIgnoreCase("join"))
					Core.bot.sendIRC().joinChannel("#bl4ckscor3");
				else if(args[0].startsWith("#"))
				{
					for(int i = 1; i < args.length; i++)
					{
						builder.append(args[i] + " ");
					}

					msg = builder.toString();

					if(args[1].startsWith("*"))
						Core.bot.sendIRC().action(args[0], msg.substring(1));
					else
						Core.bot.sendIRC().message(args[0], msg);
				}
				else if(args[0].equalsIgnoreCase("msg"))
				{
					for(int i = 2; i < args.length; i++)
					{
						builder.append(args[i] + " ");
					}

					msg = builder.toString();
					Core.bot.sendIRC().message(args[1], msg);
				}
			}
			else
			{
				for(String user : Utilities.getValidUsers())
				{
					Core.bot.sendIRC().message(user, event.getUser().getNick() + " just sent me this message: " + event.getMessage());
				}
			}
		}
	}

	@Override
	public void onConnect(ConnectEvent event) throws Exception
	{
		if(!Core.bot.getNick().equals("bl4ckb0t"))
			commands.add(new DeAds());

		String[] channelsToJoin = Utilities.addAutoJoinChans();

		for(String s : channelsToJoin)
		{
			Core.bot.sendIRC().joinChannel(s);
		}
	}
}