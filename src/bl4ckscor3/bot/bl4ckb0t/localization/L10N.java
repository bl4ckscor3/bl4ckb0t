package bl4ckscor3.bot.bl4ckb0t.localization;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

@SuppressWarnings("unused") //resource bundles are unused because they are called via reflection
public class L10N
{
	private static ResourceBundle english = ResourceBundle.getBundle("bl4ckscor3.bot.bl4ckb0t.localization.bb", new Locale("en", "US"));
	private static ResourceBundle german = ResourceBundle.getBundle("bl4ckscor3.bot.bl4ckb0t.localization.bb", new Locale("de", "DE"));
	public static ArrayMap<String, String> chanLangs = new ArrayMap<String, String>();

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
	public static String getString(String key, MessageEvent event)
	{
		try
		{
			return ((ResourceBundle)L10N.class.getDeclaredField(chanLangs.get(event.getChannel().getName())).get(null)).getString(key);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Logging.severe("Error in localizing message.");
			
			try
			{
				return ((ResourceBundle)L10N.class.getDeclaredField("english").get(null)).getString(key);
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
				return "Error in localizing english message.\n";
			}
		}
	}
}
