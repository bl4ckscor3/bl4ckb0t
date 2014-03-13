package bl4ckb0tX.commands.normal;

import java.util.Timer;
import java.util.TimerTask;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.core.Listener;
import bl4ckb0tX.util.Stuffz;

public class Fun
{
	@SuppressWarnings("rawtypes")
	public static void exe(final MessageEvent event)
	{
		try
		{
			StringBuilder builder = new StringBuilder();
			String time = Stuffz.seperate(event);;
			
			builder.append(time);
			builder.append("000");
			Listener.fun = true;
			Stuffz.chanMsg(event, "Activated the fun for " + time + "seconds!");

			new Timer().schedule(new TimerTask() 
			{          
				@Override
				public void run() 
				{
					Listener.fun = false;
					Stuffz.chanMsg(event, "Deactivated the fun >.>");
				}
			}, Integer.parseInt(builder.toString()));
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			Stuffz.chanMsg(event, "I need a number in seconds, please!");
		}
	}
}
