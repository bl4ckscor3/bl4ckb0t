package bl4ckb0tX.core;

import java.util.Random;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckb0tX.commands.normal.Bukkit;
import bl4ckb0tX.commands.normal.Calc;
import bl4ckb0tX.commands.normal.ChangeNick;
import bl4ckb0tX.commands.normal.CraftBukkit;
import bl4ckb0tX.commands.normal.Decide;
import bl4ckb0tX.commands.normal.Draw;
import bl4ckb0tX.commands.normal.GirlBalls;
import bl4ckb0tX.commands.normal.Help;
import bl4ckb0tX.commands.normal.Kick;
import bl4ckb0tX.commands.normal.LatestForge;
import bl4ckb0tX.commands.normal.Leet;
import bl4ckb0tX.commands.normal.McfUser;
import bl4ckb0tX.commands.normal.MinusVowels;
import bl4ckb0tX.commands.normal.RandomStuff;
import bl4ckb0tX.commands.normal.Stop;
import bl4ckb0tX.commands.normal.Switch;
import bl4ckb0tX.commands.normal.Twitch;
import bl4ckb0tX.commands.normal.Twitter;
import bl4ckb0tX.commands.normal.Update;
import bl4ckb0tX.commands.normal.YouTube;
import bl4ckb0tX.util.Utilities;

@SuppressWarnings("rawtypes")
public class Listener extends ListenerAdapter 
{
	private static final String p = "-";
	public static boolean enabled = true;
	public static boolean isCounting = false;
	private Random r = new Random();
	private int number;

	@Override
	public void onMessage(MessageEvent event) throws Exception
	{	
		if(enabled)
		{
			normalCommands(event);
			misc(event);

			if(event.getMessage().toLowerCase().endsWith("We aren't spambots, are we, bl4ckb0t?") && event.getUser().getNick().equalsIgnoreCase("Maunz"))
				Utilities.chanMsg(event, "No, we aren't, Maunz.");
			else if(event.getMessage().equalsIgnoreCase(p + "disable"))
				Switch.off(event);
			else if(event.getMessage().equals("?enabled"))
				Utilities.chanMsg(event, "" + enabled);
			else if(event.getMessage().toLowerCase().startsWith(p + "changenick"))
				ChangeNick.exe(event);
		}
		else
		{
			if(event.getMessage().equalsIgnoreCase(p + "enable"))
				Switch.on(event);
		}
	}	

	public void normalCommands(MessageEvent event) throws Exception
	{
		if(event.getMessage().equalsIgnoreCase(p + "help"))
			Help.exe(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "stop") || event.getMessage().equalsIgnoreCase(event.getBot().getNick() + ", sleep"))
			Stop.exe(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "kick"))
			Kick.exe(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "letter"))
			RandomStuff.letter(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "number"))
			RandomStuff.number(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "u"))
			McfUser.exe(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "yt"))
			YouTube.exe(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "tw"))
			Twitter.exe(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "tv"))
			Twitch.exe(event);
		else if(event.getMessage().equalsIgnoreCase(p + "girlballs"))
			GirlBalls.exe(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "draw"))
			Draw.exe(event);
		else if(event.getMessage().toLowerCase().startsWith(p + "latestforge"))
			LatestForge.exe(event);
		else if(event.getMessage().startsWith(p + "decide"))
			Decide.exe(event);
		else if(event.getMessage().equalsIgnoreCase(p + "dl") && (Utilities.validUser(event) || event.getUser().getNick().equalsIgnoreCase("TehKitti")))
			Utilities.userMsg(event, "Update me right here: https://www.dropbox.com/s/32pf41acn4pato5/bl4ckb0t.jar");
		else if(event.getMessage().startsWith(p + "leet"))
			Leet.exe(event);
		else if(event.getMessage().startsWith(p + "vowels"))
			MinusVowels.exe(event);
		else if(event.getMessage().startsWith(p + "calc"))
			Calc.exe(event);
		else if(event.getMessage().startsWith(p + "bukkit"))
			Bukkit.exe(event);
		else if(event.getMessage().startsWith(p + "cbukkit"))
			CraftBukkit.exe(event);
	}

	public void misc(MessageEvent event) throws Exception
	{
		if(event.getMessage().toLowerCase().startsWith(">ide"))
			Utilities.userMsg(event, "Wrong channel, pal!");
		else if(event.getMessage().equalsIgnoreCase(Core.bot.getNick() + ", source pls") || event.getMessage().equalsIgnoreCase(Core.bot.getNick() + ": source pls"))
			Utilities.chanMsg(event, "Here it is: https://github.com/bl4ckscor3/bl4ckb0tX");
		else if(event.getMessage().equalsIgnoreCase("re"))
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
					Update.exe();
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
			else if(event.getUser().getNick().equalsIgnoreCase("Zorbas2004"))
			{
				Core.bot.sendIRC().message("bl4ckscor3", "Zorbas2004 joined.");
				Core.bot.sendIRC().message("bl4ckgon3", "Zorbas2004 joined.");
			}
			else if(!(event.getUser().getNick().equalsIgnoreCase("bl4ckb0t") || event.getUser().getNick().equalsIgnoreCase("bl4ckb0t1") || event.getUser().getNick().equalsIgnoreCase("bl4ckb0t2") || event.getUser().getNick().equalsIgnoreCase("bl4ckb0t3")))
				event.getUser().send().notice("Welcome to the official channel of bl4ckscor3! Type '-help' for help and /topic to see the topic of the channel.");
		}
	}
}
