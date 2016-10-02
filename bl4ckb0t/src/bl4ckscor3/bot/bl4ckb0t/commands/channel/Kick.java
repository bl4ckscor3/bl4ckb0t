package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Kick extends BaseChannelCommand
{	
	@Override
	public void exe(MessageEvent event, String[] args) throws IOException, IrcException, IncorrectCommandExecutionException 
	{
		String channel = event.getChannel().getName();
		
		if(event.getUser().isVerified())
		{			
			if(!(Utilities.isValidUser(event.getUser()) || Utilities.isAllowedUser(event.getUser())))
			{
				Utilities.sendMessage(channel, L10N.getString("kick.notAuthorized", channel).replace("#user", event.getUser().getNick()));
				Logging.info("Denying command access to " + event.getUser().getNick() + "...");
				return;
			}

			if(args.length >= 1)
			{
				for(String userNotToKick : Lists.getValidUsers())
				{
					if(args[0].equalsIgnoreCase(userNotToKick))
					{
						Utilities.sendMessage(channel, L10N.getString("kick.cannotKick", channel));
						return;
					}
				}

				if(args[0].equalsIgnoreCase(Core.bot.getNick()))
				{
					event.getChannel().send().action(L10N.getString("kick.self", channel));
					Core.bot.kick(channel, args[0], L10N.getString("kick.self.reason", channel));

					if(L10N.chanLangs.containsKey(channel))
						L10N.chanLangs.remove(channel);
				}
				else
				{
					String result = "";

					for(int i = (args.length == 1 ? 0 : 1); i < args.length; i++)
					{
						result += args[i] + " ";
					}

					Core.bot.kick(channel, args[0], result.substring(0, result.lastIndexOf(' ')));
				}
			}
			else
				throw new IncorrectCommandExecutionException(getMainAlias());
		}
		else
		{
			Utilities.sendMessage(channel, L10N.getString("kick.identify", channel));
			Logging.info("User not identified, denying command access to " + event.getUser().getNick() + "...");
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"kick"};
	}

	@Override
	public String getSyntax(String channel)
	{
		return "-kick <" + L10N.getString("kick.help.user", channel) + "> [" + L10N.getString("kick.help.reason", channel) + "]";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{"-kick <" + L10N.getString("kick.help.user", channel) + "> [" + L10N.getString("kick.help.reason", channel) + "] || " + L10N.getString("kick.explanation", channel)};
	}

	@Override
	public String getNotes(String channel)
	{
		return L10N.getString("notes.onlyVoiceOp", channel);
	}

	@Override
	public int getPermissionLevel()
	{
		return 2;
	}
}
