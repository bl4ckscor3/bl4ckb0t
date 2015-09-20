package bl4ckscor3.bot.bl4ckb0t.util;

public class YouTubeLink
{
	private String link;
	private boolean shortLink;
	
	public YouTubeLink(String l, boolean s)
	{
		link = l;
		shortLink = s;
	}

	public void setLink(String l)
	{
		link = l;
	}
	
	public String getLink()
	{
		return link;
	}
	
	public boolean isShortLink()
	{
		return shortLink;
	}
}
