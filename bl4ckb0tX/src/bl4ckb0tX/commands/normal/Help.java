package bl4ckb0tX.commands.normal;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class Help
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		Stuffz.userMsg(event, Colors.BOLD + Colors.OLIVE + "----------------------My Commands :)----------------------");
		Stuffz.addHelpLine(event, "-time                       " + Colors.BOLD + "| Tells you the current time from where I'm running!");
		Stuffz.addHelpLine(event, "-letter <number>            " + Colors.BOLD + "| Outputs as many random letters as you specified (not more than 20)!");
		Stuffz.addHelpLine(event, "-number <number>            " + Colors.BOLD + "| Outputs as many random numbers as you specified (not more than 20)!");
		Stuffz.addHelpLine(event, "-joke                       " + Colors.BOLD + "| Simply tells a joke! NOT IMPLEMENTED YET");
		Stuffz.addHelpLine(event, "-addjoke                    " + Colors.BOLD + "| You can add your own Jokes with this command! NOT IMPLEMENTED YET");
		Stuffz.addHelpLine(event, "-u <name> [where to go]     " + Colors.BOLD + "| Minecraftforumuser command!");
		Stuffz.addHelpLine(event, "-yt <channel>               " + Colors.BOLD + "| Sends you to the specified YouTube channel!");
		Stuffz.addHelpLine(event, "-tw <profile>               " + Colors.BOLD + "| Sends you to the specified Twitter profile!");
		Stuffz.addHelpLine(event, "-tv <channel>               " + Colors.BOLD + "| Sends you to the specified Twitch channel!");
		Stuffz.addHelpLine(event, "-say <what to say>          " + Colors.BOLD + "| Lets bl4ckb0t say what you said if debug mode is activated!");
		Stuffz.addHelpLine(event, "-kick <user> <reason>       " + Colors.BOLD + "| Kicks a user from a channel. Only useable by specific people");
		Stuffz.addHelpLine(event, "-draw                       " + Colors.BOLD + "| Draws something on the screen!");
		Stuffz.addHelpLine(event, "-latestforge                " + Colors.BOLD + "| Several commands to receive the changelog, source and installer.");
		Stuffz.addHelpLine(event, "-decide <question>		   " + Colors.BOLD + "| Decides for you. Please only enter Yes/No answerable questions.");
		Stuffz.addEmptyLine(event, false);
		Stuffz.userMsg(event, Colors.BOLD + Colors.OLIVE + "----------------------Other features----------------------");
		Stuffz.addHelpLine(event, "Welcome Messages!");
		Stuffz.addHelpLine(event, "Eastereggs!");
		Stuffz.addEmptyLine(event, false);
		Stuffz.userMsg(event, Colors.BOLD + Colors.OLIVE + "-------------------------Credits--------------------------");
		Stuffz.userMsg(event, Colors.BLUE + "Made by bl4ckscor3!");
		Stuffz.userMsg(event, Colors.BLUE + "Help from TehKitti!");
		Stuffz.userMsg(event, Colors.BLUE + "Made in Java 7 using PircBotX 2.0.1 and its dependencies: https://code.google.com/p/pircbotx/");
		Stuffz.userMsg(event, Colors.TEAL + "Suggestions are much appreciated! Just ping bl4ckscor3 if you want to suggest something!");
	}
}
