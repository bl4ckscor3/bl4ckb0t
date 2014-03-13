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
		"stormtrooper"
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
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			Stuffz.chanMsg(event, getAvailableDrawings());
		}
	}
	private static void smiley(MessageEvent event)
	{
		Stuffz.chanMsg(event, "1,1          1,1          1,1          ");
		Stuffz.chanMsg(event, "1,1      1,1    8,8          1,1    1,1      ");
		Stuffz.chanMsg(event, "1,1    1,1  8,8                  1,1  1,1    ");
		Stuffz.chanMsg(event, "1,1  1,1  8,8    1,1    8,8      1,1    8,8    1,1  1,1  ");
		Stuffz.chanMsg(event, "1,1  1,1  8,8  1,1  8,8    1,1  8,8  1,1  8,8    1,1  8,8  1,1  1,1  ");
		Stuffz.chanMsg(event, "1,1  8,8                          1,1  ");
		Stuffz.chanMsg(event, "1,1  8,8  1,1                      8,8  1,1  ");
		Stuffz.chanMsg(event, "1,1  8,8  1,1  0,0  15,15  1,1  0,0    15,15  1,1  0,0  15,15  1,1  8,8  1,1  ");
		Stuffz.chanMsg(event, "1,1  8,8  1,1  0,0  15,15  1,1  0,0    15,15  1,1  0,0  15,15  1,1  8,8  1,1  ");
		Stuffz.chanMsg(event, "1,1  8,8  1,1  0,0  15,15  1,1  0,0    15,15  1,1  0,0  15,15  1,1  8,8  1,1  ");
		Stuffz.chanMsg(event, "1,1  1,1  8,8  1,1  15,15  1,1  0,0    15,15  1,1  0,0  1,1  8,8  1,1  1,1  ");
		Stuffz.chanMsg(event, "1,1  1,1  8,8    1,1    0,0    15,15  1,1    8,8    1,1  1,1  ");
		Stuffz.chanMsg(event, "1,1    1,1  8,8    1,1          8,8    1,1  1,1    ");
		Stuffz.chanMsg(event, "1,1      1,1    8,8          1,1    1,1      ");
		Stuffz.chanMsg(event, "1,1          1,1          1,1          ");
	}

	private static void mario(MessageEvent event)
	{
		Stuffz.chanMsg(event, "1,1                ");
		Stuffz.chanMsg(event, "1,1     4,4     1,1      ");
		Stuffz.chanMsg(event, "1,1    4,4         1,1   ");
		Stuffz.chanMsg(event, "1,1    5,5   7,7  5,5 7,7 1,1     ");
		Stuffz.chanMsg(event, "1,1   5,5 7,7 5,5 7,7   5,5 7,7   1,1   ");
		Stuffz.chanMsg(event, "1,1   5,5 7,7 5,5  7,7   5,5 7,7   1,1  ");
		Stuffz.chanMsg(event, "1,1   5,5  7,7    5,5    1,1   ");
		Stuffz.chanMsg(event, "1,1     7,7       1,1    ");
		Stuffz.chanMsg(event, "1,1    5,5  4,4 5,5   1,1      ");
		Stuffz.chanMsg(event, "1,1   5,5   4,4 5,5  4,4 5,5   1,1   ");
		Stuffz.chanMsg(event, "1,1  5,5    4,4    5,5    1,1  ");
		Stuffz.chanMsg(event, "1,1  7,7  5,5 4,4 7,7 4,4  7,7 4,4 5,5 7,7  1,1  ");
		Stuffz.chanMsg(event, "1,1  7,7   4,4      7,7   1,1  ");
		Stuffz.chanMsg(event, "1,1  7,7  4,4        7,7  1,1  ");
		Stuffz.chanMsg(event, "1,1    4,4   1,1  4,4   1,1    ");
		Stuffz.chanMsg(event, "1,1   5,5   1,1    5,5   1,1   ");
		Stuffz.chanMsg(event, "1,1  5,5    1,1    5,5    1,1  ");
		Stuffz.chanMsg(event, "1,1                ");
	}

	private static void batman(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0                                          ");
		Stuffz.chanMsg(event, "0,0              1,1              0,0              ");
		Stuffz.chanMsg(event, "0,0          1,1    8,8              1,1    0,0          ");
		Stuffz.chanMsg(event, "0,0        1,1  8,8                      1,1  0,0        ");
		Stuffz.chanMsg(event, "0,0      1,1  8,8    1,1  8,8    1,1  8,8  1,1  8,8    1,1  8,8    1,1  0,0      ");
		Stuffz.chanMsg(event, "0,0    1,1  8,8    1,1  8,8      1,1      8,8      1,1  8,8    1,1  0,0    ");
		Stuffz.chanMsg(event, "0,0  1,1  8,8    1,1    8,8      1,1      8,8      1,1    8,8    1,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  1,1  8,8  1,1        8,8  1,1          8,8  1,1        8,8  1,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  1,1  8,8  1,1                              8,8  1,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  1,1  8,8  1,1                              8,8  1,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  1,1  8,8  1,1                              8,8  1,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  1,1  8,8  1,1                              8,8  1,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  1,1  8,8    1,1    8,8  1,1  8,8  1,1      8,8  1,1  8,8  1,1    8,8    1,1  0,0  ");
		Stuffz.chanMsg(event, "0,0    1,1  8,8    1,1  8,8        1,1  8,8        1,1  8,8    1,1  0,0    ");
		Stuffz.chanMsg(event, "0,0      1,1  8,8    1,1  8,8      1,1  8,8      1,1  8,8    1,1  0,0      ");
		Stuffz.chanMsg(event, "0,0        1,1  8,8                      1,1  0,0        ");
		Stuffz.chanMsg(event, "0,0          1,1    8,8              1,1    0,0          ");
		Stuffz.chanMsg(event, "0,0              1,1              0,0              ");
		Stuffz.chanMsg(event, "0,0                                          ");
	}

	private static void bullshit(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0                                                  ");
		Stuffz.chanMsg(event, "0,0  14,14  0,0              14,14  0,0                              ");
		Stuffz.chanMsg(event, "0,0  14,14  0,0      1,1        14,14  1,1                    0,0          ");
		Stuffz.chanMsg(event, "0,0  0,14    0,1          0,14    0,0    0,14                0,1      0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1  0,14      0,0    0,1  0,0      0,14                0,1  0,5  0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1  0,14  0,1  0,0  0,1  0,0  0,1  0,0      0,14                0,1  0,5  0,0  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0    0,1  0,0  0,1  0,0  0,1  0,0  0,1  0,0        0,14            0,0  0,1  0,5  0,0  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0    0,1              0,0          0,14      0,0      0,1  0,0    0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,13            0,1    0,0                0,14    0,1  0,0    0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,13              0,1  0,0    0,14        0,0    0,14    0,1  0,0        ");
		Stuffz.chanMsg(event, "0,0  0,1  0,13  0,5  0,13      0,5  0,13  0,1  0,0  0,14            0,0      0,1  0,0        ");
		Stuffz.chanMsg(event, "0,0    0,1  0,13  0,5  0,13  0,5  0,13    0,1  0,14                0,0  0,1    0,0        ");
		Stuffz.chanMsg(event, "	0,0      0,1                                  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,0      0,1  0,0      0,13          0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0          0,1  0,0      0,1  0,0        0,13      0,0  0,1  0,0    0,5  0,0      ");
		Stuffz.chanMsg(event, "0,0          0,1  0,0      0,1  0,0          0,1  0,0    0,1  0,0  0,5      0,0    ");
		Stuffz.chanMsg(event, "0,0          0,1  0,0      0,1  0,0          0,1  0,0    0,1  0,5          0,0  ");
		Stuffz.chanMsg(event, "0,0  0,3                                                ");
		Stuffz.chanMsg(event, "0,0                                                  ");
	}

	private static void bunny(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0                                              ");
		Stuffz.chanMsg(event, "0,0      0,1      0,0                    0,1      0,0        ");
		Stuffz.chanMsg(event, "0,0    0,1  0,15  0,0    0,1  0,0                0,1  0,0    0,15  0,1  0,0      ");
		Stuffz.chanMsg(event, "0,0    0,1  0,15  0,13    0,0  0,1  0,0            0,1  0,0  0,13    0,15  0,1  0,0      ");
		Stuffz.chanMsg(event, "0,0    0,1  0,15    0,13  0,0  0,1  0,0            0,1  0,0  0,13  0,15    0,1  0,0      ");
		Stuffz.chanMsg(event, "0,0      0,1  0,15  0,13    0,0  0,1  0,0        0,1  0,0  0,13    0,15  0,1  0,0        ");
		Stuffz.chanMsg(event, "0,0        0,1  0,15  0,13  0,0  0,1  0,0        0,1  0,0  0,13  0,15  0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,15  0,13  0,0  0,1        0,0  0,13  0,15  0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0            0,1      0,15        0,1      0,0              ");
		Stuffz.chanMsg(event, "0,0            0,1  0,15  0,0            0,15  0,1  0,0              ");
		Stuffz.chanMsg(event, "0,0          0,1  0,15  0,0                0,15  0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0          0,1  0,15  0,0                0,15  0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0          0,1  0,15  0,0  0,1  0,0        0,1  0,0  0,15  0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0          0,1  0,15  0,0  0,1  0,0        0,1  0,0  0,15  0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0          0,1  0,15  0,13    0,0        0,13    0,15  0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0        0,1      0,15                0,1      0,0          ");
		Stuffz.chanMsg(event, "0,1        0,0    0,1                    0,0    0,1          ");
		Stuffz.chanMsg(event, "0,3      0,1  0,15      0,1  0,3            0,1  0,15      0,1  0,3        ");
		Stuffz.chanMsg(event, "0,3        0,1      0,3                0,1      0,3          ");
		Stuffz.chanMsg(event, "0,3                                              ");
		Stuffz.chanMsg(event, "0,3                                              ");
	}

	private static void cat(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0                                          ");
		Stuffz.chanMsg(event, "0,0      0,1    0,0                      0,1    0,0      ");
		Stuffz.chanMsg(event, "0,0    0,1  0,7    0,1  0,0                  0,1  0,7    0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1  0,7      0,1  0,0              0,1  0,7      0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1  0,7  0,5  0,7    0,1  0,0          0,1  0,7    0,5  0,7  0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1  0,7  0,5    0,7    0,1          0,7    0,5    0,7  0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1  0,7  0,5    0,7    0,5  0,7  0,5  0,7  0,5  0,7    0,5    0,7  0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0  0,1  0,7            0,5  0,7  0,5  0,7  0,5  0,7            0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,5    0,7                          0,5    0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,7      0,1    0,0  0,7          0,1    0,0  0,7      0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,5    0,7  0,1      0,7  0,1  0,13  0,1  0,7  0,1      0,7  0,5    0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,7      0,1      0,7  0,1      0,7  0,1      0,7      0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,5    0,7                          0,5    0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,7          0,1  0,7    0,1  0,7    0,1  0,7          0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0    0,1  0,7        0,1              0,7        0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0      0,1  0,7                          0,1  0,0      ");
		Stuffz.chanMsg(event, "0,0        0,1                          0,0        ");
		Stuffz.chanMsg(event, "0,0                                          ");
	}

	private static void chick(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0                                                  ");
		Stuffz.chanMsg(event, "0,0                0,1                  0,0                ");
		Stuffz.chanMsg(event, "0,0              0,1  0,8                  0,1  0,0              ");
		Stuffz.chanMsg(event, "0,0            0,1  0,8                      0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8                          0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8                          0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8    0,0  0,1  0,8          0,0  0,1  0,8    0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8    0,1    0,8          0,1    0,8    0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8    0,1    0,8          0,1    0,8    0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8          0,1      0,8          0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0        0,1    0,8        0,1  0,7      0,1  0,8        0,1    0,0        ");
		Stuffz.chanMsg(event, "0,0      0,1  0,8              0,1      0,8              0,1  0,0      ");
		Stuffz.chanMsg(event, "0,0    0,1  0,8                                      0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0  0,1  0,8    0,1  0,8          0,0          0,8        0,1    0,8    0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0    0,1    0,0  0,1  0,8      0,0              0,8      0,1  0,0  0,1    0,0    ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8      0,0              0,8      0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8      0,0              0,8      0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8          0,0      0,8          0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0          0,1  0,8  0,1      0,8  0,1      0,8  0,1      0,8  0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0            0,1  0,7      0,1  0,0      0,1  0,7      0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0              0,1      0,0          0,1      0,0              ");
		Stuffz.chanMsg(event, "0,0                                                                                                    ");
	}

	private static void fly(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0                                                            ");
		Stuffz.chanMsg(event, "0,0    0,14      0,0                                      0,14        0,0    ");
		Stuffz.chanMsg(event, "0,0  0,14  0,0      0,14    0,0          0,1            0,0          0,14  0,0        0,14  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,14  0,0          0,14  0,0    0,1      0,14    0,15      0,1    0,0    0,14  0,0          0,14  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,14  0,15  0,0        0,14  0,0  0,1      0,14    0,15            0,1  0,0  0,14  0,0        0,15  0,14  0,0  ");
		Stuffz.chanMsg(event, "0,0    0,14  0,15    0,0      0,1        0,14        0,15          0,1  0,15  0,0    0,15    0,14  0,0    ");
		Stuffz.chanMsg(event, "0,0      0,14    0,15      0,1          0,14        0,15      0,14  0,1  0,15      0,14    0,0      ");
		Stuffz.chanMsg(event, "0,0          0,14    0,1                  0,14            0,1  0,14    0,0          ");
		Stuffz.chanMsg(event, "0,0              0,1    0,0        0,1        0,0        0,14  0,1  0,0              ");
		Stuffz.chanMsg(event, "0,0              0,1  0,0            0,1    0,0            0,1  0,0              ");
		Stuffz.chanMsg(event, "0,0              0,1  0,0    0,1  0,15  0,0    0,1    0,0    0,1  0,15  0,0    0,1  0,0              ");
		Stuffz.chanMsg(event, "0,0              0,1  0,0    0,1    0,0    0,1    0,0    0,1    0,0    0,1  0,0              ");
		Stuffz.chanMsg(event, "0,0            0,1    0,0            0,1    0,0            0,1    0,0            ");
		Stuffz.chanMsg(event, "0,0            0,1  0,0  0,1  0,0        0,1        0,0        0,1  0,0  0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0            0,1  0,0  0,1                            0,0  0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0          0,1    0,0    0,1                        0,0    0,1    0,0          ");
		Stuffz.chanMsg(event, "0,0                  0,1          0,0    0,1          0,0                  ");
		Stuffz.chanMsg(event, "0,0                  0,1  0,0    0,1            0,0    0,1  0,0                  ");
		Stuffz.chanMsg(event, "0,0                0,1    0,0                    0,1    0,0                ");
		Stuffz.chanMsg(event, "0,0                                                            ");

	}

	private static void invaders(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0                                                                                            ");
		Stuffz.chanMsg(event, "0,0                                          0,1            0,0                                      ");
		Stuffz.chanMsg(event, "0,0                                  0,1          0,5        0,1          0,0                              ");
		Stuffz.chanMsg(event, "0,0      0,1      0,0        0,1      0,0      0,1      0,5      0,4        0,5      0,1      0,0        0,1            0,0        ");
		Stuffz.chanMsg(event, "0,0      0,1  0,12  0,1  0,0        0,1  0,12  0,1  0,0      0,1  0,5    0,4                    0,5    0,1  0,0      0,1    0,3        0,1    0,0      ");
		Stuffz.chanMsg(event, "0,0      0,1    0,12  0,1        0,12  0,1    0,0      0,1  0,5  0,4      0,1    0,4    0,1    0,4      0,5  0,1  0,0    0,1    0,3  0,9        0,3  0,1    0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1    0,12  0,11  0,12        0,11  0,12  0,1    0,0    0,1  0,5  0,4                        0,5  0,1  0,0  0,1    0,3  0,9            0,3  0,1    0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1    0,12  0,11  0,1  0,11        0,1  0,11  0,12  0,1    0,0  0,1          0,4    0,1    0,4    0,1          0,0  0,1  0,3  0,9  0,1  0,9        0,1  0,9  0,3  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1    0,11    0,1  0,11        0,1  0,11    0,1    0,0          0,1  0,4    0,1    0,4    0,1  0,0          0,1  0,3  0,9  0,1  0,9        0,1  0,9  0,3  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,12  0,11                    0,12  0,1  0,0  0,1        0,4    0,1  0,4    0,1  0,4    0,1        0,0  0,1  0,3  0,9                0,3  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,11  0,1  0,11                0,1  0,11  0,1  0,0  0,1  0,5  0,4    0,1                0,4    0,5  0,1  0,0  0,1      0,9  0,1        0,9  0,1      0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,11  0,1  0,11  0,1            0,11  0,1  0,11  0,1  0,0  0,1          0,0            0,1          0,0  0,1    0,9  0,1  0,9        0,1  0,9  0,1    0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1        0,11    0,1    0,11    0,1        0,0                                    0,1  0,3  0,1  0,9  0,1  0,9    0,1  0,9  0,1  0,3  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0        0,1      0,0    0,1      0,0                                            0,1  0,0  0,1  0,0  0,1    0,0  0,1  0,0  0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0                                                                                            ");
	}

	private static void megaman(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0                                              ");
		Stuffz.chanMsg(event, "0,0                      0,1      0,0                  ");
		Stuffz.chanMsg(event, "0,0                  0,1      0,11    0,1  0,0                ");
		Stuffz.chanMsg(event, "0,0                0,1  0,12      0,1  0,11    0,1  0,0              ");
		Stuffz.chanMsg(event, "0,0              0,1  0,12          0,1        0,0            ");
		Stuffz.chanMsg(event, "0,0              0,1  0,12          0,1  0,11    0,12  0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0            0,1  0,11  0,12            0,1    0,12  0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0            0,1  0,11  0,12    0,15  0,0      0,12    0,0  0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0            0,1  0,11  0,12  0,15  0,0    0,1    0,15  0,1  0,0  0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0              0,1  0,12  0,15  0,0    0,1    0,15  0,1  0,0  0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0            0,1    0,12  0,15    0,0      0,15  0,0    0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0        0,1    0,11    0,1  0,2  0,15  0,1        0,15  0,1      0,0        ");
		Stuffz.chanMsg(event, "0,0      0,1  0,12  0,11        0,1  0,15          0,1  0,11    0,12  0,1  0,0      ");
		Stuffz.chanMsg(event, "0,0      0,1  0,12    0,11        0,1          0,11    0,12    0,1  0,0      ");
		Stuffz.chanMsg(event, "0,0    0,1  0,12      0,11  0,1  0,11              0,1  0,11  0,12      0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1  0,12    0,1      0,11              0,1      0,12    0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1  0,12      0,1    0,11              0,1    0,12      0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0    0,1  0,12      0,1    0,12              0,1    0,12      0,1  0,0    ");
		Stuffz.chanMsg(event, "0,0      0,1      0,0  0,1  0,12              0,1  0,0  0,1      0,0      ");
		Stuffz.chanMsg(event, "0,0            0,1  0,11    0,12        0,11      0,1  0,0            ");
		Stuffz.chanMsg(event, "0,0          0,1  0,12    0,11      0,1  0,11        0,12  0,1  0,0          ");
		Stuffz.chanMsg(event, "0,0        0,1    0,12      0,11  0,1  0,0  0,1  0,11  0,12      0,1    0,0        ");
		Stuffz.chanMsg(event, "0,0    0,1    0,12          0,1  0,0      0,1  0,12          0,1    0,0    ");
		Stuffz.chanMsg(event, "0,0  0,1  0,12              0,1  0,0      0,1  0,12              0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1                  0,0      0,1                  0,0  ");
		Stuffz.chanMsg(event, "0,0                                              ");
	}

	private static void phone(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,0                            ");
		Stuffz.chanMsg(event, "0,0                0,1      0,0      ");
		Stuffz.chanMsg(event, "0,0                  0,1  0,0        ");
		Stuffz.chanMsg(event, "0,0                  0,1  0,0        ");
		Stuffz.chanMsg(event, "0,0                  0,1  0,0        ");
		Stuffz.chanMsg(event, "0,0                  0,1  0,0        ");
		Stuffz.chanMsg(event, "0,0    0,1                    0,0    ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10                    0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,0                0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,0  0,1            0,0  0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,0  0,1  0,14        0,1  0,0  0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,0  0,1  0,14        0,1  0,0  0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,0  0,1  0,14        0,1  0,0  0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,0  0,1            0,0  0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,0                0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,0                0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,1                0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,1  0,15  0,1  0,15    0,1  0,15  0,1  0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,1                0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,1  0,15  0,1  0,15    0,1  0,15  0,1  0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,1                0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,1  0,15  0,1  0,15    0,1  0,15  0,1  0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,1                0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10  0,0                0,10  0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0  0,1  0,10                    0,1  0,0  ");
		Stuffz.chanMsg(event, "0,0    0,1                    0,0    ");
		Stuffz.chanMsg(event, "0,0                            ");
	}

	private static void superman(MessageEvent event)
	{

		Stuffz.chanMsg(event, "0,0                                                  ");
		Stuffz.chanMsg(event, "0,0                                                  ");
		Stuffz.chanMsg(event, "0,0        0,12                                  0,0        ");
		Stuffz.chanMsg(event, "0,0      0,12    0,4                              0,12    0,0      ");
		Stuffz.chanMsg(event, "0,0    0,12    0,4      0,8  0,4    0,8        0,4    0,8  0,4        0,12    0,0    ");
		Stuffz.chanMsg(event, "0,0  0,12    0,4      0,8  0,4    0,8            0,4      0,8  0,4      0,12    0,0  ");
		Stuffz.chanMsg(event, "0,0  0,12    0,4    0,8    0,4    0,8                      0,4    0,12    0,0  ");
		Stuffz.chanMsg(event, "0,0    0,12    0,4    0,8  0,4                      0,8    0,4  0,12    0,0    ");
		Stuffz.chanMsg(event, "0,0      0,12    0,4                              0,12    0,0      ");
		Stuffz.chanMsg(event, "0,0        0,12    0,4    0,8              0,4        0,12    0,0        ");
		Stuffz.chanMsg(event, "0,0          0,12    0,4      0,8  0,4  0,8      0,4      0,12    0,0          ");
		Stuffz.chanMsg(event, "0,0            0,12    0,4                  0,12    0,0            ");
		Stuffz.chanMsg(event, "0,0              0,12    0,4    0,8      0,4    0,12    0,0              ");
		Stuffz.chanMsg(event, "0,0                0,12    0,4    0,8  0,4    0,12    0,0                ");
		Stuffz.chanMsg(event, "0,0                  0,12    0,4      0,12    0,0                  ");
		Stuffz.chanMsg(event, "0,0                    0,12    0,4  0,12    0,0                    ");
		Stuffz.chanMsg(event, "0,0                      0,12      0,0                      ");
		Stuffz.chanMsg(event, "0,0                        0,12  0,0                        ");
		Stuffz.chanMsg(event, "0,0                                                  ");
	}

	private static void stormtrooper(MessageEvent event)
	{
		Stuffz.chanMsg(event, "0,14                                  ");
		Stuffz.chanMsg(event, "0,14          0,1              0,14          ");
		Stuffz.chanMsg(event, "0,14        0,1  0,0          0,15    0,1  0,14        ");
		Stuffz.chanMsg(event, "0,14      0,1  0,0              0,15    0,1  0,14      ");
		Stuffz.chanMsg(event, "0,14      0,1  0,0                0,15  0,1  0,14      ");
		Stuffz.chanMsg(event, "0,14      0,1                      0,14      ");
		Stuffz.chanMsg(event, "0,14      0,1  0,0                0,15  0,1  0,14      ");
		Stuffz.chanMsg(event, "0,14    0,1  0,0  0,1      0,0      0,1      0,15  0,1  0,14    ");
		Stuffz.chanMsg(event, "0,14    0,1  0,0  0,1    0,0          0,1    0,15  0,1  0,14    ");
		Stuffz.chanMsg(event, "0,14    0,1  0,0        0,1      0,0      0,15  0,1  0,14    ");
		Stuffz.chanMsg(event, "0,14    0,1  0,0        0,1      0,0      0,15  0,1  0,14    ");
		Stuffz.chanMsg(event, "0,14    0,1    0,0    0,1          0,0    0,1    0,14    ");
		Stuffz.chanMsg(event, "0,14  0,1  0,15    0,0  0,1    0,15      0,1    0,0  0,15    0,1  0,14  ");
		Stuffz.chanMsg(event, "0,14  0,1  0,0                          0,1  0,14  ");
		Stuffz.chanMsg(event, "0,14  0,1  0,0    0,1  0,0  0,15  0,1      0,0    0,1  0,0  0,15  0,1  0,14  ");
		Stuffz.chanMsg(event, "0,14    0,1  0,0    0,15  0,1          0,0    0,15  0,1  0,14    ");
		Stuffz.chanMsg(event, "0,14      0,1        0,14      0,1        0,14      ");
		Stuffz.chanMsg(event, "0,14                                  ");

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
