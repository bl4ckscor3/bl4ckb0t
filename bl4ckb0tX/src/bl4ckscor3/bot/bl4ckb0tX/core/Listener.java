package bl4ckscor3.bot.bl4ckb0tX.core;

import java.util.LinkedList;
import java.util.Random;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.commands.*;
import bl4ckscor3.bot.bl4ckb0tX.commands.Number;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Listener extends ListenerAdapter 
{
	private static final String p = "-";
	public static boolean enabled = true;
	public static boolean isCounting = false;
	private Random r = new Random();
	private int number;
	private final LinkedList<Command> commands = new LinkedList<>();

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
		commands.add(new Letter());
		commands.add(new McfUser());
		commands.add(new MinusVowels());
		commands.add(new Number());
		commands.add(new Source());
		commands.add(new Stop());
		commands.add(new SwitchOff());
		commands.add(new SwitchOn());
		commands.add(new Twitch());
		commands.add(new Twitter());
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

			if(event.getMessage().equals("?enabled"))
				Utilities.chanMsg(event, "" + enabled);
		}
		else
		{
			for(Command cmd : commands)
			{
				if(cmd instanceof SwitchOn && "enable".equalsIgnoreCase(p + cmd.getAlias()))
				{
					cmd.exe(event);
					return;
				}
			}
		}
	}	

	public void misc(MessageEvent event) throws Exception
	{
		if(event.getMessage().equalsIgnoreCase("re"))
			Utilities.chanMsg(event, "wb, " + event.getUser().getNick());
		else if(event.getMessage().toLowerCase().startsWith("lol"))
		{
			number = r.nextInt(21);

			if(number == 5)
				Utilities.chanMsg(event, "Yeah, lol.");
		}
		else if(event.getMessage().toLowerCase().startsWith("subaraki is awesome"))
			Utilities.respond(event, "http://pastebin.com/Vtpb9DWg", true);

		int number = r.nextInt(1000);

		if(number == 851)
			Core.bot.sendIRC().action("#bl4ckscor3", "is masturbating now.");
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
				else if(event.getMessage().equals("update"))
				{
					if(isCounting)
					{
						Core.bot.sendIRC().message("bl4ckscor3", "I'm already asking TehKitti to update me.");
						Core.bot.sendIRC().message("bl4ckgon3", "I'm already asking TehKitti to update me.");
					}
					else
					{
						Update.exe();
						isCounting = true;
					}
				}
				else if(event.getMessage().equals("updating?"))
				{
					Core.bot.sendIRC().message("bl4ckscor3", "" + isCounting);
					Core.bot.sendIRC().message("bl4ckgon3", "" + isCounting);
				}
				else
					Core.bot.sendIRC().message("#bl4ckscor3", event.getMessage());
			}		
			else
			{
				if(event.getUser().getNick().equals("TehKitti") && event.getMessage().equals("stop") && isCounting)
				{
					isCounting = false;
					Core.bot.sendIRC().message("TehKitti", "Update me right here: https://www.dropbox.com/s/32pf41acn4pato5/bl4ckb0t.jar");
				}
				else
				{	
					String[] validUsers = Utilities.getValidUsers();

					for(int i = 0; i < Utilities.getValidUsers().length; i++)
					{
						Core.bot.sendIRC().message(validUsers[i], event.getUser().getNick() + " just sent me this message: " + event.getMessage());
					}
				}
			}
		}
	}

	@Override
	public void onJoin(JoinEvent event)
	{
		if(enabled)
		{
			if(event.getUser().getNick().equalsIgnoreCase("Maunz"))
				event.getChannel().send().message("Maunz!!!!!!!!!!!!!!!!!! <3 MY LOVE!!! I LOVE YOU :* :* :* <3 <3 <3");
			else if(!(event.getUser().getNick().equalsIgnoreCase("bl4ckb0t") || event.getUser().getNick().equalsIgnoreCase("bl4ckb0t1") || event.getUser().getNick().equalsIgnoreCase("bl4ckb0t2") || event.getUser().getNick().equalsIgnoreCase("bl4ckb0t3")))
				event.getUser().send().notice("Welcome to the official channel of bl4ckscor3! Type '-help' for help and /topic to see the topic of the channel.");
		}
	}
}
