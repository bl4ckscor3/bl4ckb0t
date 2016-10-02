package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Language extends BaseChannelCommand
{
	private String[] availableLanguages = {
				"English",
				"German"
		};

	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException, IOException
	{
		String channel = event.getChannel().getName();
		
		if(args.length == 0)
			Utilities.sendMessage(channel, L10N.getString("language.currentLanguage", channel).replace("#lang", Utilities.capitalizeFirstLetter(L10N.chanLangs.get(channel))));
		else if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase(availableLanguages[0]))
			{
				L10N.setChannelLanguage(channel, "english");
				Logging.info("Changed localizitation in " + channel + " to " + availableLanguages[0] + ".");
			}
			else if(args[0].equalsIgnoreCase(availableLanguages[1]))
			{
				L10N.setChannelLanguage(channel, "german");
				Logging.info("Changed localizitation in " + channel + " to " + availableLanguages[1] + ".");
			}
			else	
				Utilities.sendMessage(channel, L10N.getString("language.availableLanguages", channel).replace("#langs", getAvailableLanguages()));

			Utilities.sendMessage(channel, L10N.getString("language.success", channel).replace("#lang", Utilities.capitalizeFirstLetter(L10N.chanLangs.get(channel))));
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
	public String getSyntax(String channel)
	{
		return "-language [" + L10N.getString("language.help.language", channel) + "]";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-language || " + L10N.getString("language.explanation.1", channel),
				"-language <" + L10N.getString("language.help.language", channel) + "> || " + L10N.getString("language.explanation.2", channel)
		};
	}

	@Override
	public int getPermissionLevel()
	{
		return 2;
	}
}
