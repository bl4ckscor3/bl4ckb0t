package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Kick
{	
	public static void exe(MessageEvent event)
	{
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

		for(int i = 0; i < allowedUsers.length; i++)
		{
			if(Stuffz.getNick(event).equals(allowedUsers[i]))
			{
				Stuffz.sorry(event);
			}
		}
	}
}
