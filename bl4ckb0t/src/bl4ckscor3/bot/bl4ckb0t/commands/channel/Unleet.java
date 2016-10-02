package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Unleet extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length >= 1)
		{
			char[] chars = event.getMessage().substring(8).toCharArray();
			String result = "";

			for(int i = 0; i < chars.length; i++)
			{
				switch(chars[i])
				{
					case '4': result += "a"; break;
					case '3': result += "e"; break;
					case '6': result += "g"; break;
					case '1': result += "l"; break;
					case '0': result += "o"; break;
					case '5': result += "s"; break;
					case '7': result += "t"; break;
					case '2': result += "z"; break;
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
		return new String[]{"unleet"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-unleet <" + L10N.getString("cmd.help.sentence", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-unleet <" + L10N.getString("cmd.help.sentence", channel) + "> || " + L10N.getString("unleet.explanation", channel)};
	}
}
