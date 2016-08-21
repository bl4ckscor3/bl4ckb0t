package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Reddit
{
	/**
	 * Shows information about the sent reddit link
	 * @param channel The channel to show the info in
	 * @param link The reddit link
	 * @throws IOException 
	 */
	public static void showInfo(String channel, String link) throws IOException
	{
		if(link.contains("/user/") || link.contains("/u/"))
			user(channel, link.replace("/u/", "/user/"));
//		else
//		{
//			String noPrefixLink = link.replace("http://", "").replace("https://", "").replace("www.", "");
//
//			if(!noPrefixLink.startsWith("reddit.com")) //we have a link of the format subreddit.reddit.com
//				link = "https://www.reddit.com/r/" + noPrefixLink.split("\\.reddit")[0] + noPrefixLink.split("\\.com")[1];
//			
//			if(link.contains("/r/"))
//				subreddit(channel, link);
//		}
	}
	
	private static void user(String channel, String link) throws IOException
	{
		Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:48.0) Gecko/20100101 Firefox/48.0").get();
		
		Utilities.sendStarMsg(channel, "/u/" + link.split("/")[4],
				L10N.getString("reddit.postKarma", channel) + ": " + Colors.NORMAL + doc.select(".karma").get(0).text(),
				L10N.getString("reddit.commentKarma", channel) + ": " + Colors.NORMAL + doc.select(".comment-karma").text(),
				L10N.getString("reddit.dateCreated", channel) + ": " + Colors.NORMAL + doc.select(".age > time").attr("title"));
	}
	
//	private static void subreddit(String channel, String link) throws IOException
//	{
//		coming later
//	}
}
