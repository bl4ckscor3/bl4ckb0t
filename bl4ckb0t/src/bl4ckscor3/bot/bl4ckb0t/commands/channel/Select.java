package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Select extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException 
	{
		if(args.length != 0)
		{
			String[] options = event.getMessage().substring(8).split(",");

			Utilities.sendMessage(event.getChannel().getName(), options[new Random().nextInt(options.length)]);
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"select", "choose"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-select <" + L10N.getString("select.help.options", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-select <" + L10N.getString("select.help.options", channel) + "> || " + L10N.getString("select.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("select.notes", channel);
	}
}
