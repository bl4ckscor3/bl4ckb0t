package bl4ckscor3.bot.bl4ckb0t.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.channel.Remind;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;

public class Reminder
{
	public static int latestId = 1;
	private int id;
	private String issuedUser;
	private String issuedChannel;
	private String ev;
	private ScheduledFuture<?> thread;
	
	public Reminder(MessageEvent event, String e, long timeDue)
	{
		id = latestId++;
		issuedUser = event.getUser().getNick();
		issuedChannel = event.getChannel().getName();
		ev = e;
		thread = Executors.newSingleThreadScheduledExecutor().schedule(() -> {
			Utilities.chanMsg(event, L10N.getString("remind.reminder", event).replace("#user", event.getUser().getNick()).replace("#event", e));
			Remind.reminders.remove(this);
		}, timeDue, TimeUnit.MILLISECONDS); 
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getIssuedUser()
	{
		return issuedUser;
	}
	
	public String getIssuedChannel()
	{
		return issuedChannel;
	}
	
	public String getEvent()
	{
		return ev;
	}
	
	public long getRemainingTime()
	{
		return thread.getDelay(TimeUnit.MILLISECONDS);
	}
	
	public void stop()
	{
		thread.cancel(true);
		Remind.reminders.remove(this);
	}
}
