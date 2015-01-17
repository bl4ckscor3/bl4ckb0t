package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.core.Listener;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Enable implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws MalformedURLException, IOException
	{
		if(Utilities.isValidUser(event))
		{
			if(!Listener.enabled)
			{
				Listener.enabled = true;
				Utilities.chanMsg(event, L10N.strings.getString("enable.success"));
			
				for(String s : Utilities.getJoinedChannels())
				{
					if(!s.equalsIgnoreCase(event.getChannel().getName()))
						Core.bot.sendIRC().message(s, L10N.strings.getString("enable.notify"));
				}
			}
			else
				Utilities.chanMsg(event, L10N.strings.getString("enable.alreadyEnabled"));
		}
		else
			Utilities.sorry(event);
	}
	
	@Override
	public String getAlias()
	{
		return "enable";
	}
	
	@Override
	public String getSyntax()
	{
		return "-enable";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-enable || " + L10N.strings.getString("enable.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("notes.onlyOp");
	}
}
