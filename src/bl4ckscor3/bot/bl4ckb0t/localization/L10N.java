package bl4ckscor3.bot.bl4ckb0t.localization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class L10N
{
	private static String language = "en";
	private static String country = "US";
	public static String langName = parseLangName(language + "_" + country);
	public static Locale currentLocale = new Locale(language, country);
	public static ResourceBundle strings = ResourceBundle.getBundle("bl4ckscor3.bot.bl4ckb0t.localization.bb", currentLocale);
	public static HashMap<String, String> chanLangs = new HashMap<String, String>();
	private static String path = L10N.class.getProtectionDomain().getCodeSource().getLocation().getPath();

	/**
	 * Changes the localization of the bot
	 * @param l - The language code
	 * @param c - The country code
	 * @param channel - The channel the language gets changed in
	 */
	public static void changeLocalization(String l, String c, String channel, boolean command) throws IOException
	{
		language = l;
		country = c;
		langName = parseLangName(language + "_" + country);
		update();

//		if(command)
//			addChannelToFile(channel);

		chanLangs.put(channel, langName);
	}

	/**
	 * Updates which language is used
	 */
	public static void update()
	{
		currentLocale = new Locale(language, country);
		strings = ResourceBundle.getBundle("bl4ckscor3.bot.bl4ckb0t.localization.bb", currentLocale);
	}

	/**
	 * Creates the language's name
	 */
	private static String parseLangName(String code)
	{
		if(code.equals("en_US"))
			return "english";
		else if(code.equals("de_DE"))
			return "german";
		else
			return "default";
	}

	/**
	 * Creates the language's code
	 * @param language - The language to return the code from
	 * @param code - The code to return (0 = language, 1 = country)
	 */
	public static String parseLangCode(String language, int code) throws ArrayIndexOutOfBoundsException
	{
		String langCode = "";

		switch(language)
		{
			case "english":
				langCode = "en_US";
				break;
			case "german":
				langCode = "de_DE";
				break;
			default:
				langCode = "none";
		}

		return langCode.split("_")[code];
	}

	private static void addChannelToFile(String channel) throws IOException
	{
		File f = new File(path + "/chanLangs.txt");
		BufferedReader reader;
		BufferedWriter writer;
		HashMap<String,String> channels = new HashMap<String,String>();
		List<String> rawChannels = new ArrayList<String>();
		String r = "";

		if(!f.exists())
			f.createNewFile();

		reader = new BufferedReader(new FileReader(f));
		writer = new BufferedWriter(new FileWriter(f, true));

		try
		{
			while((r = reader.readLine()) != null)
			{
				channels.put(r.split("=")[0], r.split("=")[1]);
				rawChannels.add(r.split("=")[0]);
			}

			channels.put(channel, langName);

			for(int i = 0; i < channels.size(); i++)
			{
				writer.newLine();
				writer.write(rawChannels.get(i) + "=" + channels.get(channel));
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			writer.write(channel + "=" + langName);
		}

		reader.close();
		writer.close();
	}
}
