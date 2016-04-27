package bl4ckscor3.bot.bl4ckb0t.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

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
	private File f;

	/**
	 * Saves and manages a reminder
	 * @param user The user this Reminder belongs to
	 * @param channel The channel this Reminder got issued from
	 * @param e The reminder text
	 * @param timeDue How long to wait between issuing the Reminder and reminding the person
	 * @param load Wether or not the reminder gets loaded from a saved reminder
	 */
	public Reminder(String user, String channel, String e, long timeDue, boolean load) throws URISyntaxException, IOException
	{
		id = latestId++;
		
		if(load)
		{
			if(timeDue <= 0)
			{
				Utilities.sendMessage(channel, L10N.getString("remind.reminder.past", channel).replace("#user", user).replace("#event", e).replace("#time", new TimeParser().lts(0 - timeDue, "%sd%sh%sm%ss")));
				latestId--;
				return;
			}
		}

		File folder = new File(Utilities.getJarLocation() + "/reminders");
		ArrayList<String> lines = new ArrayList<String>();

		f = new File(Utilities.getJarLocation() + "/reminders/" + id + ".txt");

		if(!folder.exists())
			folder.mkdirs();

		if(!f.exists())
			f.createNewFile();

		issuedUser = user;
		issuedChannel = channel;
		ev = e;		
		thread = Executors.newSingleThreadScheduledExecutor().schedule(() -> {
			Utilities.sendMessage(channel, L10N.getString("remind.reminder", channel).replace("#user", user).replace("#event", e));
			Remind.reminders.remove(this);
			f.delete();
		}, timeDue, TimeUnit.MILLISECONDS);

		if(!load)
		{
			lines.add("issuedUser: " + issuedUser);
			lines.add("issuedChannel: " + issuedChannel);
			lines.add("event: " + ev);
			lines.add("timeDue: " + (System.currentTimeMillis() + timeDue));
			FileUtils.writeLines(f, lines);
		}
		else
			Remind.reminders.add(this);
	}

	/**
	 * @return The ID of the Reminder
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return The user this Reminder belongs to
	 */
	public String getIssuedUser()
	{
		return issuedUser;
	}

	/**
	 * @return The channel this Reminder got issued from
	 */
	public String getIssuedChannel()
	{
		return issuedChannel;
	}

	/**
	 * @return The text of this Reminder
	 */
	public String getEvent()
	{
		return ev;
	}

	/**
	 * @return How long it's left until reminding the user
	 */
	public long getRemainingTime()
	{
		return thread.getDelay(TimeUnit.MILLISECONDS);
	}

	/**
	 * Stops the reminder
	 */
	public void stop()
	{
		thread.cancel(true);
		Remind.reminders.remove(this);
		f.delete();
	}

	/**
	 * Loads reminders from the filesystem, if existing
	 */
	public static void loadReminders() throws URISyntaxException, NumberFormatException, IOException
	{
		File folder = new File(Utilities.getJarLocation() + "/reminders");

		if(!folder.exists())
			return;
		
		for(File f : folder.listFiles())
		{
			List<String> lines = FileUtils.readLines(f);
			String user = lines.get(0).split(": ")[1];
			String channel = lines.get(1).split(": ")[1];
			String e = lines.get(2).split(": ")[1];
			long timeDue = Long.parseLong(lines.get(3).split(": ")[1]) - System.currentTimeMillis();
			Reminder r = new Reminder(user, channel, e, timeDue, true);
			
			if(timeDue <= 0)
				f.delete();
			else if(!f.getName().split(".txt")[0].equals("" + r.getId()))
			{
				File newFile = new File(Utilities.getJarLocation() + "/reminders/" + r.getId() + ".txt");
				
				FileUtils.writeLines(newFile, lines);
				f.delete();
				Utilities.sendMessage(channel, L10N.getString("remind.reminder.loaded", channel).replace("#user", user).replace("#event", e).replace("#id", "" + r.getId()));
			}
		}
	}
}
