package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Stop implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IOException, IrcException, IncorrectCommandExecutionException
	{
		if(Utilities.isValidUser(event))
		{
			String[] args = Utilities.toArgs(event.getMessage());

			if(args.length == 2)
			{
				switch(args[1])
				{
					case "yes":
						Utilities.chanMsg(event, L10N.getString("stop.reboot.yes"));
						Core.bot.sendRaw().rawLine("QUIT :" + L10N.getString("stop.reason"));
						Core.createBot(); //creating another instance
						break;
					case "no":
						Utilities.chanMsg(event, L10N.getString("stop.reboot.no"));
						Core.bot.sendRaw().rawLine("QUIT :" + L10N.getString("stop.reason"));
						break;
					default:
						throw new IncorrectCommandExecutionException(getAlias());
				}
			}
			else
				throw new IncorrectCommandExecutionException(getAlias());
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
				"-stop yes || " + L10N.getString("stop.explanation.1"),
				"-stop no || " + L10N.getString("stop.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return L10N.getString("notes.onlyOp");
	}
}
