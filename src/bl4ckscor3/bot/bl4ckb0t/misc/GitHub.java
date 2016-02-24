package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class GitHub
{
	public static void showRepo(MessageEvent event, String link) throws MalformedURLException, IOException
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
		
		if(language.equals("null") || language.equals("") || language.equals(null))
			language = L10N.getString("helpMenu.noNotes", event).replace(".", "");
		
		Utilities.chanMsg(event, Colors.BOLD + "[GitHub] " + Colors.BOLD + name + " - " + description + 
				" | " + L10N.getString("github.mainLanguage", event).replace("#lang", language) + 
				" | " + L10N.getString("github.latestPush", event).replace("#push", latestPush) + 
				" | " + L10N.getString("github.watching", event).replace("#watching", watching) + 
				" | " + L10N.getString("github.stargazers", event).replace("#stars", stars) + 
				" | " + L10N.getString("github.forks", event).replace("#forks", forks) + 
				" | " + L10N.getString("github.issues", event).replace("#issues", issues));
	}

	public static void showCommit(MessageEvent event, String link) throws IOException
	{
		if(link.startsWith("www."))
			link = "http://" + link;
		
		String text = Jsoup.connect(link).get().select(".toc-diff-stats").text();
	
		Utilities.chanMsg(event, Colors.BOLD + "** " + Colors.NORMAL + Colors.PURPLE + L10N.getString("github.changedFiles", event).replace("#files", text.split(" ")[1]) + Colors.NORMAL + Colors.BOLD + " ** " + Colors.NORMAL +
				Colors.DARK_GREEN + L10N.getString("github.additions", event).replace("#additions", text.split(" ")[5]) + Colors.NORMAL + Colors.BOLD + " ** " + Colors.NORMAL +
				Colors.RED + L10N.getString("github.deletions", event).replace("#deletions", text.split(" ")[8]) + Colors.NORMAL + Colors.BOLD + " ** ");
	}
}
