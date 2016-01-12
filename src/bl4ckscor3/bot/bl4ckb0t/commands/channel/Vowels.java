package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Vowels extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length >= 2)
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

			Utilities.chanMsg(event, result);
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"vowels"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-vowels <" + L10N.getString("cmd.help.sentence", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-vowels <" + L10N.getString("cmd.help.sentence", event) + "> || " + L10N.getString("vowels.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("vowels.notes", event);
	}
}
