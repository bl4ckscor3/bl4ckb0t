package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.CMDListener;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Help implements ICommand<MessageEvent<Bot>>
{
	private static String[] aliasesValid;
	private static String[] aliasesAllowed;
	private static String[] aliasesNormal;

	public static void setupHelpMenu(LinkedList<ICommand<MessageEvent<Bot>>> cmd)
	{
		aliasesValid = new String[cmd.size()];
		aliasesAllowed = new String[cmd.size()];
		aliasesNormal = new String[cmd.size()];

		for(int i = 0; i < cmd.size(); i++)
		{
			switch(cmd.get(i).getPermissionLevel())
			{
				case 3:
					aliasesValid[i] = Colors.RED + "-" + cmd.get(i).getAlias() + Colors.NORMAL;
					break;
				case 2:
					aliasesValid[i] = Colors.DARK_GREEN + "-" + cmd.get(i).getAlias() + Colors.NORMAL;
					aliasesAllowed[i] = Colors.DARK_GREEN + "-" + cmd.get(i).getAlias() + Colors.NORMAL;
					break;
				case 1:
					aliasesValid[i] = "-" + cmd.get(i).getAlias();
					aliasesAllowed[i] = "-" + cmd.get(i).getAlias();
					aliasesNormal[i] = "-" + cmd.get(i).getAlias();
					break;
			}
		}
	}

	@Override
	public void exe(MessageEvent<Bot> event) throws InterruptedException, IncorrectCommandExecutionException, MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String nick = event.getUser().getNick();
		String msg = "";
		int pLvl = Utilities.getUserPermissionLevel(event);
		String[] aliases = pLvl == 3 ? aliasesValid : (pLvl == 2 ? aliasesAllowed : aliasesNormal);

		if(args.length == 1)
		{
			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("help.header") + "----------");

			for(int i = 0; i < aliases.length; i++)
			{
				if(aliases[i] == null)
					continue;
				
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
			for(ICommand<MessageEvent<Bot>> cmd : CMDListener.commands)
			{
				if(cmd.getAlias().equals(args[1]) || ("-" + cmd.getAlias()).equals(args[1]))
				{
					if(cmd.getPermissionLevel() > pLvl)
					{
						Core.bot.sendCustomMessage(event.getChannel().getName(), "-" + cmd.getAlias() + ": " + L10N.getString("noPermission"));
						return;
					}
					
					Utilities.sendHelp(nick, cmd.getSyntax(), cmd.getUsage(), cmd.getNotes());
					return;
				}
			}
			
			Utilities.chanMsg(event, args[1] + " " + L10N.getString("help.invalidCmd"));
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
	
	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
