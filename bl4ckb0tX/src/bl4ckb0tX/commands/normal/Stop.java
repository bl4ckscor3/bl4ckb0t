package bl4ckb0tX.commands.normal;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import bl4ckb0tX.core.Core;
import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Stop
{
	public static void exe(MessageEvent event) throws IOException, IrcException
	{
		if(Stuffz.getNick(event).equals("bl4ckscor3") || Stuffz.getNick(event).equals("bl4ckgon3") || Stuffz.getNick(event).equals("bl4ckweb") || Stuffz.getNick(event).equals("bl4ckdro1d"))
		{
			boolean done = false;
			OutputIRC irc = new OutputIRC(Core.bot);
			
			if(Stuffz.getMessage(event).equalsIgnoreCase(event.getBot().getNick() + ", sleep!"))
			{
				Stuffz.chanMsg(event, "k");
				irc.quitServer("My master sent me to sleep!");
				done = true;
			}

			if(!done)
			{
				String[] reboot = event.getMessage().split(" ", 2); //getting everything after "-stop " | [0] is "-stop" for 3 chars in general

				try
				{
					switch(reboot[1])
					{
						case "yes":
							Stuffz.chanMsg(event, "I will reboot, sir");
							irc.quitServer("My master sent me to sleep!");
							Core.main2(); //making another PircBotX
							break;

						case "no":
							Stuffz.chanMsg(event, "You wished that I don't reboot. Do you still like me?");
							irc.quitServer("My master sent me to sleep!");
							break;

						default:
							Stuffz.userMsg(event, "Should I reboot? I cannot disconnect if I don't know that :(");
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					Stuffz.userMsg(event, "Should I reboot? I cannot disconnect if I don't know that :(");
				}
			}
		}
		else
		{
			Stuffz.sorry(event);
		}
	}
}
