package bl4ckscor3.bot.bl4ckb0tX.core;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.commands.Bukkit;
import bl4ckscor3.bot.bl4ckb0tX.commands.Calculate;
import bl4ckscor3.bot.bl4ckb0tX.commands.ChangeNick;
import bl4ckscor3.bot.bl4ckb0tX.commands.Command;
import bl4ckscor3.bot.bl4ckb0tX.commands.CraftBukkit;
import bl4ckscor3.bot.bl4ckb0tX.commands.DeAds;
import bl4ckscor3.bot.bl4ckb0tX.commands.Decide;
import bl4ckscor3.bot.bl4ckb0tX.commands.Draw;
import bl4ckscor3.bot.bl4ckb0tX.commands.Forge;
import bl4ckscor3.bot.bl4ckb0tX.commands.Help;
import bl4ckscor3.bot.bl4ckb0tX.commands.Join;
import bl4ckscor3.bot.bl4ckb0tX.commands.Kick;
import bl4ckscor3.bot.bl4ckb0tX.commands.Leave;
import bl4ckscor3.bot.bl4ckb0tX.commands.Leet;
import bl4ckscor3.bot.bl4ckb0tX.commands.ListChans;
import bl4ckscor3.bot.bl4ckb0tX.commands.LongURL;
import bl4ckscor3.bot.bl4ckb0tX.commands.MinusVowels;
import bl4ckscor3.bot.bl4ckb0tX.commands.RandomLetter;
import bl4ckscor3.bot.bl4ckb0tX.commands.RandomNumber;
import bl4ckscor3.bot.bl4ckb0tX.commands.Select;
import bl4ckscor3.bot.bl4ckb0tX.commands.Source;
import bl4ckscor3.bot.bl4ckb0tX.commands.Stop;
import bl4ckscor3.bot.bl4ckb0tX.commands.SwitchOff;
import bl4ckscor3.bot.bl4ckb0tX.commands.SwitchOn;
import bl4ckscor3.bot.bl4ckb0tX.commands.Twitch;
import bl4ckscor3.bot.bl4ckb0tX.commands.Twitter;
import bl4ckscor3.bot.bl4ckb0tX.commands.Weather;
import bl4ckscor3.bot.bl4ckb0tX.commands.XColor;
import bl4ckscor3.bot.bl4ckb0tX.commands.YouTube;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Listener extends ListenerAdapter 
{
	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
	private final String p = "-";
	public static boolean enabled = true;
	public boolean isCounting = false;
	private Random r = new Random();
	private int number;
	private final LinkedList<Command> commands = new LinkedList<>();
	public static boolean stopped = false;

	public Listener()
	{
		commands.add(new Bukkit());
		commands.add(new Calculate());
		commands.add(new ChangeNick());
		commands.add(new CraftBukkit());
//		commands.add(new DeAds());
		commands.add(new Decide());
		commands.add(new Draw());
		commands.add(new Help());
		commands.add(new Join());
		commands.add(new Kick());
		commands.add(new Forge());
		commands.add(new Leave());
		commands.add(new Leet());
		commands.add(new ListChans());
		commands.add(new LongURL());
		commands.add(new RandomLetter());
		commands.add(new MinusVowels());
		commands.add(new RandomNumber());
		commands.add(new Select());
		commands.add(new Source());
		commands.add(new Stop());
		commands.add(new SwitchOff());
		commands.add(new SwitchOn());
		commands.add(new Twitch());
		commands.add(new Twitter());
		commands.add(new Weather());
		commands.add(new XColor());
		commands.add(new YouTube());
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
			for(Command cmd : commands)
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
			for(Command cmd : commands)
			{
				if(cmd instanceof SwitchOn && event.getMessage().equalsIgnoreCase(p + cmd.getAlias()))
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
			if(event.getMessage().contains("www.youtube.com/watch?v=") || event.getMessage().contains("http://youtu.be/"))
			{
				YouTube.sendVideoStats(event);
				return;
			}

			if(!event.getMessage().startsWith(p))
				checkForLinkAndSendTitle(event);

			if(event.getMessage().toLowerCase().equalsIgnoreCase("re"))
				Utilities.chanMsg(event, "wb, " + event.getUser().getNick());
		}
	}

	private void checkForLinkAndSendTitle(MessageEvent event)
	{
		String[] args = Utilities.toArgs(event.getMessage());

		for(String s : args)
		{
			if(s.contains("www.") || s.contains("http://") || s.contains("https://"))
			{
				WebDriver driver = new HtmlUnitDriver();
				String title = null;
				
				driver.get(s);
				title = driver.getTitle();
				
				if(title != null)
					Utilities.chanMsg(event, "Page title: " + Colors.BOLD + title);
			}
		}
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent event)
	{
		if(enabled)
		{
			if(Utilities.validUser(event))
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
	public void onAction(final ActionEvent event) throws Exception 
	{
		if(enabled)
		{
			if(event.getMessage().toLowerCase().contains("kills himself"))
			{
				Runnable task = new Runnable()
				{
					@Override
					public void run()
					{
						event.getChannel().send().action("revives " + event.getUser().getNick());
					}
				};

				event.getChannel().send().message("https://i.imgur.com/1pOApkk.png");
				worker.schedule(task, 3, TimeUnit.SECONDS);
			}
		}	
	}
}