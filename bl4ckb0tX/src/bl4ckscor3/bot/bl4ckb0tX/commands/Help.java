package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Help implements Command<MessageEvent>
{
	private String color = Colors.BOLD + Colors.BLUE;
	
	@Override
	public void exe(MessageEvent event) //TODO: Update this menu!!
	{
		System.out.println(1);
		Utilities.userMsg(event, Colors.BOLD + Colors.OLIVE + "----------------------My Commands :)----------------------");
		Utilities.addHelpLine(event, "-bukkit <command> " + color + "| Several commands to receive the recommended, beta, and dev bukkit builds.");
		Utilities.addHelpLine(event, "-cbukkit <command> " + color + "| Several commands to receive the recommended, beta, and dev craftbukkit builds.");
		Utilities.addHelpLine(event, "-changenick <newNick>"  + color + "| Changes the bot's nickname. Only userable by specific people.");
		Utilities.addHelpLine(event, "-decide <question> " + color + "| Decides for you. Please only enter yes/no answerable questions.");
		Utilities.addHelpLine(event, "-disable " + color + "| Disables all the bot's features. Only useable by specific people.");
		Utilities.addHelpLine(event, "-draw <drawing> " + color + "| Draws something on the screen!");
		Utilities.addHelpLine(event, "-enable " + color + "| Enables all the bot's features. Only useable by specific people.");
		Utilities.addHelpLine(event, "-girlballs " + color + "| Girlballs.");
		Utilities.addHelpLine(event, "-kick <user> <reason> " + color + "| Kicks a user from a channel. Only useable by specific people.");
		Utilities.addHelpLine(event, "-latestforge <command> " + color + "| Several commands to receive the changelog, source and installer.");
		Utilities.addHelpLine(event, "-leet <sentence> " + color + "| Converts your sentence into leet.");
		Utilities.addHelpLine(event, "-letter <number> " + color + "| Outputs as many random letters as you specified (not more than 20)!");
		Utilities.addHelpLine(event, "-number <number> " + color + "| Outputs as many random numbers as you specified (not more than 20)!");
		Utilities.addHelpLine(event, "-select <options> " + color + "| Selects an option you specified for you. Please only enter options seperated with a comma.");
		Utilities.addHelpLine(event, "-source" + color + "| Gives you the link to the bot's sourcecode.");
		Utilities.addHelpLine(event, "-stop <yes|no> " + color + "| Stops the bot with or without a restart. Only useable by specific people.");
		Utilities.addHelpLine(event, "-tv <channel> " + color + "| Sends you to the specified Twitch channel!");
		Utilities.addHelpLine(event, "-tw <profile> " + color + "| Sends you to the specified Twitter profile!");
		Utilities.addHelpLine(event, "-u <name> [where to go] " + color + "| Minecraftforumuser command!");
		Utilities.addHelpLine(event, "-w <city> " + color + "| Shows you the current weather stats of the specified city.");
		Utilities.addHelpLine(event, "-yt <channel> " + color + "| Sends you to the specified YouTube channel!");
		Utilities.addEmptyLine(event, false);
		Utilities.userMsg(event, Colors.BOLD + Colors.OLIVE + "-------------------------Credits--------------------------");
		Utilities.userMsg(event, Colors.BLUE + "Made by bl4ckscor3!");
		Utilities.userMsg(event, Colors.BLUE + "Help from Lord_Ralex (and TehKitti, but that doesn't count)!");
		Utilities.userMsg(event, Colors.BLUE + "Made in Java 7 using PircBotX 2.0.1 and its dependencies: https://code.google.com/p/pircbotx/");
		Utilities.userMsg(event, Colors.TEAL + "Suggestions are much appreciated! Just ping bl4ckscor3 if you want to suggest something!");
	}
	
	@Override
	public String getAlias()
	{
		return "help";
	}
}