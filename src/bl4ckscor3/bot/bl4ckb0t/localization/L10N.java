package bl4ckscor3.bot.bl4ckb0t.localization;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

@SuppressWarnings("unused") //ResourceBundles are seen as unused because they are called via reflection
public class L10N
{
	private static final ResourceBundle english = ResourceBundle.getBundle((Core.bot.isDevelopment() ? "" : "src.") + "bl4ckscor3.bot.bl4ckb0t.localization.bb", new Locale("en", "US"));
	private static final ResourceBundle german = ResourceBundle.getBundle((Core.bot.isDevelopment() ? "" : "src.") + "bl4ckscor3.bot.bl4ckb0t.localization.bb", new Locale("de", "DE"));
	public static final ArrayMap<String,String> chanLangs = new ArrayMap<String,String>();

	/**
	 * Changes the localization of the bot
	 * @param l - The language code
	 * @param c - The country code
	 * @param channel - The channel the language gets changed in
	 */
	public static void setChannelLanguage(String channel, String langName) throws IOException
	{
		chanLangs.put(channel, langName);
	}

	/**
	 * Gets the localized message for the key with the language of the channel in the event
	 * @param key The unlocalized message key
	 * @param event The event which contains the channel the message get's sent to
	 * @return The localized String
	 */
	public static String getString(String key, String channel)
	{
		try
		{
			return ((ResourceBundle)L10N.class.getDeclaredField(chanLangs.get(channel)).get(null)).getString(key);
		}
		catch(Exception e)
		{
			Logging.stackTrace(e);
			Logging.severe("Error in localizing message.");
			
			try
			{
				return ((ResourceBundle)L10N.class.getDeclaredField("english").get(null)).getString(key)
						/*Colors*/
						.replace("&0", Colors.WHITE)
						.replace("&1", Colors.BLACK)
						.replace("&2", Colors.DARK_BLUE)
						.replace("&3", Colors.DARK_GREEN)
						.replace("&4", Colors.RED)
						.replace("&5", Colors.BROWN)
						.replace("&6", Colors.PURPLE)
						.replace("&7", Colors.OLIVE)
						.replace("&8", Colors.YELLOW)
						.replace("&9", Colors.GREEN)
						.replace("&a", Colors.TEAL)
						.replace("&b", Colors.CYAN)
						.replace("&c", Colors.BLUE)
						.replace("&d", Colors.MAGENTA)
						.replace("&e", Colors.DARK_GRAY)
						.replace("&f", Colors.LIGHT_GRAY)
						.replace("&g", Colors.NORMAL)
						.replace("&h", Colors.BOLD)
						.replace("&i", Colors.UNDERLINE)
						.replace("&j", Colors.REVERSE)
						.replace("&k", Colors.ITALICS)
						/*Colors end*/;
			}
			catch(Exception e1)
			{
				Logging.stackTrace(e1);
				return "Error in localizing english message.\n";
			}
		}
	}
}
