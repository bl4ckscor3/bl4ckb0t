package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class XColor extends BaseChannelCommand
{
	@Override
	public void exe(MessageEvent event, String[] args)
	{
		String[] colors = {
				"Grey",
				"Black",
				"Blue",
				"Dark Green",
				"Red",
				"Red",
				"Purple",
				"Brown",
				"Yellow",
				"Light Green",
				"Dark Aqua",
				"Lighter Aqua",
				"Pink"
		};
		User user = event.getUser();

		for(int i = 0; i < 14; i++)
		{
			if(i < 4)
				Utilities.notice(user, "%C" + i + ": " + colors[i]);
			else if(i > 4 && i < 12)
				Utilities.notice(user, "%C" + i + ": " + colors[i]);
			else if(i == 13)
				Utilities.notice(user, "%C" + i + ": " + colors[i - 1]);
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"xcolor", "xc"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-xcolor";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-xcolor || " + L10N.getString("xcolor.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("xcolor.notes", channel);
	}
}
