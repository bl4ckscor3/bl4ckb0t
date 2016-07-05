package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class GitHub
{
	/**
	 * Shows information about a GitHub repository
	 * @param channel The channel to show the info in
	 * @param link The link to the repo
	 */
	public static void showRepo(String channel, String link) throws MalformedURLException, IOException
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
			language = L10N.getString("helpMenu.noNotes", channel).replace(".", "");
		
		Utilities.sendMessage(channel, Colors.BOLD + "[GitHub] " + Colors.BOLD + name + " - " + description + 
				" | " + L10N.getString("github.mainLanguage", channel).replace("#lang", language) + 
				" | " + L10N.getString("github.latestPush", channel).replace("#push", latestPush) + 
				" | " + L10N.getString("github.watching", channel).replace("#watching", watching) + 
				" | " + L10N.getString("github.stargazers", channel).replace("#stars", stars) + 
				" | " + L10N.getString("github.forks", channel).replace("#forks", forks) + 
				" | " + L10N.getString("github.issues", channel).replace("#issues", issues));
	}

	/**
	 * Shows information about a git commit
	 * @param channel The channel to show the information in
	 * @param link The link to the commit
	 */
	public static void showCommit(String channel, String link) throws IOException
	{
		if(link.startsWith("www."))
			link = "http://" + link;
		
		String text = Jsoup.connect(link).get().select(".toc-diff-stats").text();
	
		Utilities.sendStarMsg(channel,
				Colors.NORMAL + Colors.PURPLE + L10N.getString("github.changedFiles", channel).replace("#files", text.split(" ")[1]),
				Colors.NORMAL + Colors.DARK_GREEN + L10N.getString("github.additions", channel).replace("#additions", text.split(" ")[5]),
				Colors.NORMAL + Colors.RED + L10N.getString("github.deletions", channel).replace("#deletions", text.split(" ")[8]));
	}
}
