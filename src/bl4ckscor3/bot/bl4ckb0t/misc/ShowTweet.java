package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ShowTweet
{
	public static void show(MessageEvent<Bot> event, String link) throws IOException
	{
		String name = "";
		String account = "";
		String tweet = "";

		if(link.startsWith("www."))
			link = "http://" + link;

		if(link.split("/").length < 6) //it's not a tweet
			return;

		Document doc = Jsoup.connect(link).get();
		
		name = doc.select("a.account-group:nth-child(2) > strong:nth-child(2)").get(0).toString().split(">")[1].split("<")[0];
		
		if(name.endsWith("Verified Account"))
		{
			name = name.substring(0, name.lastIndexOf("Verified Account"));
			name = name.substring(0, name.length() - 1);
		}
		
		account = "@" + doc.select("a.account-group:nth-child(2) > span:nth-child(4) > b:nth-child(2)").get(0).toString().replace("<b>", "").replace("</b>", "");
		tweet = doc.select(".TweetTextSize--28px").get(0).text();
		Utilities.chanMsg(event, Colors.BOLD + name + " (" + account + ") - " + Colors.BOLD + tweet);
	}
}
