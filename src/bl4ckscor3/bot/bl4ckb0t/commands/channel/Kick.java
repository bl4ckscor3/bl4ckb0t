package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Kick implements ICommand<MessageEvent<Bot>>
{	
	@Override
	public void exe(MessageEvent<Bot> event) throws IOException, IrcException, IncorrectCommandExecutionException 
	{
		if(event.getUser().isVerified())
		{
			String[] args = Utilities.toArgs(event.getMessage());

			if(!(Utilities.isValidUser(event) || Utilities.isAllowedUser(event)))
			{
				Utilities.chanMsg(event, "Sorry, " + event.getUser().getNick() + ", " + L10N.getString("kick.notAuthorized"));
				Logging.info("Denying command access to " + event.getUser().getNick() + "...");
				return;
			}

			if(args.length >= 2)
			{
				for(String userNotToKick : Lists.getValidUsers())
				{
					if(args[1].equalsIgnoreCase(userNotToKick))
					{
						Utilities.chanMsg(event, L10N.getString("kick.cannotKick"));
						return;
					}
				}

				if(args[1].equalsIgnoreCase(Core.bot.getNick()))
				{
					event.getChannel().send().action(L10N.getString("kick.self"));
					Core.bot.kick(event.getChannel().getName(), args[1], L10N.getString("kick.self.reason"));

					if(L10N.chanLangs.containsKey(event.getChannel().getName()))
						L10N.chanLangs.remove(event.getChannel().getName());
				}
				else
				{
					String result = "";

					for(int i = (args.length == 2 ? 1 : 2); i < args.length; i++)
					{
						result += args[i] + " ";
					}

					Core.bot.kick(event.getChannel().getName(), args[1], result.substring(0, result.lastIndexOf(' ')));
				}
			}
			else
				throw new IncorrectCommandExecutionException(getAlias());
		}
		else
		{
			Utilities.chanMsg(event, L10N.getString("kick.identify"));
			Logging.info("User not identified, denying command access to " + event.getUser().getNick() + "...");
		}
	}

	@Override
	public String getAlias()
	{
		return "kick";
	}

	@Override
	public String getSyntax()
	{
		return "-kick <" + L10N.getString("kick.help.user") + "> [" + L10N.getString("kick.help.reason") + "]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-kick <" + L10N.getString("kick.help.user") + "> [" + L10N.getString("kick.help.reason") + "] || " + L10N.getString("kick.explanation")};
	}

	@Override
	public String getNotes()
	{
		return L10N.getString("notes.onlyVoiceOp");
	}

	@Override
	public int getPermissionLevel()
	{
		return 2;
	}
}
