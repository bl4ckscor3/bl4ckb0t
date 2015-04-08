package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.LinkedList;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Listener;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Help implements ICommand<MessageEvent<Bot>>
{
	private static String[] aliases;

	public static void setupHelpMenu(LinkedList<ICommand<MessageEvent<Bot>>> cmd)
	{
		aliases = new String[cmd.size()];

		for(int i = 0; i < cmd.size(); i++)
		{
			aliases[i] = "-" + cmd.get(i).getAlias();
		}
	}

	@Override
	public void exe(MessageEvent<Bot> event) throws InterruptedException, IncorrectCommandExecutionException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String nick = event.getUser().getNick();
		String msg = "";

		if(args.length == 1)
		{
			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("help.header") + "----------");

			for(int i = 0; i < aliases.length; i++)
			{
				msg += aliases[i] + " | ";
				
				if((i + 1) % 10 == 0)
				{
					Utilities.pm(nick, msg);
					msg = "";
				}
			}
			
			Utilities.pm(nick, msg.substring(0, msg.lastIndexOf(" | ")));
			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------------------------------------------------------");
			Utilities.pm(nick, L10N.getString("help.moreInfo"));
			Thread.sleep(2000);
			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("help.credits.header") + "----------");
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.1"));
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.2"));
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.3"));
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.4"));
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.5"));
		}
		else if(args.length == 2)
		{
			for(ICommand<MessageEvent<Bot>> cmd : Listener.commands)
			{
				if(cmd.getAlias().equals(args[1]) || ("-" + cmd.getAlias()).equals(args[1]))
				{
					Utilities.sendHelp(nick, cmd.getSyntax(), cmd.getUsage(), cmd.getNotes());
					return;
				}
			}
		}
		else
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias()
	{
		return "help";
	}

	@Override
	public String getSyntax()
	{
		return "-help [" + L10N.getString("help.help.command") + "]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-help || " + L10N.getString("help.explanation.1"),
				"-help <" + L10N.getString("help.help.command") + "> || " + L10N.getString("help.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
