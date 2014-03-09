package bl4ckb0tX.core;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.commands.egg.Easter;
import bl4ckb0tX.commands.normal.ChangeNick;
import bl4ckb0tX.commands.normal.Debug;
import bl4ckb0tX.commands.normal.Help;
import bl4ckb0tX.commands.normal.Joke;
import bl4ckb0tX.commands.normal.Kick;
import bl4ckb0tX.commands.normal.McfUser;
import bl4ckb0tX.commands.normal.RandomStuff;
import bl4ckb0tX.commands.normal.Say;
import bl4ckb0tX.commands.normal.Stop;
import bl4ckb0tX.commands.normal.Switch;
import bl4ckb0tX.commands.normal.Test;
import bl4ckb0tX.commands.normal.Time;
import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Listener extends ListenerAdapter 
{
	private static final String p = "-";
	public static boolean enabled = true;
	public static boolean debug = false;

	@Override
	public void onJoin(JoinEvent event)
	{
		if(enabled)
		{
			if(!(event.getUser().getNick().equalsIgnoreCase("bl4ckb0t") || event.getUser().getNick().equalsIgnoreCase("bl4ckb0t1") || event.getUser().getNick().equalsIgnoreCase("bl4ckb0t2") || event.getUser().getNick().equalsIgnoreCase("bl4ckb0t3")))
			{
				event.getUser().send().notice("Welcome to the official channel of bl4ckscor3! Type '-help' for help and /topic to see the topic of the channel.");
			}
		}
	}

	@Override
	public void onMessage(MessageEvent event) throws Exception
	{
		if(enabled)
		{
			normalCommands(event);
			easterEggs(event);
			misc(event);
			
			if(Stuffz.getMessage(event).startsWith(p + "say") && debug)
				Say.exe(event);
			else if(Stuffz.getMessage(event).endsWith("We aren't spambots, are we, " + event.getBot().getNick() + "?") && Stuffz.getNick(event).equalsIgnoreCase("Maunz"))
				Stuffz.chanMsg(event, "No, we aren't, Maunz.");
		}
		
		if(Core.bot.getNick().equals("bl4ckb0t") && debug)
		{
			if(Stuffz.getMessage(event).equalsIgnoreCase(p + "disable"))
				Switch.off(event);
			else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "enable"))
				Switch.on(event);
			else if(Stuffz.getMessage(event).equals("?enabled"))
				Stuffz.chanMsg(event, "" + enabled);
			else if(Stuffz.getMessage(event).startsWith(p + "changenick"))
				ChangeNick.exe(event);
		}
	}

	public void normalCommands(MessageEvent event) throws Exception
	{
		if(Stuffz.getMessage(event).equalsIgnoreCase(p + "help"))
			Help.exe(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "time"))
			Time.exe(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "test"))
			Test.exe(event);
		else if(Stuffz.getMessage(event).startsWith(p + "stop") || Stuffz.getMessage(event).equalsIgnoreCase(event.getBot().getNick() + ", sleep!"))
			Stop.exe(event);
		else if(Stuffz.getMessage(event).startsWith(p + "kick"))
			Kick.exe(event);
		else if(Stuffz.getMessage(event).startsWith(p + "letter"))
			RandomStuff.letter(event);
		else if(Stuffz.getMessage(event).startsWith(p + "number"))
			RandomStuff.number(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "joke"))
			Joke.exe(event);
		else if(Stuffz.getMessage(event).equalsIgnoreCase(p + "debug"))
			Debug.exe(event);
		else if(Stuffz.getMessage(event).startsWith(p + "u"))
			McfUser.exe(event);
	}

	public void misc(MessageEvent event) throws Exception
	{
		if(Stuffz.getMessage(event).startsWith(">ide"))
			Stuffz.userMsg(event, "Wrong channel, pal!");
	}

	public void easterEggs(MessageEvent event)
	{
		Easter.init(event);
	}
}
