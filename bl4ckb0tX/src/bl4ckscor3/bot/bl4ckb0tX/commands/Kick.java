package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Kick implements Command<MessageEvent>
{	
	@Override
	public void exe(MessageEvent event)
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
				};
			String[] args = Utilities.toArgs(event.getMessage());

			if(args.length >= 3)
			{
				if(!(event.getUser().getNick().equalsIgnoreCase(args[1])))
				{
					for(String allowedUser : allowedUsers)
					{
						if(event.getUser().getNick().equals(allowedUser))
						{	
							allowed = true;
							break;
						}
					}

					if(!allowed)
						Utilities.chanMsg(event, "Sorry, " + event.getUser().getNick() + ", you're not authorized to kick people from this channel.");
					else
					{
						for(String userNotToKick : usersNotToKick)
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
								OutputIRC irc = new OutputIRC(Core.bot);

								event.getChannel().send().action("kicks himself");
								Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + args[1] + " :I'm said now :(");
								irc.quitServer("My master sent me to sleep!");
								Core.main2();
							}
							else
							{
								StringBuilder builder = new StringBuilder();

								for(int i = 3; i <= args.length; i++)
								{
									builder.append(args[i - 1] + " ");
								}
								
								builder.replace(builder.length() - 1, builder.length(), "");
								Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + args[1] + " :" + builder.toString());
							}
						}
						else
						{
							Utilities.chanMsg(event, "This user cannot be kicked.");
						}
					}
				}
				else
				{
					StringBuilder builder = new StringBuilder();

					for(int i = 3; i <= args.length; i++)
					{
						builder.append(args[i - 1] + " ");
					}

					builder.replace(builder.length() - 1, builder.length(), "");

					Utilities.chanMsg(event, args[1] + " was dumb and kicked himself...");
					Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + args[1] + " :" + builder.toString());
				}
			}
			else
			{
				switch(args.length)
				{				
					case 1:
						Utilities.respond(event, "please tell me which user I should kick and why. Example: -kick " + event.getUser().getNick() + " Kicked", true);
						break;
					case 2:
						Utilities.respond(event, "please tell me why I should kick " + args[1] + ". Example: -kick " + args[1] + " Kicked", true);
						break;
					default:
						Utilities.chanMsg(event, "Euuuuh: " + args.length);
				}
			}
		}
		else
			Utilities.respond(event, "you need to be identified to be able to kick someone.", true);
	}
	
	@Override
	public String getAlias()
	{
		return "kick";
	}
}