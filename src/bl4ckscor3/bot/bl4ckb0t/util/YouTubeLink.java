package bl4ckscor3.bot.bl4ckb0t.util;

public class YouTubeLink
{
	private String link;
	
	/**
	 * Saves information while a YouTube link is being analyzed
	 * @param l The link
	 * @param s Wether it's a short link or not (youtu.be)
	 */
	public YouTubeLink(String l, boolean s)
	{
		link = s ? "www.youtube.com/watch?v=" + l.split("/")[3] : l;
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
}
