package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Kick implements ICommand<MessageEvent>
{	
	@Override
	public void exe(MessageEvent event) throws IOException, IrcException 
	{
		if(event.getUser().isVerified())
		{
			boolean allowed = false;
			boolean found = false;
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://www.dropbox.com/s/0flrfzw3ljmw3u2/allowedUsers.txt?dl=1").openStream()));
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
				Utilities.chanMsg(event, "Sorry, " + event.getUser().getNick() + ", " + L10N.strings.getString("kick.notAuthorized"));
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
						event.getChannel().send().action(L10N.strings.getString("kick.self"));
						Core.bot.sendRaw().rawLine("KICK " + event.getChannel().getName() + " " + args[1] + " :" + L10N.strings.getString("kick.self.reason"));
						
						if(L10N.chanLangs.containsKey(event.getChannel().getName()))
							L10N.chanLangs.remove(event.getChannel().getName());
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
					Utilities.chanMsg(event, L10N.strings.getString("kick.cannotKick"));
			}
			else
			{
				switch(args.length)
				{				
					case 1:
						Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
						break;
					case 2:
						Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
						break;
					default:
						Utilities.chanMsg(event, L10N.strings.getString("kick.fail") + " - " + args.length);
				}
			}
		}
		else
			Utilities.respond(event, L10N.strings.getString("kick.identify"), true);
	}

	@Override
	public String getAlias()
	{
		return "kick";
	}

	@Override
	public String getSyntax()
	{
		return "-kick <" + L10N.strings.getString("kick.help.user") + "> <" + L10N.strings.getString("kick.help.reason") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-kick <" + L10N.strings.getString("kick.help.user") + "> <" + L10N.strings.getString("kick.help.reason") + "> || " + L10N.strings.getString("kick.explanation")};
	}
	
	@Override
	public String getNotes()
	{
		return L10N.strings.getString("notes.onlyVoiceOp");
	}
}
