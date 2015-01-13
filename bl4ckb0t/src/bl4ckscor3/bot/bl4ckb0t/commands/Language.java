package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Language implements ICommand<MessageEvent>
{
	String[] availableLanguages =
		{
			"English",
			"German"
		};
	
	@Override
	public void exe(MessageEvent event) throws Exception
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 1)
			Utilities.chanMsg(event, L10N.strings.getString("language.availableLanguages") + ": " + getAvailableLanguages());
		else if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase(availableLanguages[0]))
				L10N.changeLocalization("en", "US", event.getChannel().getName());
			else if(args[1].equalsIgnoreCase(availableLanguages[1]))
				L10N.changeLocalization("de", "DE", event.getChannel().getName());
			else	
				Utilities.chanMsg(event, L10N.strings.getString("language.availableLanguages") + ": " + getAvailableLanguages());
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
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
