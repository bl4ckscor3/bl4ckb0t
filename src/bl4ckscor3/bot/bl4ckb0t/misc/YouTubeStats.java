package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class YouTubeStats
{
	/**
	 * Sends information about a YouTube video
	 * @param event The MessageEvent holding the YouTube link and the channel
	 */
	public static void sendVideoStats(String link, String channel) throws MalformedURLException, IOException
	{
		String yt = null;
		String title = L10N.getString("youtube.noValue", channel);
		String duration = L10N.getString("youtube.noValue", channel);
		String views = L10N.getString("youtube.noValue", channel);
		String likes = L10N.getString("youtube.noValue", channel);
		String dislikes = L10N.getString("youtube.noValue", channel);
		String date = L10N.getString("youtube.noValue", channel);
		String uploader = L10N.getString("youtube.noValue", channel);

		if(link.contains("www.youtube.com/watch"))
		{
			if(link.contains("v="))
				yt = "www.youtube.com/watch?v=" + link.split("v=")[1].substring(0, 11) + "/";
			else
			{
				Utilities.sendMessage(channel, L10N.getString("youtube.noId", channel));
				return;
			}
		}
		else if(link.contains("youtu.be/"))
			yt = "www.youtube.com/watch?v=" + link.split("youtu.be/")[1];

		//shouldn't happen
		if(yt == null)
		{
			Logging.warn("YouTube link is null - " + link);
			return;
		}

		//if someone posts the link without a space between the link and the word before it
		if(!yt.startsWith("w"))
			yt = yt.split(":")[1].substring(2);

		//check that the link is really the link needed (main use is when someone posts a word directly after the link without a space inbetween)
		if(yt.length() != 35)
		{
			StringBuilder builder = new StringBuilder();

			builder.append(yt);
			builder.delete(35, yt.length());
			yt = builder.toString();
		}

		//make sure that the links starts with "http://"
		if(!yt.startsWith("http://"))
			yt = "http://" + yt;

		Document doc = Jsoup.connect(yt).get();

		title = doc.select("#eow-title").get(0).text();

		outer:
		for(Element e : doc.getElementsByTag("script"))
		{
			for(DataNode n : e.dataNodes())
			{
				if(n.getWholeData().contains("length_seconds"))
				{
					for(String s : n.getWholeData().split(","))
					{
						if(s.startsWith("\"length_seconds\""))
						{
							int seconds = Integer.parseInt(s.split(":")[1].replace("\"", ""));
							int minutes = 0;
							int hours = 0;

							while(seconds >= 60)
							{
								seconds -= 60;
								minutes++;
							}
							
							while(minutes >= 60)
							{
								minutes -= 60;
								hours++;
							}
							
							duration = (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
							break outer;
						}
					}
				}
			}
		}

		views = doc.select(".watch-view-count").get(0).text();

		try
		{
			likes = doc.select(".like-button-renderer-like-button-unclicked > span:nth-child(1)").get(0).text();
			dislikes = doc.select(".like-button-renderer-dislike-button-unclicked > span:nth-child(1)").get(0).text();
		}
		catch(IndexOutOfBoundsException e)
		{
			likes = (dislikes = L10N.getString("youtube.ratingDisabled", channel));
		}

		date = doc.select(".watch-time-text").get(0).text();
		uploader = doc.select(".yt-user-info > a:nth-child(1)").get(0).text();

		date = date.replaceAll("^[^0-9]*", ""); //replace everything infront of the first number
		views = views.replaceAll("[^0-9+.]", ""); //replace everything except numbers, + and . with nothing
		Utilities.sendStarMsg(channel,
				Utilities.backgroundColor(Colors.WHITE, Colors.BLACK) + "You" + Utilities.backgroundColor(Colors.RED, Colors.WHITE) + "Tube",
				L10N.getString("youtube.title", channel) + ": " + Colors.NORMAL + title,
				L10N.getString("youtube.duration", channel) + ": " + Colors.NORMAL + duration,
				L10N.getString("youtube.views", channel) + ": " + Colors.NORMAL + views,
				L10N.getString("youtube.likes", channel) + ": " + Colors.NORMAL + Colors.DARK_GREEN + likes,
				L10N.getString("youtube.dislikes", channel) + ": " + Colors.NORMAL + Colors.RED + dislikes,
				L10N.getString("youtube.uploader", channel) + ": " + Colors.NORMAL + uploader,
				L10N.getString("youtube.date", channel) + ": " + Colors.NORMAL + date);
	}
}
