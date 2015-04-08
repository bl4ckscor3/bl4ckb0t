package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Kick implements ICommand<MessageEvent<Bot>>
{	
	@Override
	public void exe(MessageEvent<Bot> event) throws IOException, IrcException, IncorrectCommandExecutionException 
	{
		if(event.getUser().isVerified())
		{
			boolean allowed = false;
			boolean found = false;
				
			String[] args = Utilities.toArgs(event.getMessage());
			
			for(String allowedUser : Lists.getAllowedUsers())
			{
				if(event.getUser().getNick().equals(allowedUser))
				{	
					allowed = true;
					break;
				}
			}

			for(String allowedUser : Lists.getValidUsers())
			{
				if(event.getUser().getNick().equalsIgnoreCase(allowedUser))
				{
					allowed = true;
					break;
				}
			}

			if(!allowed)
			{
				Utilities.chanMsg(event, "Sorry, " + event.getUser().getNick() + ", " + L10N.getString("kick.notAuthorized"));
				return;
			}

			if(args.length >= 3)
			{
				for(String userNotToKick : Lists.getValidUsers())
				{
					if(args[1].equalsIgnoreCase(userNotToKick))
					{
						found = true;
						break;
					}
				}

				if(!found)
				{
					if(args[1].equalsIgnoreCase(Core.bot.getNick()))
					{
						event.getChannel().send().action(L10N.getString("kick.self"));
						Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + args[1] + " :" + L10N.getString("kick.self.reason"));
						
						if(L10N.chanLangs.containsKey(event.getChannel().getName()))
							L10N.chanLangs.remove(event.getChannel().getName());
					}
					else
					{
						String result = "";

						for(int i = 3; i <= args.length; i++)
						{
							result += args[i - 1] + " ";
						}

						Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + args[1] + " :" + result.substring(0, result.lastIndexOf(' ')));
					}
				}
				else
					Utilities.chanMsg(event, L10N.getString("kick.cannotKick"));
			}
			else
			{
				switch(args.length)
				{				
					case 1:
						throw new IncorrectCommandExecutionException(getAlias());
					case 2:
						throw new IncorrectCommandExecutionException(getAlias());
					default:
						Utilities.chanMsg(event, L10N.getString("kick.fail") + " - " + args.length);
				}
			}
		}
		else
			Utilities.respond(event, L10N.getString("kick.identify"), true);
	}

	@Override
	public String getAlias()
	{
		return "kick";
	}

	@Override
	public String getSyntax()
	{
		return "-kick <" + L10N.getString("kick.help.user") + "> <" + L10N.getString("kick.help.reason") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-kick <" + L10N.getString("kick.help.user") + "> <" + L10N.getString("kick.help.reason") + "> || " + L10N.getString("kick.explanation")};
	}
	
	@Override
	public String getNotes()
	{
		return L10N.getString("notes.onlyVoiceOp");
	}
}
