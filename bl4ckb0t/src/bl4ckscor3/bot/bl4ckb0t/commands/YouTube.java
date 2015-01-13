package bl4ckscor3.bot.bl4ckb0t.commands;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class YouTube implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
			Utilities.respond(event, "http://www.youtube.com/" + args[1], false);
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}

	@Override
	public String getAlias()
	{
		return "yt";
	}

	@Override
	public String getSyntax()
	{
		return "-yt <" + L10N.strings.getString("yt.help.channel") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-yt <" + L10N.strings.getString("ytv.help.channel") + "> || " + L10N.strings.getString("yt.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("yt.notes");
	}
}