package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class CurseForgeWidget implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws Exception
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args[1].startsWith("http"))
			args[1] = args[1].substring(args[1].indexOf('/') + 2);

		Logging.debug(args[1]);
		
		String id = args[1].split("\\/")[2];
		
		if(Utilities.startsWithNumber(id))
			id = id.substring(id.indexOf('-') + 1);

		Utilities.chanMsg(event, "[mod=\"VERSION\"]" + id + "[/mod]");
		Utilities.chanMsg(event, L10N.getString("cfwidget.hereYouGo"));
	}

	@Override
	public String getAlias()
	{
		return "cfwidget";
	}

	@Override
	public String getSyntax()
	{
		return "-cfwidget <" + L10N.getString("cfwidget.link") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-cfwidget <" + L10N.getString("cfwidget.link") + "> || " + L10N.getString("cfwidget.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}

	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
