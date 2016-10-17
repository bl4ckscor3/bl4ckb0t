package bl4ckscor3.module.github;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;

import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.LinkAction;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Commit implements LinkAction
{
	private L10N l10n;
	
	public Commit(L10N l10n)
	{
		this.l10n = l10n;
	}
	
	@Override
	public void handle(String channel, String user, String link) throws Exception
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
		
		Utilities.sendStarMsg(channel, l10n.translate("commit.header", channel).replace("#name", name).replace("#sha", sha),
				l10n.translate("commit.changedFiles", channel).replace("#files", "" + changedFiles),
				l10n.translate("commit.additions", channel).replace("#additions", additions),
				l10n.translate("commit.deletions", channel).replace("#deletions", deletions));
	}
	
	@Override
	public boolean isValid(String channel, String link)
	{
		return link.contains("git.io") || (link.contains("github.com") && link.contains("commit"));
	}
	
	@Override
	public int getPriority()
	{
		return 90;
	}
}
