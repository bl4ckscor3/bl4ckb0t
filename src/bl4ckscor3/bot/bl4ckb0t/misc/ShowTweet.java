package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ShowTweet
{
	/**
	 * Shows a Tweet
	 * @param channel The channel to show the Tweet in
	 * @param link The link to the Tweet
	 * @param depth Recursive depth
	 */
	public static void show(String channel, String link, int depth) throws IOException
	{
		String name = "";
		String account = "";
		String tweet = "";

		if(link.startsWith("www."))
			link = "http://" + link;

		if(link.split("/").length < 6) //it's not a tweet
			return;

		Document doc = null;
		
		try
		{
			doc = Jsoup.connect(link).get();
		}
		catch(HttpStatusException e)
		{
			if(e.getStatusCode() == 404)
			{
				Utilities.sendMessage(channel, L10N.getString("tweet.404", channel));
				return;
			}
			else
			{
				Utilities.sendMessage(channel, L10N.getString("tweet.error", channel));
				e.printStackTrace();
				return;
			}
		}
		
		name = doc.select("a.account-group:nth-child(2) > strong:nth-child(2)").get(0).toString().split(">")[1].split("<")[0];
		
		if(name.endsWith("Verified Account"))
		{
			name = name.substring(0, name.lastIndexOf("Verified Account"));
			name = name.substring(0, name.length() - 1);
		}
		
		account = "@" + doc.select("a.account-group:nth-child(2) > span:nth-child(4) > b:nth-child(2)").get(0).toString().replace("<b>", "").replace("</b>", "");
		tweet = doc.select(".TweetTextSize--26px").get(0).text().replace("https://twitter.com", " https://twitter.com").replace("pic.twitter", " pic.twitter").trim();
		
		String msg = Colors.BOLD + name + " (" + account + ") - " + Colors.BOLD + tweet;
		
		for(int i = depth; i > 0; i--)
		{
			if(i == depth)
				msg = " > " + msg;
			else
				msg = " " + msg;
		}
		
		Utilities.sendMessage(channel, msg);
		
		for(String s : tweet.split(" "))
		{
			if(s.endsWith(" …"))
				show(channel, "https://twitter.com" + s.split("https://twitter.com")[1], ++depth);
		}
	}
}
