package bl4ckb0tX.commands.normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

public class McfUser
{
	private static String[] tasks = {
		"profile",
		"posts",
		"topics",
		"infractions",
		"issued_infractions",
		"reputation",
		"reputation_given",
		"reputation_received",
		"feed",
		"videos",
		"friends",
		"pm",
		"names",
		"admin",
		"edit",
		"modcp",
		"issue_infraction",
		"validate",
		"suspend",
		"ip_history",
		"merge",
		"moderator_logs",
		"administrator_logs",
		"login"
	};

	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 3)
		{
			if(isUserValid(args[1]))
				if(isTaskValid(args[2]))
					Utilities.respond(event, "http://u.mcf.li/" + args[1] + "/" + args[2], false);
				else
					Utilities.userMsg(event, "valid tasks are: " + getTaskList());
			else
				Utilities.respond(event, "the user " + args[1] + " doesn't exist.", true);
		}
	}

	private static boolean isUserValid(String user) throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://u.mcf.li/" + user).openStream()));
		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < 20; i++)
		{
			builder.append(reader.readLine());
		}

		if(builder.toString().toLowerCase().contains("404"))
			return false;
		else
			return true;
	}

	private static boolean isTaskValid(String task)
	{
		for(int i = 0; i < tasks.length; i++)
		{
			if(task.equalsIgnoreCase(tasks[i]))
				return true;
		}
		return false;
	}

	private static String getTaskList()
	{
		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < tasks.length; i++)
		{
			builder.append("'" + tasks[i] + "'" + " | ");
		}
		builder.delete(builder.length() - 2, builder.length());

		return builder.toString();
	}
}
