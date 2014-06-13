package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.core.Core;
import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Leave implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception
	{
		if(Utilities.validUser(event))
		{
			String[] args = Utilities.toArgs(event.getMessage());	

			if(args.length == 1)
				Core.bot.sendRaw().rawLine("PART " + event.getChannel().getName() + " :My master told me that I can't be here anymore :C");
			else if(args.length == 2)
			{
				String[] chans = Utilities.getJoinedChannels();
				boolean joined = false;

				for(String s : chans)
				{
					if(s != null)
					{
						if(s.equalsIgnoreCase(args[1]))
						{
							joined = true;
							break;
						}
					}
				}

				if(joined)
				{
					Utilities.chanMsg(event, "I will leave the channel " + Colors.BOLD + args[1] + Colors.BOLD + " now.");
					Core.bot.sendRaw().rawLine("PART " + args[1] + " :My master told me that I can't be here anymore :C");
				}
				else
					Utilities.chanMsg(event, "I'm not in that channel.");
			}
			else
				Utilities.respond(event, "I don't know what you did, but you just need to specify a channel, nothing else...", true);
		}
		else
			Utilities.sorry(event);
	}

	@Override
	public String getAlias()
	{
		return "leave";
	}
}
