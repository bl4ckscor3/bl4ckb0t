package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Vowels implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException
	{
		if(Utilities.toArgs(event.getMessage()).length >= 2)
		{
			char[] chars = event.getMessage().substring(8).toLowerCase().toCharArray();
			String result = "";

			for(int i = 0; i < chars.length; i++)
			{
				switch(chars[i])
				{
					case 'a': case 'e': case 'i': case 'o': case 'u': 
						result += ""; 
						break;
					default: 
						result += chars[i];
				}
			}

			Utilities.respond(event, result, false);
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias()
	{
		return "vowels";
	}

	@Override
	public String getSyntax()
	{
		return "-vowels <" + L10N.getString("cmd.help.sentence") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-vowels <" + L10N.getString("cmd.help.sentence") + "> || " + L10N.getString("vowels.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.getString("vowels.notes");
	}
}
