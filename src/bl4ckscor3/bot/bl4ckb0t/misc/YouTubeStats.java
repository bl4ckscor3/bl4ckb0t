package bl4ckscor3.bot.bl4ckb0t.misc;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class YouTubeStats
{
	public static void sendVideoStats(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] links = new String[10];
		boolean[] shortLink = new boolean[10];
		WebDriver driver = new HtmlUnitDriver();
		String title = L10N.getString("youtube.noValue");
		String duration = L10N.getString("youtube.noValue");
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

			driver.get(links[currentLink]);

			try
			{
				title = driver.findElement(By.cssSelector("#watch7-content > meta:nth-child(2)")).getAttribute("content");
			}
			catch(Exception e)
			{
				if(e instanceof NoSuchElementException)
					Logging.warn("Couldn't find \"title\" element.");
				else
				{
					Logging.warn("Error when searching for \"title\" element:");
					e.printStackTrace();
				}
			}

			duration = resolveDuration(driver);

			try
			{
				views = driver.findElement(By.cssSelector(".watch-view-count")).getText();
			}
			catch(Exception e)
			{
				if(e instanceof NoSuchElementException)
					Logging.warn("Couldn't find \"views\" element.");
				else
				{
					Logging.warn("Error when searching for \"views\" element:");
					e.printStackTrace();
				}
			}

			try
			{
				likes = driver.findElement(By.cssSelector(".like-button-renderer-like-button-unclicked > span:nth-child(1)")).getText();
			}
			catch(Exception e)
			{
				if(e instanceof NoSuchElementException)
					Logging.warn("Couldn't find \"likes\" element.");
				else
				{
					Logging.warn("Error when searching for \"likes\" element:");
					e.printStackTrace();
				}
			}

			try
			{
				dislikes = driver.findElement(By.cssSelector(".like-button-renderer-dislike-button-unclicked > span:nth-child(1)")).getText();
			}
			catch(Exception e)
			{
				if(e instanceof NoSuchElementException)
					Logging.warn("Couldn't find \"dislikes\" element.");
				else
				{
					Logging.warn("Error when searching for \"dislikes\" element:");
					e.printStackTrace();
				}
			}

			try
			{
				date = driver.findElement(By.cssSelector(".watch-time-text")).getText().split("am")[1].trim();
			}
			catch(Exception e)
			{
				if(e instanceof NoSuchElementException)
					Logging.warn("Couldn't find \"date\" element.");
				else
				{
					Logging.warn("Error when searching for \"date\" element:");
					e.printStackTrace();
				}
			}

			try
			{
				uploader = driver.findElement(By.cssSelector(".yt-user-info > a:nth-child(1)")).getText();
			}
			catch(Exception e)
			{
				if(e instanceof NoSuchElementException)
					Logging.warn("Couldn't find \"uploader\" element.");
				else
				{
					Logging.warn("Error when searching for \"uploader\" element:");
					e.printStackTrace();
				}
			}

			Utilities.chanMsg(event, Colors.BLACK + Colors.BOLD + "** " + Colors.BOLD + "1,0You0,4Tube " + 
					Colors.BOLD + "** " + L10N.getString("youtube.title") + ": " + Colors.BOLD + title + 
					Colors.BOLD + " ** " + L10N.getString("youtube.duration") + ": " + Colors.BOLD + duration + 
					Colors.BOLD + " ** " + L10N.getString("youtube.views") + ": " + Colors.BOLD + views + 
					Colors.BOLD + " ** " + L10N.getString("youtube.likes") + ":3 " + Colors.BOLD + likes + 
					Colors.BOLD + " ** " + L10N.getString("youtube.dislikes") + ":4 " + Colors.BOLD + dislikes + 
					Colors.BOLD + " ** " + L10N.getString("youtube.uploader") + ": " + Colors.BOLD + uploader + 
					Colors.BOLD + " ** " + L10N.getString("youtube.date") + ": " + Colors.BOLD + date + Colors.BOLD + " **");
			currentLink++;
		}

		driver.close();
	}

	private static String resolveDuration(WebDriver driver)
	{
		String dur = L10N.getString("youtube.noValue");
		String hours;
		String minutes = "";
		String seconds = null;
		int h = 0;
		int m;

		try
		{
			dur = driver.findElement(By.cssSelector("#watch7-content > meta:nth-child(7)")).getAttribute("content");
		}
		catch(Exception e)
		{
			if(e instanceof NoSuchElementException)
			{
				Logging.warn("Couldn't find \"duration\" element.");
				return dur;
			}
			else
			{
				Logging.warn("Error when searching for \"duration\" element:");
				e.printStackTrace();
			}
		}

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
