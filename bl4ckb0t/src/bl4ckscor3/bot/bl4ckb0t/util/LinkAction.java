package bl4ckscor3.bot.bl4ckb0t.util;

import java.util.Comparator;

public interface LinkAction
{
	/**
	 * Handles the link sent link
	 * @param The channel the link got sent in
	 * @param The user who sent the link
	 * @param The link itself
	 */
	public void handle(String channel, String user, String link) throws Exception;
	
	/**
	 * Checks wether or not the given link is valid for this LinkAction
	 * @param channel The channel the link got sent in
	 * @param link The link to check
	 * @return true if the link can be executed by this LinkAction, false otherwise
	 */
	public boolean isValid(String channel, String link);
	
	/**
	 * The priority with which the link should get handled. The higher the integer, the higher the priority of the link handling
	 * @return The priority
	 */
	public int getPriority();
	
	/**
	 * Models a Comparator that compares the LinkAction by their priority
	 */
	public static class LinkActionComparator implements Comparator<LinkAction>
	{
		@Override
		public int compare(LinkAction la1, LinkAction la2)
		{
			if(la1.getPriority() > la2.getPriority())
				return -1;
			else if(la1.getPriority() < la2.getPriority())
				return 1;
			else
				return 0;
		}
	}
}
