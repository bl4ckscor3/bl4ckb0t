package bl4ckscor3.module.github;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.LinkAction;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Repo implements LinkAction
{
	private L10N l10n;
	
	public Repo(L10N l10n)
	{
		this.l10n = l10n;
	}
	
	@Override
	public void handle(String channel, String user, String link) throws Exception
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
			language = l10n.translate("help.none", channel).replace(".", "").trim();
		
		Utilities.sendStarMsg(channel, l10n.translate("repo.header", channel).replace("#name", name).replace("#description", description),
				l10n.translate("repo.mainLanguage", channel).replace("#lang", language),
				l10n.translate("repo.latestPush", channel).replace("#push", latestPush),
				l10n.translate("repo.watching", channel).replace("#watching", watching),
				l10n.translate("repo.stargazers", channel).replace("#stars", stars),
				l10n.translate("repo.forks", channel).replace("#forks", forks),
				l10n.translate("repo.issues", channel).replace("#issues", issues));
	}
	
	@Override
	public boolean isValid(String channel, String link)
	{
		return link.contains("github.com");
	}
	
	@Override
	public int getPriority()
	{
		return 70;
	}
}
