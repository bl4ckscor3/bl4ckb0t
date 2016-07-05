package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.CMDListener;
import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.CustomArrayList;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Help extends BaseChannelCommand
{
	private static String[] aliasesValid;
	private static String[] aliasesAllowed;
	private static String[] aliasesNormal;

	@Override
	public void exe(MessageEvent event, String[] args) throws InterruptedException, IncorrectCommandExecutionException, MalformedURLException, IOException
	{
		String nick = event.getUser().getNick();
		String msg = "";
		int pLvl = Utilities.getUserPermissionLevel(event.getUser());
		String[] aliases = pLvl == 3 ? aliasesValid : (pLvl == 2 ? aliasesAllowed : aliasesNormal);
		String channel = event.getChannel().getName();
		
		if(args.length == 0)
		{
			Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("help.header", channel) + "----------");

			for(int i = 0; i < aliases.length; i++)
			{
				if(aliases[i] == null)
					continue;

				msg += aliases[i] + " | ";

				if((i + 1) % 10 == 0)
				{
					Utilities.sendMessage(nick, msg.trim());
					msg = "";
				}
			}

			Utilities.sendMessage(nick, msg.substring(0, msg.lastIndexOf(" | ")));
			Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + "----------------------------------------------------------");
			Utilities.sendMessage(nick, L10N.getString("help.moreInfo", channel));
			Thread.sleep(2000);
			Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + "----------" + L10N.getString("help.credits.header", channel) + "----------");
			Utilities.sendMessage(nick, Colors.TEAL + L10N.getString("help.credits.1", channel));
			Utilities.sendMessage(nick, Colors.TEAL + L10N.getString("help.credits.2", channel));
			Utilities.sendMessage(nick, Colors.TEAL + L10N.getString("help.credits.3", channel));
			Utilities.sendMessage(nick, Colors.TEAL + L10N.getString("help.credits.4", channel));
			Utilities.sendMessage(nick, Colors.TEAL + L10N.getString("help.credits.5", channel));
		}
		else if(args.length == 1)
		{
			for(BaseChannelCommand cmd : CMDListener.commands)
			{
				if(cmd.isValidAlias(args[0]) || cmd.isValidAlias("-" + args[0]))
				{
					if(cmd.getPermissionLevel() > pLvl)
					{
						Utilities.sendMessage(channel, "-" + cmd.getMainAlias() + ": " + L10N.getString("noPermission", channel));
						return;
					}

					Utilities.sendHelp(nick, cmd.getAliases(), cmd.getMainAlias(), cmd.getSyntax(channel), cmd.getUsage(channel), cmd.getNotes(channel), channel);
					return;
				}
			}

			Utilities.sendMessage(channel, args[0] + " " + L10N.getString("help.invalidCmd", channel));
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
	public String getSyntax(String channel)
	{
		return "-help [" + L10N.getString("help.help.command", channel) + "]";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-help || " + L10N.getString("help.explanation.1", channel),
				"-help <" + L10N.getString("help.help.command", channel) + "> || " + L10N.getString("help.explanation.2", channel)
		};
	}

	public static void setupHelpMenu(CustomArrayList<BaseChannelCommand> cmd)
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
		return Core.bot.getConfig().isEnabled("showCmdColorsInHelpMenu") ? c : "";
	}
}
