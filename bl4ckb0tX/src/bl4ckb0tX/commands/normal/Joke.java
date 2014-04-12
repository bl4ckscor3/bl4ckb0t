package bl4ckb0tX.commands.normal;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Jokes;
import bl4ckb0tX.util.Utilities;

public class Joke
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		Random rand = new Random();
		String[] jokes = Jokes.jokes; 
		int joke = rand.nextInt(jokes.length);
		Utilities.chanMsg(event, jokes[joke]);
	}
}
