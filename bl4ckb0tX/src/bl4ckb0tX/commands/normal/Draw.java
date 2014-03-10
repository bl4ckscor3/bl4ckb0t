package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Draw
{
	public static void exe(MessageEvent event)
	{
		String[] seperate = Stuffz.getMessage(event).split(" ");

		switch(seperate[1].toLowerCase())
		{
			case "help":
				Stuffz.chanMsg(event, "Available drawings: 'smiley' | 'mario'");
				break;
			case "smiley":
				smiley(event);
				break;
			case "mario":
				mario(event);
				break;
			default:
				Stuffz.chanMsg(event, "Available drawings: 'smiley' | 'mario'");
		}
	}

	private static void smiley(MessageEvent event)
	{
		Stuffz.chanMsg(event, "1,1XXXXXXXXXX1,1XXXXXXXXXX1,1XXXXXXXXXX");
		Stuffz.chanMsg(event, "1,1XXXXXX1,1XXXX8,8XXXXXXXXXX1,1XXXX1,1XXXXXX");
		Stuffz.chanMsg(event, "1,1XXXX1,1XX8,8XXXXXXXXXXXXXXXXXX1,1XX1,1XXXX");
		Stuffz.chanMsg(event, "1,1XX1,1XX8,8XXXX1,1XXXX8,8XXXXXX1,1XXXX8,8XXXX1,1XX1,1XX");
		Stuffz.chanMsg(event, "1,1XX1,1XX8,8XX1,1XX8,8XXXX1,1XX8,8XX1,1XX8,8XXXX1,1XX8,8XX1,1XX1,1XX");
		Stuffz.chanMsg(event, "1,1XX8,8XXXXXXXXXXXXXXXXXXXXXXXXXX1,1XX");
		Stuffz.chanMsg(event, "1,1XX8,8XX1,1XXXXXXXXXXXXXXXXXXXXXX8,8XX1,1XX");
		Stuffz.chanMsg(event, "1,1XX8,8XX1,1XX0,0XX15,15XX1,1XX0,0XXXX15,15XX1,1XX0,0XX15,15XX1,1XX8,8XX1,1XX");
		Stuffz.chanMsg(event, "1,1XX8,8XX1,1XX0,0XX15,15XX1,1XX0,0XXXX15,15XX1,1XX0,0XX15,15XX1,1XX8,8XX1,1XX");
		Stuffz.chanMsg(event, "1,1XX8,8XX1,1XX0,0XX15,15XX1,1XX0,0XXXX15,15XX1,1XX0,0XX15,15XX1,1XX8,8XX1,1XX");
		Stuffz.chanMsg(event, "1,1XX1,1XX8,8XX1,1XX15,15XX1,1XX0,0XXXX15,15XX1,1XX0,0XX1,1XX8,8XX1,1XX1,1XX");
		Stuffz.chanMsg(event, "1,1XX1,1XX8,8XXXX1,1XXXX0,0XXXX15,15XX1,1XXXX8,8XXXX1,1XX1,1XX");
		Stuffz.chanMsg(event, "1,1XXXX1,1XX8,8XXXX1,1XXXXXXXXXX8,8XXXX1,1XX1,1XXXX");
		Stuffz.chanMsg(event, "1,1XXXXXX1,1XXXX8,8XXXXXXXXXX1,1XXXX1,1XXXXXX");
		Stuffz.chanMsg(event, "1,1XXXXXXXXXX1,1XXXXXXXXXX1,1XXXXXXXXXX");
	}

	private static void mario(MessageEvent event)
	{
		Stuffz.chanMsg(event, "1,1xxxxxxxxxxxxxxxx");
		Stuffz.chanMsg(event, "1,1xxxxx4,4xxxxx1,1xxxxxx");
		Stuffz.chanMsg(event, "1,1xxxx4,4xxxxxxxxx1,1xxx");
		Stuffz.chanMsg(event, "1,1xxxx5,5xxx7,7xx5,5x7,7x1,1xxxxx");
		Stuffz.chanMsg(event, "1,1xxx5,5x7,7x5,5x7,7xxx5,5x7,7xxx1,1xxx");
		Stuffz.chanMsg(event, "1,1xxx5,5x7,7x5,5xx7,7xxx5,5x7,7xxx1,1xx");
		Stuffz.chanMsg(event, "1,1xxx5,5xx7,7xxxx5,5xxxx1,1xxx");
		Stuffz.chanMsg(event, "1,1xxxxx7,7xxxxxxx1,1xxxx");
		Stuffz.chanMsg(event, "1,1xxxx5,5xx4,4x5,5xxx1,1xxxxxx");
		Stuffz.chanMsg(event, "1,1xxx5,5xxx4,4x5,5xx4,4x5,5xxx1,1xxx");
		Stuffz.chanMsg(event, "1,1xx5,5xxxx4,4xxxx5,5xxxx1,1xx");
		Stuffz.chanMsg(event, "1,1xx7,7xx5,5x4,4x7,7x4,4xx7,7x4,4x5,5x7,7xx1,1xx");
		Stuffz.chanMsg(event, "1,1xx7,7xxx4,4xxxxxx7,7xxx1,1xx");
		Stuffz.chanMsg(event, "1,1xx7,7xx4,4xxxxxxxx7,7xx1,1xx");
		Stuffz.chanMsg(event, "1,1xxxx4,4xxx1,1xx4,4xxx1,1xxxx");
		Stuffz.chanMsg(event, "1,1xxx5,5xxx1,1xxxx5,5xxx1,1xxx");
		Stuffz.chanMsg(event, "1,1xx5,5xxxx1,1xxxx5,5xxxx1,1xx");
		Stuffz.chanMsg(event, "1,1xxxxxxxxxxxxxxxx");
	}
}
