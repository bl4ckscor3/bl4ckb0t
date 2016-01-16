package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Evaluate extends BaseCommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws IncorrectCommandExecutionException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://api.wolframalpha.com/v2/query?appid=" + Passwords.WOLFRAMAPIKEY.getPassword() + "&input=" + args[1].replace("+", "%2B").replace(' ', '+').replace(',', '.')).openStream()));
		String line = "";
		
		try
		{
			//skipping lines until wanted line is reached
			while(!((line = reader.readLine()).contains("position='200'")))
			{
				if(line.contains("Appid missing"))
				{
					Utilities.chanMsg(event, "appid: " + L10N.getString("evaluate.fail", event));
					Logging.severe("Appid of WolframAlpha is missing. Something went horribly wrong.");
					return;
				}
				else if(line.contains("success='false'"))
				{
					Utilities.chanMsg(event, L10N.getString("evaluate.apiFail", event));
					reader.close();
					return;
				}
			}
		}
		catch(NullPointerException e)
		{
			Utilities.chanMsg(event, "title: " + L10N.getString("evaluate.fail", event));
			Logging.warn("-evaluate: Result line could not be found.");
			reader.close();
			return;
		}
		
		try
		{
			//skipping lines to the line with the result
			while(!((line = reader.readLine()).contains("plaintext"))){}
		}
		catch(NullPointerException e)
		{
			Utilities.chanMsg(event, "plaintext: " + L10N.getString("evaluate.fail", event));
			Logging.warn("-evaluate: Actual result not found.");
			reader.close();
			return;
		}
		
		reader.close();
		Utilities.chanMsg(event, line.split(">")[1].split("<")[0]);
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"evaluate", "eval", "calc", "calculate"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event) 
	{
		return "-evaluate <" + L10N.getString("evaluate.eval", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event) 
	{
		return new String[]{"-evaluate <" + L10N.getString("evaluate.eval", event) + "> || " + L10N.getString("evaluate.explanation", event).replace("#link", "http://wolframalpha.com/")};
	}
}
