package bl4ckscor3.bot.bl4ckb0t.localization;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class L10N
{
	private static String language = "en";
	private static String country = "US";
	private static Locale currentLocale = new Locale(language, country);
	public static ResourceBundle strings = ResourceBundle.getBundle("bl4ckscor3.bot.bl4ckb0t.localization.bb", currentLocale);
	public static HashMap<String,String> chanLangs = new HashMap<String,String>();

	/**
	 * Updates which language is used
	 */
	public static void update()
	{
		currentLocale = new Locale(language, country);
		strings = ResourceBundle.getBundle("bl4ckscor3.bot.bl4ckb0t.localization.bb", currentLocale);
	}

	/**
	 * Changes the localization of a channel
	 * @param l - The language code
	 * @param c - The country code
	 */
	public static void changeLocalization(String channel, String locale, String l, String c)
	{
		language = l;
		country = c;
		setLocalization(channel, locale);
		update();
	}

	/**
	 * Saves which channel has which localization
	 * @param channel - The channel to save the localization
	 * @param locale - The localization to save
	 */
	public static void setLocalization(String channel, String locale)
	{
		if(!chanLangs.containsKey(channel))
			chanLangs.put(channel, locale);
		else
			chanLangs.replace(channel, locale);
	}

	/**
	 * Gets the localization of a channel
	 * @param channel - The channel to get the localization from
	 */
	public static String getLocalization(String channel)
	{
		return chanLangs.get(channel);
	}
}
