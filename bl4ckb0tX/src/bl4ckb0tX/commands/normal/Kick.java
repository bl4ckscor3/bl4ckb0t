package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.core.Core;
import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Kick
{	
	public static void exe(MessageEvent event)
	{
		if(event.getUser().isVerified())
		{
			boolean allowed = false;
			boolean found = false;
			String[] allowedUsers = 
				{
					"bl4ckscor3",
					"bl4ckgon3",
					"bl4ckweb",
					"bl4ckdro1d",
					"Darkhax",
					"Geforce132",
					"Geforce132|Away",
					"TehKitti"
				};
			//-kick bl4ckscor3 cause i can
			String splitUserReasonFromCommand = Stuffz.getMessage(event).substring(5);// bl4ckscor3 cause i can
			String correctSplitFromCommand = splitUserReasonFromCommand.substring(1);//bl4ckscor3 cause i can
			String[] splitUserFromReason = correctSplitFromCommand.split(" ");//bl4ckscor3
			String splitReasonFromUser = correctSplitFromCommand.substring(splitUserFromReason[0].length());//cause i can
			String user = splitUserFromReason[0];
			String reason = splitReasonFromUser;

			for(int i = 0; i < allowedUsers.length; i++)
			{
				if(Stuffz.getNick(event).equals(allowedUsers[i]))
				{	
					allowed = true;
					break;
				}
			}

			if(!allowed)
			{
				Stuffz.chanMsg(event, "Sorry, " + Stuffz.getNick(event) + ", you're not authorized to kick people from this channel.");
			}
			else
			{
				for(int k = 0; k < allowedUsers.length; k++)
				{
					if(user.equalsIgnoreCase(allowedUsers[k]))
					{
						found = true;
					}
				}

				if(!found)
				{
					event.getChannel().send().action("kicks " + user);
					Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + user + " :" + reason.substring(1));
				}
				else
				{
					Stuffz.chanMsg(event, "This user cannot be kicked.");
				}
			}
		}
		else
		{
			Stuffz.respond(event, "you need to be identified to be able to kick someone.");
		}
	}
}
