package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;
import bl4ckscor3.bot.bl4ckb0tX.core.Listener;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Stop implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws IOException, IrcException
	{
		if(Utilities.isValidUser(event))
		{
			if(event.getMessage().equalsIgnoreCase(event.getBot().getNick() + ", sleep!"))
			{
				Utilities.chanMsg(event, "k");
				Core.bot.sendRaw().rawLine("QUIT :My master sent me to sleep!");
				return;
			}

			String[] args = Utilities.toArgs(event.getMessage());

			if(args.length == 2)
			{
				switch(args[1])
				{
					case "yes":
						Utilities.chanMsg(event, "I will reboot, sir");
						Core.bot.sendRaw().rawLine("QUIT :My master sent me to sleep!");
						Core.createBot(); //creating another instance
						break;
					case "no":
						Utilities.chanMsg(event, "You wished that I don't reboot. Do you still like me?");
						Core.bot.sendRaw().rawLine("QUIT :My master sent me to sleep!");
						break;
					default:
						Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
				}
			}
			else
			{
				Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
			}
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
				"-stop yes || Stops the bot and restarts it.",
				"-stop no || Stops the bot but doesn't restart it."
				};
	}

	@Override
	public String getNotes()
	{
		return "Only useable by OPs.";
	}
}
