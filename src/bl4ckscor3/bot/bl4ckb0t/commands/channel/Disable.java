package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.core.Listener;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Disable implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws MalformedURLException, IOException
	{	
		if(Utilities.isValidUser(event))
		{
			if(Listener.enabled)
			{
				Listener.enabled = false;
				Utilities.chanMsg(event, L10N.strings.getString("disable.success"));
				System.out.println(Utilities.getJoinedChannels().length);
				if(Utilities.getJoinedChannels().length == 1)
					return;
				
				for(String s : Utilities.getJoinedChannels())
				{
					if(!s.equalsIgnoreCase(event.getChannel().getName()))
						Core.bot.sendIRC().message(s, L10N.strings.getString("disable.notify"));
				}
			}
			else
				Utilities.chanMsg(event, L10N.strings.getString("disable.alreadyDisabled"));
		}
		else
			Utilities.sorry(event);
	}
	
	@Override
	public String getAlias()
	{
		return "disable";
	}

	@Override
	public String getSyntax()
	{
		return "-disable";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-disable || " + L10N.strings.getString("disable.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("notes.onlyOp");
	}
}
