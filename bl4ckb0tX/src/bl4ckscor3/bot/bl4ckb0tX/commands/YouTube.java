package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class YouTube implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
			Utilities.respond(event, "http://www.youtube.com/" + args[1], false);
		else
			Utilities.respond(event, "please provide a channel name for me and nothing else. Example: -yt antvenom", true);
	}

	@Override
	public String getAlias()
	{
		return "yt";
	}

	public static void sendVideoStats(MessageEvent event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		int lines = 0;
		String link = null;
		BufferedReader reader;
		String line;
		boolean shortLink = false;
		String[] src = new String[2000];
		String[] results =
			{
				"", //title
				"", //views
				"", //dislikes
				"", //likes
				"", //upload date
				""  //uploader
			};

		for(String s : args)
		{
			if(s.contains("www.youtube.com/watch?v="))
			{
				link = s;
				break;
			}
			else if(s.contains("http://youtu.be/"))
			{
				link = s;
				shortLink = true;
				break;
			}
		}

		if(shortLink)
		{
			String videoId = link.split("/")[3];

			link = "www.youtube.com/watch?v=" + videoId;
		}

		//if someone posts the link without a space between the link and the word before it
		if(!link.startsWith("w"))
			link = link.split(":")[1].substring(2);

		//check that the link is really the link needed (main use is when someone posts a word directly after the link without a space inbetween)
		if(link.length() != 35)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(link);
			builder.delete(35, link.length());
			link = builder.toString();
		}

		reader = new BufferedReader(new InputStreamReader(new URL("http://" + link).openStream()));

		while((line = reader.readLine()) != null)
		{
			src[lines] = line;
			lines++;
		}

		for(int i = 0; i < src.length; i++)
		{
			if(src[i] != null)
			{
				if(src[i].contains("og:title"))
					results[0] = src[i].split("\"")[3].split("\"")[0];
				else if(src[i].contains("watch-view-count"))
					results[1] = src[i].split(">")[4].split("<")[0].split(" ")[0];
				else if(src[i].contains("dislikes-count"))
					results[2] = src[i].split(">")[1].split("<")[0];
				else if(src[i].contains("likes-count"))
					results[3] = src[i].split(">")[1].split("<")[0];
				else if(src[i].contains("watch-uploader-info"))
					results[4] = src[i + 1].split(">")[1].split("<")[0].split(" ")[2] + " " + src[i + 1].split(">")[1].split("<")[0].split(" ")[3] + " " + src[i + 1].split(">")[1].split("<")[0].split(" ")[4];
				else if(src[i].contains("http://www.youtube.com/user/"))
					results[5] = src[i].split("\"")[3].split("/")[4];
			}
		}

		Utilities.chanMsg(event, "** 1,0You0,4Tube ** Title: " + results[0] + " ** Views: " + results[1] + " ** Likes:3 " + results[3] + " ** Dislikes:4 " + results[2] + " ** Uploaded on: " + results[4] + " ** Uploaded by: " + results[5] + " **");
	}
}

