package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;
import bl4ckscor3.bot.bl4ckb0t.util.YouTubeLink;

public class YouTubeStats
{
	/**
	 * Sends information about a YouTube video
	 * @param event The MessageEvent holding the YouTube link and the channel
	 */
	public static void sendVideoStats(MessageEvent event) throws MalformedURLException, IOException
	{
		String channel = event.getChannel().getName();
		String[] args = Utilities.toArgs(event.getMessage());
		ArrayList<YouTubeLink> links = new ArrayList<YouTubeLink>();
		String title = L10N.getString("youtube.noValue", channel);
		String views = L10N.getString("youtube.noValue", channel);
		String likes = L10N.getString("youtube.noValue", channel);
		String dislikes = L10N.getString("youtube.noValue", channel);
		String date = L10N.getString("youtube.noValue", channel);
		String uploader = L10N.getString("youtube.noValue", channel);

		for(String s : args)
		{
			if(s.contains("www.youtube.com/watch"))
			{
				if(s.contains("v="))
					links.add(new YouTubeLink("http://www.youtube.com/watch?v=" + s.split("v=")[1].substring(0, 11) + "/", false));
				else
				{
					Utilities.sendMessage(channel, L10N.getString("youtube.noId", channel));
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
				Utilities.sendMessage(channel, "------------------------------------------");

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
			
			try
			{
				likes = doc.select(".like-button-renderer-like-button-unclicked > span:nth-child(1)").get(0).text();
				dislikes = doc.select(".like-button-renderer-dislike-button-unclicked > span:nth-child(1)").get(0).text();
			}
			catch(IndexOutOfBoundsException e)
			{
				likes = (dislikes = L10N.getString("youtube.ratingDisabled", channel));
			}
			
			date = doc.select(".watch-time-text").get(0).text().split(" ")[2].trim();
			uploader = doc.select(".yt-user-info > a:nth-child(1)").get(0).text();

			if(!views.matches("[0-9.\\+]*")) //any amount of numbers with . and + inbetween
				views = "0";
			
			Utilities.sendStarMsg(channel,
					Colors.BOLD + Utilities.backgroundColor(Colors.WHITE, Colors.BLACK) + "You" + Utilities.backgroundColor(Colors.RED, Colors.WHITE) + "Tube" + Colors.NORMAL,
					Colors.BOLD + L10N.getString("youtube.title", channel) + ": " + Colors.NORMAL + title,
					Colors.BOLD + L10N.getString("youtube.views", channel) + ": " + Colors.NORMAL + views,
					Colors.BOLD + L10N.getString("youtube.likes", channel) + ": " + Colors.NORMAL + Colors.DARK_GREEN + likes + Colors.NORMAL,
					Colors.BOLD + L10N.getString("youtube.dislikes", channel) + ": " + Colors.NORMAL + Colors.RED + dislikes + Colors.NORMAL,
					Colors.BOLD + L10N.getString("youtube.uploader", channel) + ": " + Colors.NORMAL + uploader + Colors.NORMAL,
					Colors.BOLD + L10N.getString("youtube.date", channel) + ": " + Colors.NORMAL + date + Colors.NORMAL);
		}
	}
}
