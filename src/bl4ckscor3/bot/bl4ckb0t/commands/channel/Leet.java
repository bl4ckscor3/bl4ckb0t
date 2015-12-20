package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Leet extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException
	{
		if(Utilities.toArgs(event.getMessage()).length >= 2)
		{
			char[] chars = event.getMessage().substring(6).toCharArray();
			String result = "";

			for(int i = 0; i < chars.length; i++)
			{
				switch(chars[i])
				{
					case 'A': case 'a': result += "4"; break;
					case 'E': case 'e': result += "3"; break;
					case 'G': case 'g': result += "6"; break;
					case 'H': case 'h': result += "h"; break;
					case 'I': case 'i': result += "1"; break;
					case 'L': case 'l': result += "|"; break;
					case 'S': case 's': result += "5"; break;
					case 'T': case 't': result += "7"; break;
					case 'Z': case 'z': result += "2"; break;
					default: result += chars[i];
				}
			}
			Utilities.chanMsg(event, result);
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias()
	{
		return "leet";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-leet <" + L10N.getString("cmd.help.sentence", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-leet <" + L10N.getString("cmd.help.sentence", event) + "> || " + L10N.getString("leet.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return null;
	}
}
