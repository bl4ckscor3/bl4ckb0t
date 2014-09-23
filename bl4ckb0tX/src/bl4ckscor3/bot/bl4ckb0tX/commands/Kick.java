package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Kick implements ICommand<MessageEvent>
{	
	@Override
	public void exe(MessageEvent event) throws IOException, IrcException 
	{
		if(event.getUser().isVerified())
		{
			boolean allowed = false;
			boolean found = false;
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/0flrfzw3ljmw3u2/allowedUsers.txt").openStream()));
			String[] allowedUsers = reader.readLine().split(",");
				
			String[] args = Utilities.toArgs(event.getMessage());
			
			for(String allowedUser : allowedUsers)
			{
				if(event.getUser().getNick().equals(allowedUser))
				{	
					allowed = true;
					break;
				}
			}

			for(String allowedUser : Utilities.getValidUsers())
			{
				if(event.getUser().getNick().equalsIgnoreCase(allowedUser))
				{
					allowed = true;
					break;
				}
			}

			if(!allowed)
			{
				Utilities.chanMsg(event, "Sorry, " + event.getUser().getNick() + ", you're not authorized to kick people from this channel.");
				return;
			}

			if(args.length >= 3)
			{
				for(String userNotToKick : Utilities.getValidUsers())
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
						event.getChannel().send().action("kicks himself");
						Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + args[1] + " :I'm said now :(");
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
					Utilities.chanMsg(event, "This user cannot be kicked.");
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

	@Override
	public String getSyntax()
	{
		return "-kick <user> <reason>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-kick <user> <reason> || Kicks the user from the channel with the given reason."};
	}
	
	@Override
	public String getNotes()
	{
		return "Only useable by voiced/OPs.";
	}
}