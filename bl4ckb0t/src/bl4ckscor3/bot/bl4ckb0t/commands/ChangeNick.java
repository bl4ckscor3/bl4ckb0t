package bl4ckscor3.bot.bl4ckb0t.commands;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ChangeNick implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws MalformedURLException, IOException
	{
		String[] args = Utilities.toArgs(event.getMessage());

		if(args.length == 2)
		{
			if(Utilities.isValidUser(event))
			{
				if(!args[1].equalsIgnoreCase("d"))
					Core.bot.sendIRC().changeNick(args[1]);
				else
					Core.bot.sendIRC().changeNick("bl4ckb0t");
			}
			else
				Utilities.sorry(event);
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
	}

	@Override
	public String getAlias()
	{
		return "changenick";
	}

	@Override
	public String getSyntax()
	{
		return "-changenick <" + L10N.strings.getString("changenick.newName") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-changenick <" + L10N.strings.getString("changenick.newName") + "> ||" + L10N.strings.getString("changenick.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("changenick.notes");
	}
}
