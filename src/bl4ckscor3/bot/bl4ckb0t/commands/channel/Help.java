package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.CMDListener;
import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.CustomArrayList;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Help extends BaseCommand<MessageEvent>
{
	private static String[] aliasesValid;
	private static String[] aliasesAllowed;
	private static String[] aliasesNormal;

	@Override
	public void exe(MessageEvent event, String[] args) throws InterruptedException, IncorrectCommandExecutionException, MalformedURLException, IOException
	{
		String nick = event.getUser().getNick();
		String msg = "";
		int pLvl = Utilities.getUserPermissionLevel(event);
		String[] aliases = pLvl == 3 ? aliasesValid : (pLvl == 2 ? aliasesAllowed : aliasesNormal);

		if(args.length == 1)
		{
			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("help.header", event) + "----------");

			for(int i = 0; i < aliases.length; i++)
			{
				if(aliases[i] == null)
					continue;

				msg += aliases[i] + " | ";

				if((i + 1) % 10 == 0)
				{
					Utilities.pm(nick, msg.trim());
					msg = "";
				}
			}

			Utilities.pm(nick, msg.substring(0, msg.lastIndexOf(" | ")));
			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------------------------------------------------------");
			Utilities.pm(nick, L10N.getString("help.moreInfo", event));
			Thread.sleep(2000);
			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("help.credits.header", event) + "----------");
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.1", event));
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.2", event));
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.3", event));
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.4", event));
			Utilities.pm(nick, Colors.TEAL + L10N.getString("help.credits.5", event));
		}
		else if(args.length == 2)
		{
			for(BaseCommand<MessageEvent> cmd : CMDListener.commands)
			{
				if(cmd.isValidAlias(args[1]) || cmd.isValidAlias("-" + args[1]))
				{
					if(cmd.getPermissionLevel() > pLvl)
					{
						Core.bot.sendCustomMessage(event.getChannel().getName(), "-" + cmd.getMainAlias() + ": " + L10N.getString("noPermission", event));
						return;
					}

					Utilities.sendHelp(nick, cmd.getAliases(), cmd.getMainAlias(), cmd.getSyntax(event), cmd.getUsage(event), cmd.getNotes(event), event);
					return;
				}
			}

			Utilities.chanMsg(event, args[1] + " " + L10N.getString("help.invalidCmd", event));
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"help"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-help [" + L10N.getString("help.help.command", event) + "]";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{
				"-help || " + L10N.getString("help.explanation.1", event),
				"-help <" + L10N.getString("help.help.command", event) + "> || " + L10N.getString("help.explanation.2", event)
		};
	}

	public static void setupHelpMenu(CustomArrayList<BaseCommand<MessageEvent>> cmd)
	{
		aliasesValid = new String[cmd.size()];
		aliasesAllowed = new String[cmd.size()];
		aliasesNormal = new String[cmd.size()];

		for(int i = 0; i < cmd.size(); i++)
		{
			switch(cmd.get(i).getPermissionLevel())
			{
				case 3:
					aliasesValid[i] = shouldColor(Colors.RED) + "-" + cmd.get(i).getMainAlias() + Colors.NORMAL;
					break;
				case 2:
					aliasesValid[i] = shouldColor(Colors.DARK_GREEN) + "-" + cmd.get(i).getMainAlias() + Colors.NORMAL;
					aliasesAllowed[i] = shouldColor(Colors.DARK_GREEN) + "-" + cmd.get(i).getMainAlias() + Colors.NORMAL;
					break;
				case 1:
					aliasesValid[i] = "-" + cmd.get(i).getMainAlias();
					aliasesAllowed[i] = "-" + cmd.get(i).getMainAlias();
					aliasesNormal[i] = "-" + cmd.get(i).getMainAlias();
					break;
			}
		}

		Logging.info("Finished setting up help menu...");
	}
	
	private static String shouldColor(String c)
	{
		return Core.bot.getConfig().getBoolean("showCmdColorsInHelpMenu") ? c : "";
	}
}
