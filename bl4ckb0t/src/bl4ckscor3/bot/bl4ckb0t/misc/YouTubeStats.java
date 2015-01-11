package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class YouTubeStats
{
	public static void sendVideoStats(MessageEvent event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] links = new String[10];
		boolean[] shortLink = new boolean[10];
		WebDriver driver = new HtmlUnitDriver();
		String title = "No value";
		String duration = "No value";
		String views = "No value";
		String likes = "No value";
		String dislikes = "No value";
		String date = "No value";
		String uploader = "No value";
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
					Utilities.chanMsg(event, "Couldn't find video id!");
					break;
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
			{
				String videoId = links[currentLink].split("/")[3];

				links[currentLink] = "www.youtube.com/watch?v=" + videoId;
			}

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

			driver.get(links[currentLink]);

			try
			{
				title = driver.findElement(By.xpath("//meta[@itemprop='name']")).getAttribute("content");
				System.out.println("title = " + title);
			}
			catch(NoSuchElementException e){}

			try
			{
				duration = resolveDuration(driver);
				System.out.println("duration = " + duration);
			}
			catch(NoSuchElementException e){}

			try
			{
				views = driver.findElement(By.xpath("//div[@class='watch-view-count']")).getText();
				System.out.println("views = " + views);
			}
			catch(NoSuchElementException e)
			{
				views = driver.findElement(By.xpath("//span[@class='watch-view-count yt-uix-hovercard-target']")).getText().split("Views")[0];
				System.out.println("views = " + views);
			}

			try
			{
				likes = driver.findElement(By.xpath("//button[@id='watch-like']/span[@class='yt-uix-button-content']")).getText();
				System.out.println("likes = " + likes);
			}
			catch(NoSuchElementException e){}

			try
			{
				dislikes = driver.findElement(By.xpath("//button[@id='watch-dislike']/span[@class='yt-uix-button-content']")).getText();
				System.out.println("dislikes = " + dislikes);
			}
			catch(NoSuchElementException e){}

			try
			{
				date = driver.findElement(By.xpath("//div[@id='watch-uploader-info']/strong")).getText().split("on")[1];
				System.out.println("date = " + date);
			}
			catch(NoSuchElementException e){}

			try
			{
				uploader = driver.findElement(By.xpath("//div[@class='yt-user-info']/a")).getText();
				System.out.println("uploader = " + uploader);
			}
			catch(NoSuchElementException e){}

			Utilities.chanMsg(event, Colors.BLACK + Colors.BOLD + "** " + Colors.BOLD + "1,0You0,4Tube " + Colors.BOLD + "** Title: " + Colors.BOLD + title + Colors.BOLD + " ** Duration: " + Colors.BOLD + duration + Colors.BOLD + " ** Views: " + Colors.BOLD + views + Colors.BOLD + " ** Likes:3 " + Colors.BOLD + likes + Colors.BOLD + " ** Dislikes:4 " + Colors.BOLD + dislikes + Colors.BOLD + " ** Uploaded by: " + Colors.BOLD + uploader + Colors.BOLD + " ** Published on:" + Colors.BOLD + date + Colors.BOLD + " **");
			currentLink++;
		}
		
		driver.close();
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
