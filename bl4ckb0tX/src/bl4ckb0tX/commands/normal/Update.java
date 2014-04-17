package bl4ckb0tX.commands.normal;

import java.util.Timer;
import java.util.TimerTask;

import bl4ckb0tX.core.Core;
import bl4ckb0tX.core.Listener;

public class Update
{
	public static void exe()
	{
		final Timer timer = new Timer();

		Listener.isCounting = true;

		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				if(Listener.isCounting)
				{
					System.out.println("k");
					Core.bot.sendIRC().message("TehKitti", "Please update me!");
				}
			}
		}, 5000, 600000);
	}
}
