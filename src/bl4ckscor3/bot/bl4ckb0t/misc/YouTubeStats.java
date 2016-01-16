package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.YouTubeLink;

public class YouTubeStats
{
	public static void sendVideoStats(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		ArrayList<YouTubeLink> links = new ArrayList<YouTubeLink>();
		String title = L10N.getString("youtube.noValue", event);
		String views = L10N.getString("youtube.noValue", event);
		String likes = L10N.getString("youtube.noValue", event);
		String dislikes = L10N.getString("youtube.noValue", event);
		String date = L10N.getString("youtube.noValue", event);
		String uploader = L10N.getString("youtube.noValue", event);

		for(String s : args)
		{
			if(s.contains("www.youtube.com/watch"))
			{
				if(s.contains("v="))
					links.add(new YouTubeLink("http://www.youtube.com/watch?v=" + s.split("v=")[1].substring(0, 11) + "/", false));
				else
				{
					Utilities.chanMsg(event, L10N.getString("youtube.noId", event));
					continue;
				}
			}
			else if(s.contains("youtu.be/"))
				links.add(new YouTubeLink(s, true));
		}

		for(int currentLink = 0; currentLink < links.size(); currentLink++)
		{
			YouTubeLink link = links.get(currentLink);
			
			if(currentLink != 0)
				Utilities.chanMsg(event, "------------------------------------------");

			if(link.isShortLink())
				link.setLink("www.youtube.com/watch?v=" + link.getLink().split("/")[3]);

			//if someone posts the link without a space between the link and the word before it
			if(!link.getLink().startsWith("w"))
				link.setLink(link.getLink().split(":")[1].substring(2));

			//check that the link is really the link needed (main use is when someone posts a word directly after the link without a space inbetween)
			if(link.getLink().length() != 35)
			{
				StringBuilder builder = new StringBuilder();
				
				builder.append(link.getLink());
				builder.delete(35, link.getLink().length());
				link.setLink(builder.toString());
			}

			//make sure that the links starts with "http://"
			if(!link.getLink().startsWith("http://"))
				link.setLink("http://" + link.getLink());

			Document doc = Jsoup.connect(link.getLink()).get();

			title = doc.select("#eow-title").get(0).text();
			views = doc.select(".watch-view-count").get(0).text().split(" ")[0];
			likes = doc.select(".like-button-renderer-like-button-unclicked > span:nth-child(1)").get(0).text();
			dislikes = doc.select(".like-button-renderer-dislike-button-unclicked > span:nth-child(1)").get(0).text();
			date = doc.select(".watch-time-text").get(0).text().split(" ")[2].trim();
			uploader = doc.select(".yt-user-info > a:nth-child(1)").get(0).text();
			Utilities.chanMsg(event, Colors.BOLD + "** " + Colors.BOLD + "1,0You0,4Tube " + 
					Colors.BOLD + "** " + L10N.getString("youtube.title", event) + ": " + Colors.BOLD + title + 
					Colors.BOLD + " ** " + L10N.getString("youtube.views", event) + ": " + Colors.BOLD + views + 
					Colors.BOLD + " ** " + L10N.getString("youtube.likes", event) + ":3 " + Colors.BOLD + likes + 
					Colors.BOLD + " ** " + L10N.getString("youtube.dislikes", event) + ":4 " + Colors.BOLD + dislikes + 
					Colors.BOLD + " ** " + L10N.getString("youtube.uploader", event) + ": " + Colors.BOLD + uploader + 
					Colors.BOLD + " ** " + L10N.getString("youtube.date", event) + ": " + Colors.BOLD + date + Colors.BOLD + " **");
		}
		
		links.clear();
	}
}
