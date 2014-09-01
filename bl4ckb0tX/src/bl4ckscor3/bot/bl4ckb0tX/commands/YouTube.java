package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
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
		String link = null;
		boolean shortLink = false;
		WebDriver driver = new HtmlUnitDriver();
		String title = "No value";
		String duration = "No value";
		String views = "No value";
		String likes = "No value";
		String dislikes = "No value";
		String date = "No value";
		String uploader = "No value";

		for(String s : args)
		{
			if(s.contains("www.youtube.com/watch"))
			{
				if(s.contains("v="))
					link = "http://www.youtube.com/watch?v=" + s.split("v=")[1].substring(0, 11) + "/";
				else
				{
					Utilities.chanMsg(event, "Couldn't find video id!");
					break;
				}
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

		//make sure that the links starts with "http://"
		if(!link.startsWith("http://"))
			link = "http://" + link;

		driver.get(link);

		try
		{
			title = driver.findElement(By.xpath("//meta[@itemprop='name']")).getAttribute("content");
		}
		catch(NoSuchElementException e){}

		try
		{
			duration = resolveDuration(driver);
		}
		catch(NoSuchElementException e){}

		try
		{
			views = driver.findElement(By.xpath("//div[@class='watch-view-count']")).getText();
		}
		catch(NoSuchElementException e)
		{
			views = driver.findElement(By.xpath("//span[@class='watch-view-count yt-uix-hovercard-target']")).getText().split("Views")[0];
		}

		try
		{
			likes = driver.findElement(By.xpath("//button[@id='watch-like']/span[@class='yt-uix-button-content']")).getText();
		}
		catch(NoSuchElementException e){}

		try
		{
			dislikes = driver.findElement(By.xpath("//button[@id='watch-dislike']/span[@class='yt-uix-button-content']")).getText();
		}
		catch(NoSuchElementException e){}

		try
		{
			date = driver.findElement(By.xpath("//p[@id='watch-uploader-info']/strong")).getText().split("on")[1];
		}
		catch(NoSuchElementException e){}

		try
		{
			uploader = driver.findElement(By.xpath("//div[@class='yt-user-info']/a")).getText();
		}
		catch(NoSuchElementException e){}

		driver.close();
		Utilities.chanMsg(event, Colors.BLACK + Colors.BOLD + "** " + Colors.BOLD + "1,0You0,4Tube " + Colors.BOLD + "** Title: " + Colors.BOLD + title + Colors.BOLD + " ** Duration: " + Colors.BOLD + duration + Colors.BOLD + " ** Views: " + Colors.BOLD + views + Colors.BOLD + " ** Likes:3 " + Colors.BOLD + likes + Colors.BOLD + " ** Dislikes:4 " + Colors.BOLD + dislikes + Colors.BOLD + " ** Uploaded by: " + Colors.BOLD + uploader + Colors.BOLD + " ** Uploaded on:" + Colors.BOLD + date + Colors.BOLD + " **");
	}

	private static String resolveDuration(WebDriver driver)
	{
		String dur = "No Value";
		String hours;
		String minutes = "";
		String seconds = null;
		int h = 0;
		int m;

		dur = driver.findElement(By.xpath("//meta[@itemprop='duration']")).getAttribute("content");

		if(dur.contains("M"))
			minutes = dur.split("T")[1].split("M")[0]; 

		m = Integer.parseInt(minutes);

		if(m > 60)
		{
			h = m / 60;
			m %= 60;
		}

		if(dur.contains("S"))
			seconds = dur.split("T")[1].split("M")[1].split("S")[0];

		hours = "" + h;

		if(m < 10)
			minutes = "0" + m;
		else
			minutes = "" + m;

		if(Integer.parseInt(seconds) < 10)
			seconds = "0" + seconds;

		dur = hours + ":" + minutes + ":" + seconds;
		return dur;
	}
}