package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Language implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] availableLanguages =
			{
				L10N.strings.getString("language.english"),
				L10N.strings.getString("language.german")
			};

		if(args.length == 1)
		{
			String langs = "";

			for(int i = 0; i < availableLanguages.length; i++)
			{
				langs += availableLanguages[i] + (i + 1 == availableLanguages.length ? "" : " | ");
			}

			Utilities.chanMsg(event, L10N.strings.getString("language.availableLanguages") + ": " + langs);
		}
		else if(args.length == 2)
		{
			switch(args[1])
			{
				case "english":
					L10N.changeLocalization(event.getChannel().getName(), args[1], "en", "US");
					break;
				case "german":
					L10N.changeLocalization(event.getChannel().getName(), args[1], "de", "DE");
					break;
				default:
					Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
			}
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}

	@Override
	public String getAlias()
	{
		return "language";
	}

	@Override
	public String getSyntax()
	{
		return "-language [" + L10N.strings.getString("language.help.language") + "]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-language || " + L10N.strings.getString("language.explanation.1"),
				"-language <" + L10N.strings.getString("language.help.language") + "> || " + L10N.strings.getString("language.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
