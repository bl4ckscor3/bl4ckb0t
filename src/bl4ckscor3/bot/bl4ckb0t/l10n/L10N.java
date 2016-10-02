package bl4ckscor3.bot.bl4ckb0t.l10n;

import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import org.pircbotx.Colors;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

@SuppressWarnings("unused")
public class L10N
{
	public static ArrayMap<String,String> chanLangs = new ArrayMap<String,String>();
	private ResourceBundle english;
	private ResourceBundle german;
	private String moduleName;
	
	/**
	 * Constructor for the main bot. Do NOT use this for initializing language for modules!
	 */
	public L10N()
	{
		moduleName = "bot";
		english = ResourceBundle.getBundle((Core.wasStartedAsWIP ? "" : "src.") + moduleName, new Locale("en", "US"));
		german = ResourceBundle.getBundle((Core.wasStartedAsWIP ? "" : "src.") + moduleName, new Locale("de", "DE"));
	}
	
	/**
	 * Initializes the language handler for the given module
	 * @param m The module to initialize for
	 * @param loader The loader of the module, used to load the properties files
	 */
	public L10N(Module m, URLClassLoader loader)
	{
		moduleName = m.getName().toLowerCase();
		english = ResourceBundle.getBundle("src." + moduleName, new Locale("en", "US"), loader);
		german = ResourceBundle.getBundle("src." + moduleName, new Locale("de", "DE"), loader);
	}
	
	/**
	 * Gets the String for the language in the given language by the given key
	 * @param key The language key
	 * @param channel The channel which's language to use
	 * @return The translated String
	 */
	public String translate(String key, String channel)
	{
		try
		{
			return ((ResourceBundle)getClass().getDeclaredField(chanLangs.get(channel)).get(this)).getString(key)
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
					.replace("%cmd%", Core.bot.getCmdPrefix());
		}
		catch(Exception e)
		{
			Logging.stackTrace(e);
			Logging.severe("Error in localizing message.");
			
			try
			{
				return ((ResourceBundle)getClass().getDeclaredField("english").get(this)).getString(key)
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
						.replace("%cmd%", Core.bot.getCmdPrefix());
			}
			catch(Exception e1)
			{
				Logging.stackTrace(e1);
				return "Error in localizing english message.\n";
			}
		}
	}
}
