package bl4ckscor3.bot.bl4ckb0t;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.UtilSSLSocketFactory;

import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;

public class Core
{
	public static Bot bot;
	public static boolean wasStartedAsWIP;
	private static final String botName = "bl4ckb0t";
	private static final String version = "6.0.1.3";
	public static Modules modules;
	public static L10N l10n;

	public static void main(String args[])
	{
		try
		{
			Logging.setup(botName);
			Logger.getLogger("").setLevel(Level.OFF);
			Logging.info("Disabled loggers");
			wasStartedAsWIP = args.length >= 1 && args[0].equals("-wip");

			if(wasStartedAsWIP)
				Logging.info("Bot was started as WIP version");

			createBot(wasStartedAsWIP);
		}
		catch(Exception e)
		{
			Logging.stackTrace(e);
		}
	}

	/**
	 * Creates the bot and starts it
	 * @param wip Whether or not the bot should be started as a development version
	 */
	public static void createBot(boolean wip) throws Exception
	{
		Module.Builder builder = (Module.Builder)new Module.Builder()
				.setVersion(version + (wip ? "_WIP" : ""))
				.setName(botName)
				.setLogin(botName)
				.addServer("irc.esper.net", 6697)
				.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates())
				.setNickservPassword(Passwords.NICKSERV.getPassword())
				.setAutoNickChange(true)
				.setAutoReconnect(true)
				.setMessageDelay(0)
				.addListener(new Listener())
				.addListener(new Logging());
		Logging.info("Created PircBotX config");
		Logging.info("Setting up external information");
		Startup.callMethods();
		Logging.info("Starting to load modules");
		modules = new Modules(builder);
		Logging.info("Loading private modules");
		builder = modules.initPrivate();
		Logging.info("Loading public modules");
		builder = modules.initPublic();
		Logging.info("All modules loaded");
		bot = new Bot(builder.buildConfiguration(), "-");
		l10n = new L10N();
		Logging.info("Completed last setup steps");
		Logging.info("Starting bot");
		bot.startBot();
	}
}
