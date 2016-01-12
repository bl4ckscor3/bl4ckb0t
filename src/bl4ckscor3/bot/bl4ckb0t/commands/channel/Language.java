package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Language extends BaseCommand<MessageEvent<Bot>>
{
	private String[] availableLanguages =
		{
				"English",
				"German"
		};

	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws IncorrectCommandExecutionException, IOException
	{
		if(args.length == 1)
			Utilities.chanMsg(event, L10N.getString("language.currentLanguage", event).replace("#lang", Utilities.capitalizeFirstLetter(L10N.chanLangs.get(event.getChannel().getName()))));
		else if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase(availableLanguages[0]))
			{
				L10N.setChannelLanguage(event.getChannel().getName(), "english");
				Logging.info("Changed localizitation in " + event.getChannel().getName() + " to " + availableLanguages[0] + ".");
			}
			else if(args[1].equalsIgnoreCase(availableLanguages[1]))
			{
				L10N.setChannelLanguage(event.getChannel().getName(), "german");
				Logging.info("Changed localizitation in " + event.getChannel().getName() + " to " + availableLanguages[1] + ".");
			}
			else	
				Utilities.chanMsg(event, L10N.getString("language.availableLanguages", event).replace("#langs", getAvailableLanguages()));

			Utilities.chanMsg(event, L10N.getString("language.success", event).replace("#lang", Utilities.capitalizeFirstLetter(L10N.chanLangs.get(event.getChannel().getName()))));
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	private String getAvailableLanguages()
	{
		String langs = "";

		for(int i = 0; i < availableLanguages.length; i++)
		{
			langs += availableLanguages[i] + (i + 1 == availableLanguages.length ? "" : " | ");
		}

		return langs;
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"language", "lang"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-language [" + L10N.getString("language.help.language", event) + "]";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{
				"-language || " + L10N.getString("language.explanation.1", event),
				"-language <" + L10N.getString("language.help.language", event) + "> || " + L10N.getString("language.explanation.2", event)
		};
	}

	@Override
	public int getPermissionLevel()
	{
		return 2;
	}
}
