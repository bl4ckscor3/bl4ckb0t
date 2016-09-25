package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class LinkManager
{
	/**
	 * Takes actions when specific links are sent
	 * @param message The message containing links
	 * @param channel The channel the message got sent in
	 * @param user The user the message got sent by
	 * @param tweet Wether or not the links are searched in a Tweet or not (this is to prevent an infinite loop with nested Tweets)
	 */
	public static void handleLink(String message, String channel, String user, boolean tweet) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(message);
		
		for(String s : args)
		{
			s = Colors.removeFormattingAndColors(s);

			if(s.contains("www.") || s.contains("http://") || s.contains("https://"))
			{
				if(Core.bot.getConfig().isEnabled("allowBlacklistWebsites") && isWebsiteBlacklisted(s))
				{
					Logging.info("Website blacklisted: " + s);
					continue;
				}

				if(!tweet && Core.bot.getConfig().isEnabled("showTweets") && s.contains("twitter.com"))
					ShowTweet.show(channel, s, user, 0);
				else if(Core.bot.getConfig().isEnabled("showGitHubCommitInfo") && (s.contains("git.io") || (s.contains("github.com") && s.contains("commit"))))
					GitHub.showCommit(channel, s);
				else if(Core.bot.getConfig().isEnabled("showGitHubIssueInfo") && s.contains("github.com") && (s.contains("issues") || s.contains("pull")))
					GitHub.showIssue(channel, s);
				else if(Core.bot.getConfig().isEnabled("showGitHubRepoInfo") && s.contains("github.com"))
					GitHub.showRepo(channel, s);
				else if(Core.bot.getConfig().isEnabled("showYouTubeStats") && (s.contains("www.youtube.com/watch") || s.contains("youtu.be/")))
					YouTubeStats.sendVideoStats(s, channel);
				else if(Core.bot.getConfig().isEnabled("kickOnBannedImgurLink") && channel.equals("#bl4ckscor3") && (s.contains("imgur.com") && !s.contains("i.imgur.com")))
				{//code courtesy of Vauff
					int images = 0;

					for(String o : Jsoup.connect(s).get().select("div[class=post-images]").html().split(" "))
					{
						if(o.equals("class=\"post-image\">"))
						{
							images++;

							if(images == 2)
								break;
						}
					}

					if(images == 1)
						Core.bot.kick(channel, user, "Only use i.imgur.com links.");
				}
				else if(Core.bot.getConfig().isEnabled("showRedditInfo") && s.contains("reddit.com"))
					Reddit.showInfo(channel, s);
				else if(Core.bot.getConfig().isEnabled("showLinkTitles") && (tweet ^ s.contains("twitter.com")))
				{
					WebDriver driver = new HtmlUnitDriver();
					String title = "";

					if(s.startsWith("www."))
						s = "http://" + s;

					driver.get(s);
					title = driver.getTitle();
					driver.quit();
					
					if(s.startsWith("http://"))
						s = s.substring(7);
					else if(s.startsWith("https://"))
						s = s.substring(8);

					if(s.length() > 21)
					{
						s = s.substring(0, 21);
						s += "...";
					}

					if(title == null || title == "null" || title == "")
						Utilities.sendMessage(channel, L10N.getString("linkTitle.notFound", channel).replace("#link", s));
					else
						Utilities.sendMessage(channel, L10N.getString("linkTitle.available", channel).replace("#link", s).replace("#title", title));
				}
			}
		}
	}

	private static boolean isWebsiteBlacklisted(String website) throws MalformedURLException, IOException
	{
		for(String s : Lists.getBlacklistedWebsites())
		{
			if(website.contains(s))
				return true;
		}

		return false;
	}
}
