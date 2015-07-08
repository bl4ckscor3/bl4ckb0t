package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Language implements ICommand<MessageEvent<Bot>>
{
	private String[] availableLanguages =
		{
			"English",
			"German"
		};
	
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 1)
			Utilities.chanMsg(event, L10N.getString("language.currentLanguage") + ": " + Utilities.capitalizeFirstLetter(L10N.langName));
		else if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase(availableLanguages[0]))
			{
				L10N.changeLocalization("en", "US", event.getChannel().getName());
				Logging.info("Changed localizitation in " + event.getChannel().getName() + " to " + availableLanguages[0] + ".");
			}
			else if(args[1].equalsIgnoreCase(availableLanguages[1]))
			{
				L10N.changeLocalization("de", "DE", event.getChannel().getName());
				Logging.info("Changed localizitation in " + event.getChannel().getName() + " to " + availableLanguages[1] + ".");
			}
			else	
				Utilities.chanMsg(event, L10N.getString("language.availableLanguages") + ": " + getAvailableLanguages());

			Utilities.chanMsg(event, L10N.getString("language.success") + ": " + Utilities.capitalizeFirstLetter(L10N.langName));
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
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
	public String getAlias()
	{
		return "language";
	}

	@Override
	public String getSyntax()
	{
		return "-language [" + L10N.getString("language.help.language") + "]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-language || " + L10N.getString("language.explanation.1"),
				"-language <" + L10N.getString("language.help.language") + "> || " + L10N.getString("language.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
	
	@Override
	public int getPermissionLevel()
	{
		return 2;
	}
}
