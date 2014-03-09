package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckb0tX.core.Core;
import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Kick
{	
	public static void exe(MessageEvent event)
	{
		OutputIRC irc = new OutputIRC(Core.bot);
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
		String userToKick = Stuffz.getMessage(event).substring(2);
		
		for(int i = 0; i < allowedUsers.length; i++)
		{
			if(Stuffz.getNick(event).equals(allowedUsers[i]))
			{
				event.getChannel().send().action("kicks " + userToKick);
			}
		}
	}
}
