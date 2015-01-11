package bl4ckscor3.bot.bl4ckb0t.commands;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.core.Listener;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Stop implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws IOException, IrcException
	{
		if(Utilities.isValidUser(event))
		{
			String[] args = Utilities.toArgs(event.getMessage());

			if(args.length == 2)
			{
				switch(args[1])
				{
					case "yes":
						Utilities.chanMsg(event, L10N.strings.getString("stop.reboot.yes"));
						Core.bot.sendRaw().rawLine("QUIT :" + L10N.strings.getString("stop.reason"));
						Core.createBot(); //creating another instance
						break;
					case "no":
						Utilities.chanMsg(event, L10N.strings.getString("stop.reboot.no"));
						Core.bot.sendRaw().rawLine("QUIT :" + L10N.strings.getString("stop.reason"));
						break;
					default:
						Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
				}
			}
			else
				Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
		}
		else
			Utilities.sorry(event);
	}

	@Override
	public String getAlias()
	{
		return "stop";
	}

	@Override
	public String getSyntax()
	{
		return "-stop <yes|no>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-stop yes || " + L10N.strings.getString("stop.explanation.1"),
				"-stop no || " + L10N.strings.getString("stop.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("notes.onlyOP");
	}
}
