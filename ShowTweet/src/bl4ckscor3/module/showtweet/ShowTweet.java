package bl4ckscor3.module.showtweet;

import java.net.URLClassLoader;
import java.util.List;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.modules.LinkManager;
import bl4ckscor3.bot.bl4ckb0t.util.LinkAction;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ShowTweet extends Module implements LinkAction
{
	private L10N l10n;

	public ShowTweet(String name)
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
		show(channel, user, link, 0);
	}

	/**
	 * Shows a Tweet
	 * @param channel The channel to show the Tweet in
	 * @param link The link to the Tweet
	 * @param depth Recursive depth
	 */
	private void show(String channel, String user, String link, int depth) throws Exception
	{
		String name = "";
		String account = "";
		String tweetText = "";
		boolean verified = false;

		if(link.startsWith("www."))
			link = "http://" + link;

		if(link.split("/").length < 6) //it's not a tweet
			return;

		Document doc = null;

		try
		{
			doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.183 Safari/537.36 Vivaldi/1.96.1147.42").get();
		}
		catch(HttpStatusException e)
		{
			if(e.getStatusCode() == 404)
			{
				Utilities.sendMessage(channel, l10n.translate("404", channel));
				return;
			}
			else
			{
				Utilities.sendMessage(channel, l10n.translate("error", channel));
				Logging.stackTrace(e);
				return;
			}
		}

		try
		{
			name = doc.select(".permalink-header > a:nth-child(1) > span:nth-child(2) > strong:nth-child(1)").get(0).ownText();

			if(doc.select(".permalink-header > a:nth-child(1) > span:nth-child(2) > span:nth-child(3) > span:nth-child(1)").size() != 0)
				verified = true;
		}
		catch(IndexOutOfBoundsException e1)
		{
			try
			{
				doc.select("div.ProtectedTimeline").get(0);
				Utilities.sendMessage(channel, l10n.translate("protectedTweets", channel));
			}
			catch(IndexOutOfBoundsException e2)
			{
				Logging.stackTrace(e2);
				Utilities.sendMessage(channel, "Error. See log for details.");
			}

			return;
		}

		Elements replyingTo = doc.select(".permalink-tweet > .ReplyingToContextBelowAuthor");

		account = doc.select(".permalink-header > a:nth-child(1) > span:nth-child(3)").text();
		Element tweetElement = doc.select(".tweet").get(0);
		Element textElement = tweetElement.select(".TweetTextSize--jumbo").get(0);

		for(Element img : textElement.getElementsByClass("Emoji"))
		{
			img.appendText(img.attr("alt"));
		}

		tweetText = textElement.text().replace("https://twitter.com", " https://twitter.com").replace("pic.twitter", " pic.twitter").replace(" ", "").trim();

		//adding people this tweet replies to, if it does
		if(replyingTo.size() != 0)
		{
			for(Element e : replyingTo.select(".js-user-profile-link"))
			{
				tweetText = e.text() + " " + tweetText;
			}
		}

		String msg = Colors.BOLD + name + " (" + account + ") " + (verified ? "\u2713 " : "") + "- " + Colors.BOLD + tweetText;

		//adding nested tweets prefixes
		for(int i = depth; i > 0; i--)
		{
			if(i == depth)
				msg = " > " + msg;
			else
				msg = " " + msg;
		}

		List<Element> vote = tweetElement.select(".card2");
		boolean hasVote = false;

		for(Element e : vote)
		{
			if(e.hasAttr("data-card2-name") && e.attr("data-card2-name").matches("poll[0-9]+choice_text_only"))
				hasVote = true;
		}

		Utilities.sendMessage(channel, msg.replace("…", "") + (hasVote ? Colors.ITALICS + " (" + l10n.translate("vote", channel) + ")" : ""));

		String[] split = tweetText.split(" ");

		try
		{
			for(int i = 0; i < split.length; i++)
			{
				if(split[i].contains("twitter.com"))
				{
					show(channel, user, split[i], depth + 1);
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){} //happens when the Tweet doesn't actually have any quoted Tweet in it

		String links = "";

		//extracting all links from the tweet
		for(Element e : doc.select(".TweetTextSize--jumbo").get(0).select(".twitter-timeline-link"))
		{
			if(!e.attr("data-expanded-url").contains("twitter.com")) //ignore nested tweets
				links += e.attr("data-expanded-url") + " ";
		}

		LinkManager.handleLink(links.trim(), channel, user);
	}

	@Override
	public boolean isValid(String channel, String link)
	{
		return link.contains("twitter.com");
	}

	@Override
	public int getPriority()
	{
		return 100;
	}
}