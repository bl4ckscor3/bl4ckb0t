package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class YouTube extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException
	{
		if(args.length == 1)
			Utilities.sendMessage(event.getChannel().getName(), "http://www.youtube.com/" + args[0]);
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"yt", "youtube"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-yt <" + L10N.getString("yt.help.channel", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-yt <" + L10N.getString("ytv.help.channel", channel) + "> || " + L10N.getString("yt.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("yt.notes", channel);
	}
}