package bl4ckscor3.bot.bl4ckb0tX.core;

import java.util.Timer;
import java.util.TimerTask;

public class Update
{
	public static void exe()
	{
		final Timer timer = new Timer();

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
				else
					return;
			}
		}, 5000, 600000);//5000 = first delay | 60000 = repeat after
	}
}
