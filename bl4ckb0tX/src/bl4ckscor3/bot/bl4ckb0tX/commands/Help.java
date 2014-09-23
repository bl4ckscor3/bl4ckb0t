package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.util.LinkedList;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;
import bl4ckscor3.bot.bl4ckb0tX.core.Listener;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Help implements ICommand<MessageEvent>
{
	private static String[] aliases;

	public static void setupHelpMenu(LinkedList<ICommand> cmd)
	{
		aliases = new String[cmd.size()];

		for(int i = 0; i < cmd.size(); i++)
		{
			aliases[i] = "-" + cmd.get(i).getAlias();
		}
	}

	@Override
	public void exe(MessageEvent event) throws InterruptedException
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String nick = event.getUser().getNick();
		int i = 0;
		
		if(args.length == 1)
		{
			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------------------My Commands :)----------------------");

			for(String s : aliases)
			{
				if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24 || i == 29 || i == 34 || i == 39)
					Thread.sleep(2000);
				
				Utilities.pm(nick, "    " + s);
				i++;
			}

			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "----------------------------------------------------------");
			Utilities.pm(nick, "Write -help <command> to get more specific help on that command.");
			Thread.sleep(2000);
			Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "-------------------------Credits--------------------------");
			Utilities.pm(nick, Colors.TEAL + "Made by bl4ckscor3!");
			Utilities.pm(nick, Colors.TEAL + "Help from Lord_Ralex (and TehKitti, but that doesn't count)!");
			Utilities.pm(nick, Colors.TEAL + "Made in Java 7 using PircBotX 2.0.1 (https://code.google.com/p/pircbotx/), its dependencies and Selenium (https://code.google.com/p/selenium/).");
			Utilities.pm(nick, Colors.TEAL + "Suggestions are much appreciated! Just ping bl4ckscor3 if you want to suggest something!");
		}
		else if(args.length == 2)
		{
			System.out.println("-" + args[1]);
			
			for(ICommand cmd : Listener.commands)
			{
				System.out.println("-" + cmd.getAlias());
				
				if(cmd.getAlias().equals(args[1]) || ("-" + cmd.getAlias()).equals(args[1]))
				{
					Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "-------------------------Syntax--------------------------");
					Utilities.pm(nick, cmd.getSyntax());
					Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "--------------------------Usage--------------------------");

					for(String s : cmd.getUsage())
					{
						Utilities.pm(nick, s);
					}

					Utilities.pm(nick, Colors.BOLD + Colors.OLIVE + "--------------------------Notes--------------------------");
					Utilities.pm(nick, cmd.getNotes() == null ? "None." :  cmd.getNotes());
					break;
				}
			}
		}
		else
			Utilities.chanMsg(event, "Only add 1 command as an argument.");
	}

	public String getAlias()
	{
		return "help";
	}

	@Override
	public String getSyntax()
	{
		return "-help [command]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-help || Shows this menu.",
				"-help <command> || Shows help for the specified command."
				};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
