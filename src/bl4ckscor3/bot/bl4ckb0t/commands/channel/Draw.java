package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Draw implements ICommand<MessageEvent<Bot>>
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
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException
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
			throw new IncorrectCommandExecutionException(getAlias());
	}

	@Override
	public String getAlias()
	{
		return "draw";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-draw [" + L10N.getString("draw.help.drawing", event) + "]";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]
				{
				"-draw || " + L10N.getString("draw.explanation.1", event),
				"-draw <" + L10N.getString("draw.help.drawing", event) + "> || " + L10N.getString("draw.explanation.2", event)
				};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event)
	{
		return null;
	}
	
	private void smiley(MessageEvent<Bot> event)
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

	private void mario(MessageEvent<Bot> event)
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

	private void batman(MessageEvent<Bot> event)
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

	private void bullshit(MessageEvent<Bot> event)
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

	private void bunny(MessageEvent<Bot> event)
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

	private void cat(MessageEvent<Bot> event)
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

	private void chick(MessageEvent<Bot> event)
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

	private void fly(MessageEvent<Bot> event)
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

	private void invaders(MessageEvent<Bot> event)
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

	private void megaman(MessageEvent<Bot> event)
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

	private void phone(MessageEvent<Bot> event)
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

	private void superman(MessageEvent<Bot> event)
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

	private void stormtrooper(MessageEvent<Bot> event)
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

	private void sendAvailableDrawings(MessageEvent<Bot> event)
	{
		String result = "";

		result += L10N.getString("draw.list", event) + ": ";

		for(String drawing : drawings)
		{
			result += "'" + drawing + "' | ";
		}

		Utilities.notice(event, result.substring(0, result.lastIndexOf(" | ")));
	}
}
