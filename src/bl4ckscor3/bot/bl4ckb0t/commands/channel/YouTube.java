package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class YouTube implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
			Utilities.chanMsg(event, "http://www.youtube.com/" + args[1]);
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias()
	{
		return "yt";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-yt <" + L10N.getString("yt.help.channel", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-yt <" + L10N.getString("ytv.help.channel", event) + "> || " + L10N.getString("yt.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("yt.notes", event);
	}
	
	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}