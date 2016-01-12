package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class CurseForgeWidget extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws Exception
	{
		if(args[1].startsWith("http"))
			args[1] = args[1].substring(args[1].indexOf('/') + 2);

		Logging.debug(args[1]);
		
		String id = args[1].split("\\/")[2];
		
		if(Utilities.startsWithNumber(id))
			id = id.substring(id.indexOf('-') + 1);

		Utilities.chanMsg(event, "[mod=\"VERSION\"]" + id + "[/mod]");
		Utilities.chanMsg(event, L10N.getString("cfwidget.hereYouGo", event));
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"cfwidget", "curseforgewidget", "cfw"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-cfwidget <" + L10N.getString("cfwidget.link", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-cfwidget <" + L10N.getString("cfwidget.link", event) + "> || " + L10N.getString("cfwidget.explanation", event)};
	}
}
