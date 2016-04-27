package bl4ckscor3.bot.bl4ckb0t.util;

public class YouTubeLink
{
	private String link;
	private boolean shortLink;
	
	/**
	 * Saves information while a YouTube link is being analyzed
	 * @param l The link
	 * @param s Wether it's a short link or not (youtu.be)
	 */
	public YouTubeLink(String l, boolean s)
	{
		link = l;
		shortLink = s;
	}

	/**
	 * Sets the link
	 * @param l The link to set
	 */
	public void setLink(String l)
	{
		link = l;
	}
	
	/**
	 * @return The link
	 */
	public String getLink()
	{
		return link;
	}
	
	/**
	 * @return true if the link is a short link, false otherwise
	 */
	public boolean isShortLink()
	{
		return shortLink;
	}
}
