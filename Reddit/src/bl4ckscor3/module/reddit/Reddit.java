package bl4ckscor3.module.reddit;

import java.io.IOException;
import java.net.URLClassLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.modules.LinkManager;
import bl4ckscor3.bot.bl4ckb0t.util.LinkAction;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Reddit extends Module implements LinkAction
{
	private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0";
	private L10N l10n;
	
	public Reddit(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		LinkManager.registerLinkAction(this);
		l10n = new L10N(this, loader);
	}
	
	@Override
	public void onDisable()
	{
		LinkManager.removeLinkAction(this);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	@Override
	public void handle(String channel, String user, String link) throws Exception
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
	private void user(String channel, String link) throws IOException
	{
		Document doc = Jsoup.connect(link).userAgent(userAgent).get();

		Utilities.sendStarMsg(channel, "/u/" + link.split("/")[4],
				l10n.translate("postKarma", channel).replace("#", doc.select(".karma").get(0).text()),
				l10n.translate("commentKarma", channel).replace("#", doc.select(".comment-karma").text()),
				l10n.translate("dateCreated", channel).replace("#", doc.select(".age > time").attr("title")));
	}

	/**
	 * Shows information about the subreddit
	 * @param channel The channel to send the information in
	 * @param link The link to the subreddit
	 */
	private void subreddit(String channel, String link) throws IOException
	{
		Document doc = Jsoup.connect(link).userAgent(userAgent).get();

		Utilities.sendStarMsg(channel, "/r/" + doc.select(".redditname > a").text().split(" ")[0],
				l10n.translate("subscribers", channel).replace("#", doc.select(".subscribers > .number").text()),
				l10n.translate("viewers", channel).replace("#", doc.select(".users-online > .number").text()));
	}

	/**
	 * Shows information about a selfpost, or linked post
	 * @param channel The channel to send the information in
	 * @param link The link to the post
	 */
	private void post(String channel, String link) throws IOException
	{
		Document doc = Jsoup.connect(link).userAgent(userAgent).get();
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
	private void comment(String channel, String link) throws IOException
	{
		Document doc = Jsoup.connect(link).userAgent(userAgent).get();
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

	@Override
	public boolean isValid(String channel, String link)
	{
		return link.contains("reddit.com");
	}

	@Override
	public int getPriority()
	{
		return 40;
	}
}