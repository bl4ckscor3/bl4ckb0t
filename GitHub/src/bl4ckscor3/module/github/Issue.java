package bl4ckscor3.module.github;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.LinkAction;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Issue implements LinkAction
{
	private L10N l10n;
	
	public Issue(L10N l10n)
	{
		this.l10n = l10n;
	}
	
	@Override
	public void handle(String channel, String user, String link) throws Exception
	{
		if(link.startsWith("www."))
			link = "http://" + link;

		String name = link.split("/")[3] + "/" + link.split("/")[4];
		String number = link.split(link.contains("issues") ? "issues/" : "pull/")[1].replace("/", "");
		String title = " ";
		String username = " ";
		String state = " ";
		String comments = " ";
		boolean author = false;
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
					if(!author)
					{
						username = split[2].replace("\"", "");
						author = true;
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
		
		Utilities.sendStarMsg(channel, l10n.translate("issue.header", channel).replace("#name", name).replace("#type", (pr ? "Pull Request" : "Issue")).replace("#number", number),
				l10n.translate("issue.title", channel).replace("#title", title),
				l10n.translate("issue.createdBy", channel).replace("#creator", username),
				l10n.translate("issue.state", channel).replace("#state", state),
				l10n.translate("issue.comments", channel).replace("#amount", comments));
	}
	
	@Override
	public boolean isValid(String channel, String link)
	{
		return link.contains("github.com") && (link.contains("issues") || link.contains("pull"));
	}
	
	@Override
	public int getPriority()
	{
		return 80;
	}
}
