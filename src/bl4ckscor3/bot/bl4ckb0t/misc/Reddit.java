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
		else
		{
			String noPrefixLink = link.replace("http://", "").replace("https://", "").replace("www.", "");

			if(!noPrefixLink.startsWith("reddit.com")) //we have a link of the format subreddit.reddit.com
			{
				link = "https://www.reddit.com/r/" + noPrefixLink.split("\\.reddit")[0];

				if(noPrefixLink.split("\\.com").length > 1)
					link += noPrefixLink.split("\\.com")[1];
			}

			if(link.contains("/r/"))
			{
				if(link.contains("/comments/"))
				{
					if(link.endsWith("/")) //it's not a comment
						post(channel, link);
					else
						comment(channel, link);
				}
				else
					subreddit(channel, link);
			}
		}
	}

	/**
	 * Shows information about the reddit user
	 * @param channel The channel to send the information in
	 * @param link The link to the user's profile
	 */
	private static void user(String channel, String link) throws IOException
	{
		Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:48.0) Gecko/20100101 Firefox/48.0").get();

		Utilities.sendStarMsg(channel, "/u/" + link.split("/")[4],
				L10N.getString("reddit.postKarma", channel).replace("#", Colors.NORMAL + doc.select(".karma").get(0).text()),
				L10N.getString("reddit.commentKarma", channel).replace("#", Colors.NORMAL + doc.select(".comment-karma").text()),
				L10N.getString("reddit.dateCreated", channel).replace("#", Colors.NORMAL + doc.select(".age > time").attr("title")));
	}

	/**
	 * Shows information about the subreddit
	 * @param channel The channel to send the information in
	 * @param link The link to the subreddit
	 */
	private static void subreddit(String channel, String link) throws IOException
	{
		Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:48.0) Gecko/20100101 Firefox/48.0").get();

		Utilities.sendStarMsg(channel, "/r/" + doc.select(".redditname > a").text().split(" ")[0],
				L10N.getString("reddit.subscribers", channel).replace("#", Colors.NORMAL + doc.select(".subscribers > .number").text()),
				L10N.getString("reddit.viewers", channel).replace("#", Colors.NORMAL + doc.select(".users-online > .number").text()));
	}

	/**
	 * Shows information about a selfpost, or linked post
	 * @param channel The channel to send the information in
	 * @param link The link to the post
	 */
	private static void post(String channel, String link) throws IOException
	{
		Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:48.0) Gecko/20100101 Firefox/48.0").get();
		String gilded = doc.select(".gilded-icon").attr("data-count");
		String votes = doc.select("div.score > span.number").text();
		String percentage = doc.select(".score").get(0).text().split("\\(")[1].split("%")[0] + "%";
		String title = doc.select("a.title").get(0).text();
		String type = doc.select(".domain").get(0).text().contains("self.") ? "(Selfpost)" : doc.select(".domain").get(0).text();
		String comments = doc.select(".comments[href=\"" + link +"\"]").text();
		String author = doc.select("p.tagline > a.author").get(0).text();
		String time = doc.select("p.tagline > time").get(0).text();

		if(gilded == "")
			gilded = "0";

		Utilities.sendMessage(channel, Colors.BROWN + "(" + gilded + ") " + Colors.GREEN + "[" + votes + " " + percentage + "] " + Colors.NORMAL + title + " " + Colors.ITALICS + Colors.LIGHT_GRAY  + type  + Colors.NORMAL + " - " + comments + " - " + Colors.MAGENTA + "/u/" + author + " posted " + time);
	}

	/**
	 * Shows information about a comment
	 * @param channel The channel to send the information in
	 * @param link The link to the comment
	 */
	private static void comment(String channel, String link) throws IOException
	{
		Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:48.0) Gecko/20100101 Firefox/48.0").get();
		String gilded;
		String author = doc.select("#thing_t1_" + link.substring(link.lastIndexOf('/') + 1) +" > div.entry > p.tagline > a.author").text();
		String votes = doc.select("#thing_t1_" + link.substring(link.lastIndexOf('/') + 1) +" > div.entry > p.tagline > span.unvoted").text().split(" ")[0];
		String time = doc.select("#thing_t1_" + link.substring(link.lastIndexOf('/') + 1) +" > div.entry > p.tagline > time").text();

		try
		{
			gilded = doc.select(".gilded-icon").get(1).attr("data-count"); //for if the op has a gild
		}
		catch(IndexOutOfBoundsException e)
		{
			gilded = doc.select(".gilded-icon").attr("data-count"); //for it the op has no gild
		}

		if(gilded == "")
			gilded = "0";

		Utilities.sendMessage(channel,  Colors.BROWN + "(" + gilded + ") " + Colors.GREEN + "[" + votes + "] " + Colors.MAGENTA + "/u/" + author + " posted " + time);
	}
}
