package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Draw implements Command<MessageEvent>
{
	private String[] drawings =
		{
			"mario",
			"batman",
			"bullshit",
			"bunny",
			"cat",
			"chick",
			"fly",
			"invaders",
			"megaman",
			"phone",
			"superman",
			"stormtrooper",
		};

	@Override
	public void exe(MessageEvent event)
	{
		String[] toArgs = Utilities.toArgs(event.getMessage());

		if(toArgs.length == 2)
		{
			switch(toArgs[1].toLowerCase())
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
				case "bullshit":
					bullshit(event);
					break;
				case "bunny":
					bunny(event);
					break;
				case "cat":
					cat(event);
					break;
				case "chick":
					chick(event);
					break;
				case "fly":
					fly(event);
					break;
				case "invaders":
					invaders(event);
					break;
				case "megaman":
					megaman(event);
					break;
				case "phone":
					phone(event);
					break;
				case "superman":
					superman(event);
					break;
				case "stormtrooper":
					stormtrooper(event);
					break;
				default:
					sendAvailableDrawings(event);
			}
		}
		else
			sendAvailableDrawings(event);
	}

	private void smiley(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "1,1          1,1          1,1          ");
		Utilities.pm(event.getUser().getNick(), "1,1      1,1    8,8          1,1    1,1      ");
		Utilities.pm(event.getUser().getNick(), "1,1    1,1  8,8                  1,1  1,1    ");
		Utilities.pm(event.getUser().getNick(), "1,1  1,1  8,8    1,1    8,8      1,1    8,8    1,1  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  1,1  8,8  1,1  8,8    1,1  8,8  1,1  8,8    1,1  8,8  1,1  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  8,8                          1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  8,8  1,1                      8,8  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  8,8  1,1  0,0  15,15  1,1  0,0    15,15  1,1  0,0  15,15  1,1  8,8  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  8,8  1,1  0,0  15,15  1,1  0,0    15,15  1,1  0,0  15,15  1,1  8,8  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  8,8  1,1  0,0  15,15  1,1  0,0    15,15  1,1  0,0  15,15  1,1  8,8  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  1,1  8,8  1,1  15,15  1,1  0,0    15,15  1,1  0,0  1,1  8,8  1,1  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  1,1  8,8    1,1    0,0    15,15  1,1    8,8    1,1  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1    1,1  8,8    1,1          8,8    1,1  1,1    ");
		Utilities.pm(event.getUser().getNick(), "1,1      1,1    8,8          1,1    1,1      ");
		Utilities.pm(event.getUser().getNick(), "1,1          1,1          1,1          ");
	}

	private void mario(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "1,1                ");
		Utilities.pm(event.getUser().getNick(), "1,1     4,4     1,1      ");
		Utilities.pm(event.getUser().getNick(), "1,1    4,4         1,1   ");
		Utilities.pm(event.getUser().getNick(), "1,1    5,5   7,7  5,5 7,7 1,1     ");
		Utilities.pm(event.getUser().getNick(), "1,1   5,5 7,7 5,5 7,7   5,5 7,7   1,1   ");
		Utilities.pm(event.getUser().getNick(), "1,1   5,5 7,7 5,5  7,7   5,5 7,7   1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1   5,5  7,7    5,5    1,1   ");
		Utilities.pm(event.getUser().getNick(), "1,1     7,7       1,1    ");
		Utilities.pm(event.getUser().getNick(), "1,1    5,5  4,4 5,5   1,1      ");
		Utilities.pm(event.getUser().getNick(), "1,1   5,5   4,4 5,5  4,4 5,5   1,1   ");
		Utilities.pm(event.getUser().getNick(), "1,1  5,5    4,4    5,5    1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  7,7  5,5 4,4 7,7 4,4  7,7 4,4 5,5 7,7  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  7,7   4,4      7,7   1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1  7,7  4,4        7,7  1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1    4,4   1,1  4,4   1,1    ");
		Utilities.pm(event.getUser().getNick(), "1,1   5,5   1,1    5,5   1,1   ");
		Utilities.pm(event.getUser().getNick(), "1,1  5,5    1,1    5,5    1,1  ");
		Utilities.pm(event.getUser().getNick(), "1,1                ");
	}

	private void batman(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,0                                          ");
		Utilities.pm(event.getUser().getNick(), "0,0              1,1              0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0          1,1    8,8              1,1    0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0        1,1  8,8                      1,1  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0      1,1  8,8    1,1  8,8    1,1  8,8  1,1  8,8    1,1  8,8    1,1  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0    1,1  8,8    1,1  8,8      1,1      8,8      1,1  8,8    1,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0  1,1  8,8    1,1    8,8      1,1      8,8      1,1    8,8    1,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  1,1  8,8  1,1        8,8  1,1          8,8  1,1        8,8  1,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  1,1  8,8  1,1                              8,8  1,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  1,1  8,8  1,1                              8,8  1,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  1,1  8,8  1,1                              8,8  1,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  1,1  8,8  1,1                              8,8  1,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  1,1  8,8    1,1    8,8  1,1  8,8  1,1      8,8  1,1  8,8  1,1    8,8    1,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0    1,1  8,8    1,1  8,8        1,1  8,8        1,1  8,8    1,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0      1,1  8,8    1,1  8,8      1,1  8,8      1,1  8,8    1,1  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0        1,1  8,8                      1,1  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0          1,1    8,8              1,1    0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0              1,1              0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0                                          ");
	}

	private void bullshit(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,0                                                  ");
		Utilities.pm(event.getUser().getNick(), "0,0  14,14  0,0              14,14  0,0                              ");
		Utilities.pm(event.getUser().getNick(), "0,0  14,14  0,0      1,1        14,14  1,1                    0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,14    0,1          0,14    0,0    0,14                0,1      0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,14      0,0    0,1  0,0      0,14                0,1  0,5  0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,14  0,1  0,0  0,1  0,0  0,1  0,0      0,14                0,1  0,5  0,0  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,0  0,1  0,0  0,1  0,0  0,1  0,0        0,14            0,0  0,1  0,5  0,0  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1              0,0          0,14      0,0      0,1  0,0    0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,13            0,1    0,0                0,14    0,1  0,0    0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,13              0,1  0,0    0,14        0,0    0,14    0,1  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,13  0,5  0,13      0,5  0,13  0,1  0,0  0,14            0,0      0,1  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,13  0,5  0,13  0,5  0,13    0,1  0,14                0,0  0,1    0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1                                  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,0      0,1  0,0      0,13          0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,0      0,1  0,0        0,13      0,0  0,1  0,0    0,5  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,0      0,1  0,0          0,1  0,0    0,1  0,0  0,5      0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,0      0,1  0,0          0,1  0,0    0,1  0,5          0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,3                                                ");
		Utilities.pm(event.getUser().getNick(), "0,0                                                  ");
	}

	private void bunny(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,0                                              ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1      0,0                    0,1      0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,15  0,0    0,1  0,0                0,1  0,0    0,15  0,1  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,15  0,13    0,0  0,1  0,0            0,1  0,0  0,13    0,15  0,1  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,15    0,13  0,0  0,1  0,0            0,1  0,0  0,13  0,15    0,1  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1  0,15  0,13    0,0  0,1  0,0        0,1  0,0  0,13    0,15  0,1  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0        0,1  0,15  0,13  0,0  0,1  0,0        0,1  0,0  0,13  0,15  0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,15  0,13  0,0  0,1        0,0  0,13  0,15  0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1      0,15        0,1      0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1  0,15  0,0            0,15  0,1  0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,15  0,0                0,15  0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,15  0,0                0,15  0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,15  0,0  0,1  0,0        0,1  0,0  0,15  0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,15  0,0  0,1  0,0        0,1  0,0  0,15  0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,15  0,13    0,0        0,13    0,15  0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0        0,1      0,15                0,1      0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,1        0,0    0,1                    0,0    0,1          ");
		Utilities.pm(event.getUser().getNick(), "0,3      0,1  0,15      0,1  0,3            0,1  0,15      0,1  0,3        ");
		Utilities.pm(event.getUser().getNick(), "0,3        0,1      0,3                0,1      0,3          ");
		Utilities.pm(event.getUser().getNick(), "0,3                                              ");
		Utilities.pm(event.getUser().getNick(), "0,3                                              ");
	}

	private void cat(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,0                                          ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1    0,0                      0,1    0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,7    0,1  0,0                  0,1  0,7    0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,7      0,1  0,0              0,1  0,7      0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,7  0,5  0,7    0,1  0,0          0,1  0,7    0,5  0,7  0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,7  0,5    0,7    0,1          0,7    0,5    0,7  0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,7  0,5    0,7    0,5  0,7  0,5  0,7  0,5  0,7    0,5    0,7  0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,7            0,5  0,7  0,5  0,7  0,5  0,7            0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,5    0,7                          0,5    0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,7      0,1    0,0  0,7          0,1    0,0  0,7      0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,5    0,7  0,1      0,7  0,1  0,13  0,1  0,7  0,1      0,7  0,5    0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,7      0,1      0,7  0,1      0,7  0,1      0,7      0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,5    0,7                          0,5    0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,7          0,1  0,7    0,1  0,7    0,1  0,7          0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,7        0,1              0,7        0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1  0,7                          0,1  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0        0,1                          0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0                                          ");
	}

	private void chick(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,0                                                  ");
		Utilities.pm(event.getUser().getNick(), "0,0                0,1                  0,0                ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,1  0,8                  0,1  0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1  0,8                      0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8                          0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8                          0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8    0,0  0,1  0,8          0,0  0,1  0,8    0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8    0,1    0,8          0,1    0,8    0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8    0,1    0,8          0,1    0,8    0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8          0,1      0,8          0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0        0,1    0,8        0,1  0,7      0,1  0,8        0,1    0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1  0,8              0,1      0,8              0,1  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,8                                      0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,8    0,1  0,8          0,0          0,8        0,1    0,8    0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1    0,0  0,1  0,8      0,0              0,8      0,1  0,0  0,1    0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8      0,0              0,8      0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8      0,0              0,8      0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8          0,0      0,8          0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,8  0,1      0,8  0,1      0,8  0,1      0,8  0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1  0,7      0,1  0,0      0,1  0,7      0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,1      0,0          0,1      0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0                                                  ");

	}

	private void fly(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,0                                                            ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,14      0,0                                      0,14        0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,14  0,0      0,14    0,0          0,1            0,0          0,14  0,0        0,14  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,14  0,0          0,14  0,0    0,1      0,14    0,15      0,1    0,0    0,14  0,0          0,14  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,14  0,15  0,0        0,14  0,0  0,1      0,14    0,15            0,1  0,0  0,14  0,0        0,15  0,14  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,14  0,15    0,0      0,1        0,14        0,15          0,1  0,15  0,0    0,15    0,14  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,14    0,15      0,1          0,14        0,15      0,14  0,1  0,15      0,14    0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,14    0,1                  0,14            0,1  0,14    0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,1    0,0        0,1        0,0        0,14  0,1  0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,1  0,0            0,1    0,0            0,1  0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,1  0,0    0,1  0,15  0,0    0,1    0,0    0,1  0,15  0,0    0,1  0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,1  0,0    0,1    0,0    0,1    0,0    0,1    0,0    0,1  0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1    0,0            0,1    0,0            0,1    0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1  0,0  0,1  0,0        0,1        0,0        0,1  0,0  0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1  0,0  0,1                            0,0  0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1    0,0    0,1                        0,0    0,1    0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0                  0,1          0,0    0,1          0,0                  ");
		Utilities.pm(event.getUser().getNick(), "0,0                  0,1  0,0    0,1            0,0    0,1  0,0                  ");
		Utilities.pm(event.getUser().getNick(), "0,0                0,1    0,0                    0,1    0,0                ");
		Utilities.pm(event.getUser().getNick(), "0,0                                                            ");

	}

	private void invaders(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,0                                                                                            ");
		Utilities.pm(event.getUser().getNick(), "0,0                                          0,1            0,0                                      ");
		Utilities.pm(event.getUser().getNick(), "0,0                                  0,1          0,5        0,1          0,0                              ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1      0,0        0,1      0,0      0,1      0,5      0,4        0,5      0,1      0,0        0,1            0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1  0,12  0,1  0,0        0,1  0,12  0,1  0,0      0,1  0,5    0,4                    0,5    0,1  0,0      0,1    0,3        0,1    0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1    0,12  0,1        0,12  0,1    0,0      0,1  0,5  0,4      0,1    0,4    0,1    0,4      0,5  0,1  0,0    0,1    0,3  0,9        0,3  0,1    0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1    0,12  0,11  0,12        0,11  0,12  0,1    0,0    0,1  0,5  0,4                        0,5  0,1  0,0  0,1    0,3  0,9            0,3  0,1    0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1    0,12  0,11  0,1  0,11        0,1  0,11  0,12  0,1    0,0  0,1          0,4    0,1    0,4    0,1          0,0  0,1  0,3  0,9  0,1  0,9        0,1  0,9  0,3  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1    0,11    0,1  0,11        0,1  0,11    0,1    0,0          0,1  0,4    0,1    0,4    0,1  0,0          0,1  0,3  0,9  0,1  0,9        0,1  0,9  0,3  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,12  0,11                    0,12  0,1  0,0  0,1        0,4    0,1  0,4    0,1  0,4    0,1        0,0  0,1  0,3  0,9                0,3  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,11  0,1  0,11                0,1  0,11  0,1  0,0  0,1  0,5  0,4    0,1                0,4    0,5  0,1  0,0  0,1      0,9  0,1        0,9  0,1      0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,11  0,1  0,11  0,1            0,11  0,1  0,11  0,1  0,0  0,1          0,0            0,1          0,0  0,1    0,9  0,1  0,9        0,1  0,9  0,1    0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1        0,11    0,1    0,11    0,1        0,0                                    0,1  0,3  0,1  0,9  0,1  0,9    0,1  0,9  0,1  0,3  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0        0,1      0,0    0,1      0,0                                            0,1  0,0  0,1  0,0  0,1    0,0  0,1  0,0  0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0                                                                                            ");
	}

	private void megaman(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,0                                              ");
		Utilities.pm(event.getUser().getNick(), "0,0                      0,1      0,0                  ");
		Utilities.pm(event.getUser().getNick(), "0,0                  0,1      0,11    0,1  0,0                ");
		Utilities.pm(event.getUser().getNick(), "0,0                0,1  0,12      0,1  0,11    0,1  0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,1  0,12          0,1        0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,1  0,12          0,1  0,11    0,12  0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1  0,11  0,12            0,1    0,12  0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1  0,11  0,12    0,15  0,0      0,12    0,0  0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1  0,11  0,12  0,15  0,0    0,1    0,15  0,1  0,0  0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,1  0,12  0,15  0,0    0,1    0,15  0,1  0,0  0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1    0,12  0,15    0,0      0,15  0,0    0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0        0,1    0,11    0,1  0,2  0,15  0,1        0,15  0,1      0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1  0,12  0,11        0,1  0,15          0,1  0,11    0,12  0,1  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1  0,12    0,11        0,1          0,11    0,12    0,1  0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,12      0,11  0,1  0,11              0,1  0,11  0,12      0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,12    0,1      0,11              0,1      0,12    0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,12      0,1    0,11              0,1    0,12      0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1  0,12      0,1    0,12              0,1    0,12      0,1  0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,1      0,0  0,1  0,12              0,1  0,0  0,1      0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,1  0,11    0,12        0,11      0,1  0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,1  0,12    0,11      0,1  0,11        0,12  0,1  0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0        0,1    0,12      0,11  0,1  0,0  0,1  0,11  0,12      0,1    0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1    0,12          0,1  0,0      0,1  0,12          0,1    0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,12              0,1  0,0      0,1  0,12              0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1                  0,0      0,1                  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0                                              ");
	}

	private void phone(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,0                            ");
		Utilities.pm(event.getUser().getNick(), "0,0                0,1      0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0                  0,1  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0                  0,1  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0                  0,1  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0                  0,1  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1                    0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10                    0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,0                0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,0  0,1            0,0  0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,0  0,1  0,14        0,1  0,0  0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,0  0,1  0,14        0,1  0,0  0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,0  0,1  0,14        0,1  0,0  0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,0  0,1            0,0  0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,0                0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,0                0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,1                0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,1  0,15  0,1  0,15    0,1  0,15  0,1  0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,1                0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,1  0,15  0,1  0,15    0,1  0,15  0,1  0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,1                0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,1  0,15  0,1  0,15    0,1  0,15  0,1  0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,1                0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10  0,0                0,10  0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,1  0,10                    0,1  0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,1                    0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0                            ");
	}

	private void superman(MessageEvent event)
	{

		Utilities.pm(event.getUser().getNick(), "0,0                                                  ");
		Utilities.pm(event.getUser().getNick(), "0,0                                                  ");
		Utilities.pm(event.getUser().getNick(), "0,0        0,12                                  0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,12    0,4                              0,12    0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,12    0,4      0,8  0,4    0,8        0,4    0,8  0,4        0,12    0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,12    0,4      0,8  0,4    0,8            0,4      0,8  0,4      0,12    0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0  0,12    0,4    0,8    0,4    0,8                      0,4    0,12    0,0  ");
		Utilities.pm(event.getUser().getNick(), "0,0    0,12    0,4    0,8  0,4                      0,8    0,4  0,12    0,0    ");
		Utilities.pm(event.getUser().getNick(), "0,0      0,12    0,4                              0,12    0,0      ");
		Utilities.pm(event.getUser().getNick(), "0,0        0,12    0,4    0,8              0,4        0,12    0,0        ");
		Utilities.pm(event.getUser().getNick(), "0,0          0,12    0,4      0,8  0,4  0,8      0,4      0,12    0,0          ");
		Utilities.pm(event.getUser().getNick(), "0,0            0,12    0,4                  0,12    0,0            ");
		Utilities.pm(event.getUser().getNick(), "0,0              0,12    0,4    0,8      0,4    0,12    0,0              ");
		Utilities.pm(event.getUser().getNick(), "0,0                0,12    0,4    0,8  0,4    0,12    0,0                ");
		Utilities.pm(event.getUser().getNick(), "0,0                  0,12    0,4      0,12    0,0                  ");
		Utilities.pm(event.getUser().getNick(), "0,0                    0,12    0,4  0,12    0,0                    ");
		Utilities.pm(event.getUser().getNick(), "0,0                      0,12      0,0                      ");
		Utilities.pm(event.getUser().getNick(), "0,0                        0,12  0,0                        ");
		Utilities.pm(event.getUser().getNick(), "0,0                                                  ");
	}

	private void stormtrooper(MessageEvent event)
	{
		Utilities.pm(event.getUser().getNick(), "0,14                                  ");
		Utilities.pm(event.getUser().getNick(), "0,14          0,1              0,14          ");
		Utilities.pm(event.getUser().getNick(), "0,14        0,1  0,0          0,15    0,1  0,14        ");
		Utilities.pm(event.getUser().getNick(), "0,14      0,1  0,0              0,15    0,1  0,14      ");
		Utilities.pm(event.getUser().getNick(), "0,14      0,1  0,0                0,15  0,1  0,14      ");
		Utilities.pm(event.getUser().getNick(), "0,14      0,1                      0,14      ");
		Utilities.pm(event.getUser().getNick(), "0,14      0,1  0,0                0,15  0,1  0,14      ");
		Utilities.pm(event.getUser().getNick(), "0,14    0,1  0,0  0,1      0,0      0,1      0,15  0,1  0,14    ");
		Utilities.pm(event.getUser().getNick(), "0,14    0,1  0,0  0,1    0,0          0,1    0,15  0,1  0,14    ");
		Utilities.pm(event.getUser().getNick(), "0,14    0,1  0,0        0,1      0,0      0,15  0,1  0,14    ");
		Utilities.pm(event.getUser().getNick(), "0,14    0,1  0,0        0,1      0,0      0,15  0,1  0,14    ");
		Utilities.pm(event.getUser().getNick(), "0,14    0,1    0,0    0,1          0,0    0,1    0,14    ");
		Utilities.pm(event.getUser().getNick(), "0,14  0,1  0,15    0,0  0,1    0,15      0,1    0,0  0,15    0,1  0,14  ");
		Utilities.pm(event.getUser().getNick(), "0,14  0,1  0,0                          0,1  0,14  ");
		Utilities.pm(event.getUser().getNick(), "0,14  0,1  0,0    0,1  0,0  0,15  0,1      0,0    0,1  0,0  0,15  0,1  0,14  ");
		Utilities.pm(event.getUser().getNick(), "0,14    0,1  0,0    0,15  0,1          0,0    0,15  0,1  0,14    ");
		Utilities.pm(event.getUser().getNick(), "0,14      0,1        0,14      0,1        0,14      ");
		Utilities.pm(event.getUser().getNick(), "0,14                                  ");
	}

	private void sendAvailableDrawings(MessageEvent event)
	{
		StringBuilder output = new StringBuilder();

		output.append("Available drawings: ");

		for(String drawing : drawings)
		{
			output.append("'").append(drawing).append("' | ");
		}

		output.delete(output.length() - 3, output.length() - 1);
		Utilities.notice(event, output.toString());
	}
	
	@Override
	public String getAlias()
	{
		return "draw";
	}
}
