package bl4ckb0tX.core;

import java.util.Random;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckb0tX.commands.egg.Easter;
import bl4ckb0tX.commands.normal.ChangeNick;
import bl4ckb0tX.commands.normal.Debug;
import bl4ckb0tX.commands.normal.Decide;
import bl4ckb0tX.commands.normal.Draw;
import bl4ckb0tX.commands.normal.Fun;
import bl4ckb0tX.commands.normal.GirlBalls;
import bl4ckb0tX.commands.normal.Help;
import bl4ckb0tX.commands.normal.Joke;
import bl4ckb0tX.commands.normal.Kick;
import bl4ckb0tX.commands.normal.LatestForge;
import bl4ckb0tX.commands.normal.McfUser;
import bl4ckb0tX.commands.normal.RandomStuff;
import bl4ckb0tX.commands.normal.Say;
import bl4ckb0tX.commands.normal.Stop;
import bl4ckb0tX.commands.normal.Switch;
import bl4ckb0tX.commands.normal.Test;
import bl4ckb0tX.commands.normal.Time;
import bl4ckb0tX.commands.normal.Twitch;
import bl4ckb0tX.commands.normal.Twitter;
import bl4ckb0tX.commands.normal.YouTube;
import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Listener extends ListenerAdapter 
{
	private static final String p = "-";
	public static boolean enabled = true;
	public static boolean debug = false;
	public static boolean fun = false;

	@Override
	public void onMessage(MessageEvent event) throws Exception
	{		
		if(enabled)
		{
			normalCommands(event);
			easterEggs(event);
			misc(event);

			if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "say") && debug)
				Say.exe(event);
			else if(Stuffz.getMessage(event).toLowerCase().endsWith("We aren't spambots, are we, bl4ckb0t?") && Stuffz.getNick(event).equalsIgnoreCase("Maunz"))
				Stuffz.chanMsg(event, "No, we aren't, Maunz.");
		}

		if(debug)
		{
			if(Core.bot.getNick().equals("bl4ckb0t"))
			{
				if(Stuffz.getMessage(event).equalsIgnoreCase(p + "disable"))
					Switch.off(event);
				else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "enable"))
					Switch.on(event);
				else if(Stuffz.getMessage(event).equals("?enabled"))
					Stuffz.chanMsg(event, "" + enabled);
				else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "changenick"))
					ChangeNick.exe(event);
			}

			if(enabled)
			{
				if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "changenick"))
					ChangeNick.exe(event);
			}
		}

		if(fun && !(Stuffz.getMessage(event).equalsIgnoreCase(p + "fun")))
		{
			Stuffz.chanMsg(event, Stuffz.getMessage(event));
		}
	}

	public void normalCommands(MessageEvent event) throws Exception
	{
		if(Stuffz.getMessage(event).equalsIgnoreCase(p + "help"))
			Help.exe(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "time"))
			Time.exe(event);
		else if(Stuffz.getMessage(event).startsWith(p + "test"))
			Test.exe(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "stop") || Stuffz.getMessage(event).equalsIgnoreCase(event.getBot().getNick() + ", sleep"))
			Stop.exe(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "kick"))
			Kick.exe(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "letter"))
			RandomStuff.letter(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "number"))
			RandomStuff.number(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "joke"))
			Joke.exe(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "debug"))
			Debug.exe(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "u"))
			McfUser.exe(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "yt"))
			YouTube.exe(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "tw"))
			Twitter.exe(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "tv"))
			Twitch.exe(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "girlballs"))
			GirlBalls.exe(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "draw"))
			Draw.exe(event);
		else if(Stuffz.getMessage(event).toLowerCase().startsWith(p + "latestforge"))
			LatestForge.exe(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "fun") && Stuffz.validUser(event))
			Fun.exe(event);
		else if(Stuffz.getMessage(event).startsWith(p + "decide") && Stuffz.getMessage(event).endsWith("?"))
			Decide.exe(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "dl") && (Stuffz.validUser(event) || Stuffz.getNick(event).equalsIgnoreCase("TehKitti")))
			Stuffz.userMsg(event, "Update me right here: https://www.dropbox.com/s/32pf41acn4pato5/bl4ckb0t.jar");
	}

	public void misc(MessageEvent event) throws Exception
	{
		if(Stuffz.getMessage(event).toLowerCase().startsWith(">ide"))
			Stuffz.userMsg(event, "Wrong channel, pal!");
		else if(Stuffz.getMessage(event).equalsIgnoreCase("Wheres your source bl4ckb0t?"))
			Stuffz.chanMsg(event, "Here it is: https://github.com/bl4ckscor3/bl4ckb0tX");
		else if(Stuffz.getMessage(event).equalsIgnoreCase("re"))
			Stuffz.chanMsg(event, "wb, " + Stuffz.getNick(event));
		else if(Stuffz.getMessage(event).toLowerCase().startsWith("lol"))
		{
			Random r = new Random();
			int number = r.nextInt(21);

			if(number == 5)
				Stuffz.chanMsg(event, "Yeah, lol.");
		}
		
		Random r = new Random();
		int number = r.nextInt(1000);
		
		if(number == 851)
			Core.bot.sendIRC().action("#bl4ckscor3", "is masturbating now.");
	}
	
	public void easterEggs(MessageEvent event)
	{
		Easter.init(event);
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent event)
	{
		if(enabled)
		{
			if(Stuffz.validUser(event))
			{
				if(event.getMessage().startsWith("*"))
					Core.bot.sendIRC().action("#bl4ckscor3", event.getMessage().substring(1));
				else
					Core.bot.sendIRC().message("#bl4ckscor3", event.getMessage());
			}		
			else
				Core.bot.sendIRC().message("bl4ckscor3", event.getUser().getNick() + " just sent me this message: " + event.getMessage());
			Core.bot.sendIRC().message("bl4ckgon3", event.getUser().getNick() + " just sent me this message: " + event.getMessage());
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
