package bl4ckb0tX.commands.normal;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Utilities;

public class Help
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event) //TODO: Update this menu!!
	{
		Utilities.userMsg(event, Colors.BOLD + Colors.OLIVE + "----------------------My Commands :)----------------------");
		Utilities.addHelpLine(event, "-letter <number> " + Colors.BOLD + Colors.BLUE + "| Outputs as many random letters as you specified (not more than 20)!");
		Utilities.addHelpLine(event, "-number <number> " + Colors.BOLD + Colors.BLUE + "| Outputs as many random numbers as you specified (not more than 20)!");
		Utilities.addHelpLine(event, "-u <name> [where to go] " + Colors.BOLD + Colors.BLUE + "| Minecraftforumuser command!");
		Utilities.addHelpLine(event, "-yt <channel> " + Colors.BOLD + Colors.BLUE + "| Sends you to the specified YouTube channel!");
		Utilities.addHelpLine(event, "-tw <profile> " + Colors.BOLD + Colors.BLUE + "| Sends you to the specified Twitter profile!");
		Utilities.addHelpLine(event, "-tv <channel> " + Colors.BOLD + Colors.BLUE + "| Sends you to the specified Twitch channel!");
		Utilities.addHelpLine(event, "-kick <user> <reason> " + Colors.BOLD + Colors.BLUE + "| Kicks a user from a channel. Only useable by specific people");
		Utilities.addHelpLine(event, "-draw " + Colors.BOLD + Colors.BLUE + "| Draws something on the screen!");
		Utilities.addHelpLine(event, "-latestforge " + Colors.BOLD + Colors.BLUE + "| Several commands to receive the changelog, source and installer.");
		Utilities.addHelpLine(event, "-decide <question> " + Colors.BOLD + Colors.BLUE + "| Decides for you. Please only enter Yes/No answerable questions.");
		Utilities.addEmptyLine(event, false);
		Utilities.userMsg(event, Colors.BOLD + Colors.OLIVE + "-------------------------Credits--------------------------");
		Utilities.userMsg(event, Colors.BLUE + "Made by bl4ckscor3!");
		Utilities.userMsg(event, Colors.BLUE + "Help from TehKitti!");
		Utilities.userMsg(event, Colors.BLUE + "Made in Java 7 using PircBotX 2.0.1 and its dependencies: https://code.google.com/p/pircbotx/");
		Utilities.userMsg(event, Colors.TEAL + "Suggestions are much appreciated! Just ping bl4ckscor3 if you want to suggest something!");
	}
}
