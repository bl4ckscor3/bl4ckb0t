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

public class Kick extends BaseCommand<MessageEvent<Bot>>
{	
	@Override
	public void exe(MessageEvent<Bot> event) throws IOException, IrcException, IncorrectCommandExecutionException 
	{
		if(event.getUser().isVerified())
		{
			String[] args = Utilities.toArgs(event.getMessage());

			if(!(Utilities.isValidUser(event) || Utilities.isAllowedUser(event)))
			{
				Utilities.chanMsg(event, L10N.getString("kick.notAuthorized", event).replace("#user", event.getUser().getNick()));
				Logging.info("Denying command access to " + event.getUser().getNick() + "...");
				return;
			}

			if(args.length >= 2)
			{
				for(String userNotToKick : Lists.getValidUsers())
				{
					if(args[1].equalsIgnoreCase(userNotToKick))
					{
						Utilities.chanMsg(event, L10N.getString("kick.cannotKick", event));
						return;
					}
				}

				if(args[1].equalsIgnoreCase(Core.bot.getNick()))
				{
					event.getChannel().send().action(L10N.getString("kick.self", event));
					Core.bot.kick(event.getChannel().getName(), args[1], L10N.getString("kick.self.reason", event));

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
			Utilities.chanMsg(event, L10N.getString("kick.identify", event));
			Logging.info("User not identified, denying command access to " + event.getUser().getNick() + "...");
		}
	}

	@Override
	public String getAlias()
	{
		return "kick";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-kick <" + L10N.getString("kick.help.user", event) + "> [" + L10N.getString("kick.help.reason", event) + "]";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-kick <" + L10N.getString("kick.help.user", event) + "> [" + L10N.getString("kick.help.reason", event) + "] || " + L10N.getString("kick.explanation", event)};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return L10N.getString("notes.onlyVoiceOp", event);
	}

	@Override
	public int getPermissionLevel()
	{
		return 2;
	}
}
