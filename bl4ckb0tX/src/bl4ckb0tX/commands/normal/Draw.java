package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

@SuppressWarnings("rawtypes")
public class Draw
{
	private static String[] drawings =
		{
		"smiley",
		"mario",
		"batman"
		};

	public static void exe(MessageEvent event)
	{
		try
		{
			String[] seperate = Stuffz.getMessage(event).split(" ");

			switch(seperate[1].toLowerCase())
			{
				case "smiley":
					smiley(event);
					break;
				case "mario":
					mario(event);
					break;
				case "batman":
					batman(event);
					break;
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			Stuffz.chanMsg(event, getAvailableDrawings());
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

	private static void batman(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Stuffz.chanMsg(event, "0,0xxxxxxxxxxxxxx1,1xxxxxxxxxxxxxx0,0xxxxxxxxxxxxxx");
		Stuffz.chanMsg(event, "0,0xxxxxxxxxx1,1xxxx8,8xxxxxxxxxxxxxx1,1xxxx0,0xxxxxxxxxx");
		Stuffz.chanMsg(event, "0,0xxxxxxxx1,1xx8,8xxxxxxxxxxxxxxxxxxxxxx1,1xx0,0xxxxxxxx");
		Stuffz.chanMsg(event, "0,0xxxxxx1,1xx8,8xxxx1,1xx8,8xxxx1,1xx8,8xx1,1xx8,8xxxx1,1xx8,8xxxx1,1xx0,0xxxxxx");
		Stuffz.chanMsg(event, "0,0xxxx1,1xx8,8xxxx1,1xx8,8xxxxxx1,1xxxxxx8,8xxxxxx1,1xx8,8xxxx1,1xx0,0xxxx");
		Stuffz.chanMsg(event, "0,0xx1,1xx8,8xxxx1,1xxxx8,8xxxxxx1,1xxxxxx8,8xxxxxx1,1xxxx8,8xxxx1,1xx0,0xx");
		Stuffz.chanMsg(event, "0,0xx1,1xx8,8xx1,1xxxxxxxx8,8xx1,1xxxxxxxxxx8,8xx1,1xxxxxxxx8,8xx1,1xx0,0xx");
		Stuffz.chanMsg(event, "0,0xx1,1xx8,8xx1,1xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx8,8xx1,1xx0,0xx");
		Stuffz.chanMsg(event, "0,0xx1,1xx8,8xx1,1xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx8,8xx1,1xx0,0xx");
		Stuffz.chanMsg(event, "0,0xx1,1xx8,8xx1,1xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx8,8xx1,1xx0,0xx");
		Stuffz.chanMsg(event, "0,0xx1,1xx8,8xx1,1xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx8,8xx1,1xx0,0xx");
		Stuffz.chanMsg(event, "0,0xx1,1xx8,8xxxx1,1xxxx8,8xx1,1xx8,8xx1,1xxxxxx8,8xx1,1xx8,8xx1,1xxxx8,8xxxx1,1xx0,0xx");
		Stuffz.chanMsg(event, "0,0xxxx1,1xx8,8xxxx1,1xx8,8xxxxxxxx1,1xx8,8xxxxxxxx1,1xx8,8xxxx1,1xx0,0xxxx");
		Stuffz.chanMsg(event, "0,0xxxxxx1,1xx8,8xxxx1,1xx8,8xxxxxx1,1xx8,8xxxxxx1,1xx8,8xxxx1,1xx0,0xxxxxx");
		Stuffz.chanMsg(event, "0,0xxxxxxxx1,1xx8,8xxxxxxxxxxxxxxxxxxxxxx1,1xx0,0xxxxxxxx");
		Stuffz.chanMsg(event, "0,0xxxxxxxxxx1,1xxxx8,8xxxxxxxxxxxxxx1,1xxxx0,0xxxxxxxxxx");
		Stuffz.chanMsg(event, "0,0xxxxxxxxxxxxxx1,1xxxxxxxxxxxxxx0,0xxxxxxxxxxxxxx");
		Stuffz.chanMsg(event, "0,0xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	}

	private static String getAvailableDrawings()
	{
		StringBuilder output = new StringBuilder();

		output.append("Available drawings: ");

		for(int i = 0; i < drawings.length; i++)
		{
			output.append("'" + drawings[i] + "' | ");
		}

		output.delete(output.length() - 3, output.length() - 1);
		return output.toString();
	}
}
