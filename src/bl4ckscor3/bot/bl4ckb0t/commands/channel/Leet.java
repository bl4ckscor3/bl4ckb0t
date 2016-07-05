package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Leet extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length >= 1)
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
					case 'L': case 'l': result += "1"; break;
					case 'O': case 'o': result += "0"; break;
					case 'S': case 's': result += "5"; break;
					case 'T': case 't': result += "7"; break;
					case 'Z': case 'z': result += "2"; break;
					default: result += chars[i];
				}
			}
			
			Utilities.sendMessage(event.getChannel().getName(), result);
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"leet", "1337"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-leet <" + L10N.getString("cmd.help.sentence", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-leet <" + L10N.getString("cmd.help.sentence", channel) + "> || " + L10N.getString("leet.explanation", channel)};
	}
}
