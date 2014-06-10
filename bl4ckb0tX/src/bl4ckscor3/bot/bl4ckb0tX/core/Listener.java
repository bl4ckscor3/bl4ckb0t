package bl4ckscor3.bot.bl4ckb0tX.core;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.commands.*;
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
		commands.add(new Decide());
		commands.add(new Draw());
		commands.add(new GirlBalls());
		commands.add(new Help());
		commands.add(new Kick());
		commands.add(new LatestForge());
		commands.add(new Leet());
		commands.add(new LongURL());
		commands.add(new RandomLetter());
		commands.add(new McfUser());
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
		String cmdName = event.getMessage().split(" ")[0];

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
			Utilities.chanMsg(event, "" + enabled);

		if(enabled)
		{
			if(event.getMessage().equalsIgnoreCase("re"))
				Utilities.chanMsg(event, "wb, " + event.getUser().getNick());
			if(event.getMessage().toLowerCase().startsWith("lol"))
			{
				number = r.nextInt(21);

				if(number == 5)
					Utilities.chanMsg(event, "Yeah, lol.");
			}
			else if(event.getMessage().toLowerCase().startsWith("subaraki is awesome"))
				Utilities.respond(event, "http://pastebin.com/Vtpb9DWg", true);
		}
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent event)
	{
		if(enabled)
		{
			if(Utilities.validUser(event))
			{
				if(event.getMessage().startsWith("*"))
					Core.bot.sendIRC().action("#bl4ckscor3", event.getMessage().substring(1));
				else if(event.getMessage().startsWith("msg"))
				{
					String[] parts = Utilities.toArgs(event.getMessage());
					String[] msg = Utilities.toArgs(event.getMessage()); //"= Utilities.toArgs(event.getMessage())" <--- only a placeholder to avoid npe's
					StringBuilder builder = new StringBuilder();

					for(int i = 2; i < parts.length; i++)
					{
						msg[i - 2] = parts[i];
					}

					msg[msg.length - 2] = "";
					msg[msg.length - 1] = "";

					for(String s : msg)
					{
						builder.append(s + " ");
					}

					Core.bot.sendIRC().message(parts[1], builder.toString());
				}
				else
					Core.bot.sendIRC().message("#bl4ckscor3", event.getMessage());
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