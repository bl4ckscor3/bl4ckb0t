package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class YouTubeStats
{
	public static void sendVideoStats(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] links = new String[10];
		boolean[] shortLink = new boolean[10];
		String title = L10N.getString("youtube.noValue");
		String views = L10N.getString("youtube.noValue");
		String likes = L10N.getString("youtube.noValue");
		String dislikes = L10N.getString("youtube.noValue");
		String date = L10N.getString("youtube.noValue");
		String uploader = L10N.getString("youtube.noValue");
		int linkCounter = 0;
		int currentLink = 0;

		for(String s : args)
		{
			if(s.contains("www.youtube.com/watch"))
			{
				if(s.contains("v="))
				{
					links[linkCounter] = "http://www.youtube.com/watch?v=" + s.split("v=")[1].substring(0, 11) + "/";
					linkCounter++;
				}
				else
				{
					Utilities.chanMsg(event, L10N.getString("youtube.noId"));
					continue;
				}
			}
			else if(s.contains("youtu.be/"))
			{
				links[linkCounter] = s;
				shortLink[linkCounter] = true;
				linkCounter++;
			}
		}

		while(currentLink != linkCounter)
		{
			if(currentLink != 0)
				Utilities.chanMsg(event, "------------------------------------------");

			if(shortLink[currentLink])
				links[currentLink] = "www.youtube.com/watch?v=" + links[currentLink].split("/")[3];

			//if someone posts the link without a space between the link and the word before it
			if(!links[currentLink].startsWith("w"))
				links[currentLink] = links[currentLink].split(":")[1].substring(2);

			//check that the link is really the link needed (main use is when someone posts a word directly after the link without a space inbetween)
			if(links[currentLink].length() != 35)
			{
				StringBuilder builder = new StringBuilder();
				builder.append(links[currentLink]);
				builder.delete(35, links[currentLink].length());
				links[currentLink] = builder.toString();
			}

			//make sure that the links starts with "http://"
			if(!links[currentLink].startsWith("http://"))
				links[currentLink] = "http://" + links[currentLink];

			Document doc = Jsoup.connect(links[currentLink]).get();

			title = doc.select("#eow-title").get(0).text();
			views = doc.select(".watch-view-count").get(0).text().split(" ")[0];
			likes = doc.select(".like-button-renderer-like-button-unclicked > span:nth-child(1)").get(0).text();
			dislikes = doc.select(".like-button-renderer-dislike-button-unclicked > span:nth-child(1)").get(0).text();
			date = doc.select(".watch-time-text").get(0).text().split(" ")[2].trim();
			uploader = doc.select(".yt-user-info > a:nth-child(1)").get(0).text();
			Utilities.chanMsg(event, Colors.BOLD + "** " + Colors.BOLD + "1,0You0,4Tube " + 
					Colors.BOLD + "** " + L10N.getString("youtube.title") + ": " + Colors.BOLD + title + 
					Colors.BOLD + " ** " + L10N.getString("youtube.views") + ": " + Colors.BOLD + views + 
					Colors.BOLD + " ** " + L10N.getString("youtube.likes") + ":3 " + Colors.BOLD + likes + 
					Colors.BOLD + " ** " + L10N.getString("youtube.dislikes") + ":4 " + Colors.BOLD + dislikes + 
					Colors.BOLD + " ** " + L10N.getString("youtube.uploader") + ": " + Colors.BOLD + uploader + 
					Colors.BOLD + " ** " + L10N.getString("youtube.date") + ": " + Colors.BOLD + date + Colors.BOLD + " **");
			currentLink++;
		}
	}
}
