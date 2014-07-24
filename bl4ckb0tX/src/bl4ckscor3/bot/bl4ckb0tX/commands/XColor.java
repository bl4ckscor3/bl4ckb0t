package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class XColor implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception 
	{
		String[] colors =
			{
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

		for(int i = 0; i < 14; i++)
		{
			if(i < 4)
				Utilities.notice(event, "%C" + i + ": " + colors[i]);
			else if(i > 4 && i < 12)
				Utilities.notice(event, "%C" + i + ": " + colors[i]);
			else if(i == 13)
				Utilities.notice(event, "%C" + i + ": " + colors[i - 1]);
		}
	}

	@Override
	public String getAlias() 
	{
		return "xcolor";
	}

}
