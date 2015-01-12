package bl4ckscor3.bot.bl4ckb0t.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class L10N
{
	private static String language = "en";
	private static String country = "US";
	private static Locale currentLocale = new Locale(language, country);
	public static ResourceBundle strings = ResourceBundle.getBundle("bl4ckscor3.bot.bl4ckb0t.localization.bb", currentLocale);

	/**
	 * Changes the localization of the bot
	 * @param l - The language code
	 * @param c - The country code
	 */
	public static void changeLocalization(String l, String c)
	{
		language = l;
		country = c;
		update();
	}
	
	/**
	 * Updates which language is used
	 */
	public static void update()
	{
		currentLocale = new Locale(language, country);
		strings = ResourceBundle.getBundle("bl4ckscor3.bot.bl4ckb0t.localization.bb", currentLocale);
	}
}
