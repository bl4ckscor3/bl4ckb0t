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
	 * @param channel The channel to show the information in
	 * @param link The link to the repository
	 */
	public static void showRepo(String channel, String link) throws MalformedURLException, IOException
	{
		if(link.startsWith("www."))
			link = "http://" + link;
		
		String name = link.split("/")[3] + "/" + link.split("/")[4];
		String description = "";
		String language = "";
		String latestPush = "";
		String watching = "";
		String stars = "";
		String forks = "";
		String issues = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://api.github.com/repos/" + name).openStream()));

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
			}
		}
		
		reader.close();
		
		if(link.split("/").length > 6 && link.split("/")[5].equals("tree"))
		{
			name += "/branches/" + link.split("/")[6];
			reader = new BufferedReader(new InputStreamReader(new URL("https://api.github.com/repos/" + name).openStream()));
			
			for(String s : reader.readLine().split(",\""))
			{
				String[] split = s.split(":");
				
				switch(split[0])
				{
					case "date\"":
						latestPush = split[1].replace("\"", "").replace("T", " ") + ":" + split[2] + ":" + split[3].replace("Z", "").replace("\"", "").replace("}", "") + " GMT";
						break;
				}
			}
			
			reader.close();
		}
		
		if(language.equals("null") || language.equals("") || language.equals(null))
			language = L10N.getString("helpMenu.noNotes", channel).replace(".", "");
		
		Utilities.sendStarMsg(channel, "[GitHub] " +  name + " - " + Colors.NORMAL + description,
				L10N.getString("github.mainLanguage", channel).replace("#lang", Colors.NORMAL + language),
				L10N.getString("github.latestPush", channel).replace("#push", Colors.NORMAL + latestPush),
				L10N.getString("github.watching", channel).replace("#watching", Colors.NORMAL + watching),
				L10N.getString("github.stargazers", channel).replace("#stars", Colors.NORMAL + stars),
				L10N.getString("github.forks", channel).replace("#forks", Colors.NORMAL + forks),
				L10N.getString("github.issues", channel).replace("#issues", Colors.NORMAL + issues));
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
		
		String title = Jsoup.connect(link).get().title();
		String sha = title.substring(title.lastIndexOf('@') + 1).split(" ")[0];
		String name = title.substring(0, title.lastIndexOf('@')).split(" ")[title.substring(0, title.lastIndexOf('@')).split(" ").length - 1];
		System.out.println(name);
		int changedFiles = 0;
		String additions = "";
		String deletions = "";
		boolean files = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://api.github.com/repos/" + name + "/commits/" + sha).openStream()));

		for(String s : reader.readLine().split(",\""))
		{
			String[] split = s.split(":");

			switch(split[0])
			{
				case "files\"":
					files = true;
					break;
				case "additions\"":
					if(!files)
						additions = split[1].replace(",", "");
					break;
				case "deletions\"":
					if(!files)
						deletions = split[1].replace("}", "");
					break;
				case "filename\"":
					if(files)
						changedFiles++;
			}
		}
		
		Utilities.sendStarMsg(channel, "[GitHub] " + Colors.NORMAL + name + "@" + sha,
				Colors.PURPLE + L10N.getString("github.changedFiles", channel).replace("#files", Colors.BOLD + changedFiles),
				Colors.DARK_GREEN + L10N.getString("github.additions", channel).replace("#additions", Colors.BOLD + additions),
				Colors.RED + L10N.getString("github.deletions", channel).replace("#deletions", Colors.BOLD + deletions));
	}

	/**
	 * Shows information about a GitHub issue/pull request
	 * @param channel The channel to show the information in
	 * @param link The link to the issue/pull request
	 */
	public static void showIssue(String channel, String link) throws IOException
	{
		if(link.startsWith("www."))
			link = "http://" + link;

		String name = link.split("/")[3] + "/" + link.split("/")[4];
		String number = link.split(link.contains("issues") ? "issues/" : "pull/")[1].replace("/", "");
		String title = " ";
		String username = " ";
		String state = " ";
		String comments = " ";
		boolean user = false;
		boolean pr = false;
		BufferedReader reader;
		
		if(number.contains("#"))
			number = number.split("#")[0];
		
		reader = new BufferedReader(new InputStreamReader(new URL("https://api.github.com/repos/" + name + "/issues/" + number).openStream()));

		for(String s : reader.readLine().split(",\""))
		{
			String[] split = s.split(":");

			switch(split[0])
			{
				case "title\"":
					title = split[1].replace("\"", "");
					break;
				case "user\"":
					if(!user)
					{
						username = split[2].replace("\"", "");
						user = true;
					}
					
					break;
				case "state\"":
					state = split[1].replace("\"", "");
					break;
				case "comments\"":
					comments = split[1].replace(",", "");
					break;
				case "pull_request\"":
					pr = true;
			}
		}
		
		Utilities.sendStarMsg(channel, "[GitHub] " + Colors.NORMAL + name + " - " + (pr ? "Pull Request " : "Issue ") + "#" + number,
				L10N.getString("github.title", channel).replace("#title", Colors.NORMAL + title),
				L10N.getString("github.createdBy", channel).replace("#creator", Colors.NORMAL + username),
				L10N.getString("github.state", channel).replace("#state", Colors.NORMAL + state),
				L10N.getString("github.comments", channel).replace("#amount", Colors.NORMAL + comments));
	}
}
