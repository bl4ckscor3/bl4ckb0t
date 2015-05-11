package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Startup;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Update implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{
		if(Utilities.isValidUser(event))
		{
			Lists.clearAll();
			Startup.setAutoJoinChans();
			Startup.setAllowedUsers();
			Startup.setValidUsers();
			Utilities.chanMsg(event, L10N.getString("update.success"));
		}
		else
			Utilities.sorry(event);
	}

	@Override
	public String getAlias()
	{
		return "update";
	}

	@Override
	public String getSyntax()
	{
		return "-update";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-update | " + L10N.getString("update.usage")
				};
	}

	@Override
	public String getNotes()
	{
		return L10N.getString("notes.onlyOp");
	}
}
