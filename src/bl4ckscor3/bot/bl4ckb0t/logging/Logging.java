package bl4ckscor3.bot.bl4ckb0t.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.KickEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.NickChangeEvent;
import org.pircbotx.hooks.events.NoticeEvent;
import org.pircbotx.hooks.events.PartEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.events.QuitEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Logging extends ListenerAdapter<Bot>
{
	private static boolean enabled = true;
	private static File f;
	private static FileWriter writer;
	private static boolean failed = true;

	public static void setup()
	{
		try
		{
			f = new File("bl4ckb0t.log");

			if(!f.exists())
				f.createNewFile();

			writer = new FileWriter(f);
			failed = false;
			enable();
		}
		catch(IOException e)
		{
			severe("Could not create logger!");
			e.printStackTrace();
			disable();
		}
	}

	public static void enable()
	{
		if(!failed)
		{
			enabled = true;
			start();
		}
	}

	public static void disable()
	{
		end();
		enabled = false;
	}

	public static void severe(String line)
	{
		log("[SEVERE] " + line);
	}

	public static void warn(String line)
	{
		log("[WARNING] " + line);
	}

	public static void info(String line)
	{
		log("[INFO] " + line);
	}

	public static void debug(String line)
	{
		if(Core.bot.isDevelopment())
			log("[DEBUG] " + line);
	}

	public static void message(String channel, String sender, String msg)
	{
		log("[MESSAGE] " + channel + " | " + sender + ": " + msg);
	}

	public static void noticeSent(String receiver, String msg)
	{
		log("[NOTICE] " + receiver + " | " + Core.bot.getNick() + " - " + msg);
	}

	public static void noticeReceived(String sender, String msg)
	{
		log("[NOTICE] " + sender + ": " + msg);
	}

	public static void pm(String sender, String msg)
	{
		log("[PM] " + sender + ": " + msg);
	}

	public static void action(String channel, String sender, String msg)
	{
		log("[ACTION] " + channel + " | *" + sender + " " + msg);
	}

	private static void start()
	{
		raw("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		raw("Started logging on " + Utilities.getCurrentDate().toString());
	}
	
	private static void end()
	{
		raw("Ended logging on " + Utilities.getCurrentDate().toString());
		raw("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	private static void raw(String line)
	{
		if(enabled)
		{
			if(!failed)
			{
				try
				{
					writer.write(line + "\n");
					writer.flush();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			System.out.println(line);
		}
	}
	
	private static void log(String line)
	{
		if(enabled)
		{
			if(!failed)
			{
				try
				{
					writer.write(Utilities.getCurrentDate().toString() + " " + line + "\n");
					writer.flush();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			System.out.println(Utilities.getCurrentDate().toString() + " " + line);
		}
	}

	/***************************Listeners***************************/

	@Override
	public void onMessage(MessageEvent<Bot> event) throws Exception
	{
		message(event.getChannel().getName(), event.getUser().getNick(), event.getMessage());
	}
	@Override
	public void onAction(ActionEvent<Bot> event) throws Exception
	{
		action(event.getChannel().getName(), event.getUser().getNick(), event.getMessage());
	}

	@Override
	public void onNotice(NoticeEvent<Bot> event) throws Exception
	{
		noticeReceived(event.getUser().getNick(), event.getMessage());
	}

	@Override
	public void onKick(KickEvent<Bot> event) throws Exception
	{
		if(event.getUser().getNick().equals(Core.bot.getNick()))
			warn("Bot kicked from " + event.getChannel().getName() + " for \"" + event.getReason() + "\".");
		else
			info(event.getRecipient().getNick() + " was kicked from " + event.getChannel().getName() + " for \"" + event.getReason() + "\".");
	}

	@Override
	public void onNickChange(NickChangeEvent<Bot> event) throws Exception
	{
		info(event.getOldNick() + " changed his nick to " + event.getNewNick() + ".");
	}

	@Override
	public void onPart(PartEvent<Bot> event) throws Exception
	{
		info(event.getUser().getNick() + " left " + event.getChannel().getName() + ": \"" + event.getReason() + "\".");
	}

	@Override
	public void onJoin(JoinEvent<Bot> event) throws Exception
	{
		if(!event.getUser().getNick().equals(Core.bot.getNick()))
			info(event.getUser().getNick() + " joined " + event.getChannel().getName() + ".");
	}

	@Override
	public void onQuit(QuitEvent<Bot> event) throws Exception
	{
		info(event.getUser().getNick() + " quit: \"" + event.getReason() + "\".");
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent<Bot> event) throws Exception
	{
		pm(event.getUser().getNick(), event.getMessage());
	}

	@Override
	public void onDisconnect(DisconnectEvent<Bot> event) throws Exception
	{
		severe("Disconnected from server!");
	}

	public static FileWriter getWriter()
	{
		return writer;
	}
}
