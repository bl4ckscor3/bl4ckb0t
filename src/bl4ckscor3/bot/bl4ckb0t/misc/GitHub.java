package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class GitHub
{
	public static void showRepo(MessageEvent<Bot> event, String link) throws MalformedURLException, IOException
	{
		BufferedReader reader;
		String name = "";
		String description = "";
		String language = "";
		String latestPush = "";
		String watching = "";
		String stars = "";
		String forks = "";
		String issues = "";
		
		if(link.startsWith("www."))
			link = "http://" + link;
		
		name = link.split("/")[3] + "/" + link.split("/")[4];
		reader = new BufferedReader(new InputStreamReader(new URL("https://api.github.com/repos/" + name).openStream()));

		for(String s : reader.readLine().split(",\""))
		{
			String[] split = s.split(":");
			
			switch(split[0])
			{
				case "description\"":
					description = split[1].replace(split[1].charAt(0) + "", "").replace(split[1].charAt(split[1].length() - 1) + "", "");
					break;
				case "pushed_at\"":
					latestPush = split[1].replace("\"", "").replace("T", " ") + ":" + split[2] + ":" + split[3].replace("Z", "").replace("\"", "") + " GMT";
					break;
				case "stargazers_count\"":
					stars = split[1];
					break;
				case "subscribers_count\"":
					watching = split[1].replace("}", "");
					break;
				case "language\"":
					language = split[1].replace("\"", "");
					break;
				case "forks_count\"":
					forks = split[1];
					break;
				case "open_issues_count\"":
					issues = split[1];
					break;
			}
		}
		
		reader.close();
		Utilities.chanMsg(event, Colors.BOLD + "[GitHub] " + Colors.BOLD + name + " - " + description + " | Main language: " + language + " | Latest push: " + latestPush + " | Watching: " + watching + " | Stargazers: " + stars + " | Forks: " + forks + " | Open issues: " + issues);
	}

	public static void showCommit(MessageEvent<Bot> event, String link) throws IOException
	{
		String text;
		String changed;
		String additions;
		String deletions;
		
		if(link.startsWith("www."))
			link = "http://" + link;
		
		text = Jsoup.connect(link).get().select(".toc-diff-stats").text();
		changed = text.split(" ")[1];
		additions = text.split(" ")[5];
		deletions = text.split(" ")[8];
		Utilities.chanMsg(event, Colors.BOLD + "** " + Colors.NORMAL + Colors.PURPLE + "Changed files: " + changed + Colors.NORMAL + Colors.BOLD + " ** " + Colors.NORMAL +
				Colors.DARK_GREEN + "Additions: " + additions + Colors.NORMAL + Colors.BOLD + " ** " + Colors.NORMAL +
				Colors.RED + "Deletions: " + deletions + Colors.NORMAL + Colors.BOLD + " ** ");
	}
}
