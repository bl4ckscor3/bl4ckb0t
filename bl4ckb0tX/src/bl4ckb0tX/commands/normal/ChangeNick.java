package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckb0tX.core.Core;
import bl4ckb0tX.util.Utilities;

public class ChangeNick
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		String[] args = Utilities.toArgs(event.getMessage());
		
		if(args.length == 2)
		{
			if(Utilities.validUser(event))
			{
				OutputIRC irc = new OutputIRC(Core.bot);
			
				irc.changeNick(args[1]);
			}
			else
				Utilities.sorry(event);
		}
		else
			Utilities.respond(event, "please just put in a name and nothing else. Example: -changenick newName", true);
	}
}
