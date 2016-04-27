package bl4ckscor3.bot.bl4ckb0t.util;

import java.util.ArrayList;

/**
 * Simple wrapper class to make adding values much easier and more compact
 */
public class CustomArrayList<T> extends ArrayList<T>
{
	private static final long serialVersionUID = -1826258082326460410L;

	/**
	 * Adds all listed objects to the list
	 * @param ts The objects to add
	 */
	@SuppressWarnings("unchecked")
	public void addEverything(T... ts)
	{
		for(T t : ts)
		{
			add(t);
		}
	}
}
