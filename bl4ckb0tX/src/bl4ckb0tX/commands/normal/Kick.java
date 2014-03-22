package bl4ckb0tX.commands.normal;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckb0tX.core.Core;
import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Kick
{	
	public static void exe(MessageEvent event) throws IOException, IrcException
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
					"TehKitti",
					"Maunz",
					"StealthBravo",
					"FishFish0001"
				};
			String[] usersNotToKick =
				{
					"bl4ckscor3",
					"bl4ckgon3",
					"bl4ckweb",
					"bl4ckdro1d",
					"Darkhax",
					"Geforce132",
					"Geforce132|Away",
					"TehKitti",
					"Maunz",
					"StealthBravo",
					"FishFish0001"
				};
			//-kick bl4ckscor3 cause i can
			String splitUserReasonFromCommand = Stuffz.getMessage(event).substring(5);// bl4ckscor3 cause i can
			String correctSplitFromCommand = splitUserReasonFromCommand.substring(1);//bl4ckscor3 cause i can
			String[] splitUserFromReason = correctSplitFromCommand.split(" ");//bl4ckscor3
			String splitReasonFromUser = correctSplitFromCommand.substring(splitUserFromReason[0].length());//cause i can
			String user = splitUserFromReason[0];
			String reason = splitReasonFromUser;

			if(!(Stuffz.getNick(event).equalsIgnoreCase(user)))
			{
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
					for(int k = 0; k < usersNotToKick.length; k++)
					{
						if(user.equalsIgnoreCase(usersNotToKick[k]))
						{
							found = true;
						}
					}

					if(!found)
					{
						if(user.equalsIgnoreCase(Core.bot.getNick()))
						{
							OutputIRC irc = new OutputIRC(Core.bot);
							
							event.getChannel().send().action("kicks himself");
							Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + user + " :I'm said now :(");
							irc.quitServer("My master sent me to sleep!");
							Core.main2();
						}
						else
						{
							event.getChannel().send().action("kicks " + user);
							Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + user + " :" + reason.substring(1));
						}
					}
					else
					{
						Stuffz.chanMsg(event, "This user cannot be kicked.");
					}
				}
			}
			else
			{
				Stuffz.chanMsg(event, user + " was dumb and kicked himself...");
				Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + user + " :I'm dumb.");
			}
		}
		else
		{
			Stuffz.respond(event, "you need to be identified to be able to kick someone.", true);
		}
	}
}
