package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Evaluate extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws IncorrectCommandExecutionException, IOException
	{
		String input = "";
		
		for(int i = 1; i < args.length; i++)
		{
			input += args[i] + " ";
		}
		
		evaluate(event, input);
	}
	
	private void evaluate(MessageEvent event, String input) throws MalformedURLException, IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://api.wolframalpha.com/v2/query?appid=" + Passwords.WOLFRAMAPIKEY.getPassword() + "&input=" + input.trim().replace("+", "%2B").replace(' ', '+').replace(',', '.')).openStream()));
		String line = "";
		String channel = event.getChannel().getName();
		
		try
		{
			//skipping lines until wanted line is reached
			while(!((line = reader.readLine()).contains("position='200'")))
			{
				if(line.contains("Appid missing"))
				{
					Utilities.sendMessage(channel, "appid: " + L10N.getString("evaluate.fail", channel));
					Logging.severe("Appid of WolframAlpha is missing. Something went horribly wrong.");
					return;
				}
				else if(line.contains("success='false'"))
				{
					Utilities.sendMessage(channel, L10N.getString("evaluate.apiFail", channel));
					reader.close();
					return;
				}
			}
		}
		catch(NullPointerException e)
		{
			Utilities.sendMessage(channel, "title: " + L10N.getString("evaluate.fail", channel));
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
			Utilities.sendMessage(channel, "plaintext: " + L10N.getString("evaluate.fail", channel));
			Logging.warn("-evaluate: Actual result not found.");
			reader.close();
			return;
		}
		
		reader.close();
		
		String result = line.split(">")[1].split("<")[0];
		
		if(result.contains("/"))
		{
			evaluate(event, input + " in decimal");
			return;
		}
		
		Utilities.sendMessage(channel, line.split(">")[1].split("<")[0]);
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"evaluate", "eval", "calc", "calculate"};
	}

	@Override
	public String getSyntax(String channel) 
	{
		return "-evaluate <" + L10N.getString("evaluate.eval", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel) 
	{
		return new String[]{"-evaluate <" + L10N.getString("evaluate.eval", channel) + "> || " + L10N.getString("evaluate.explanation", channel).replace("#link", "http://wolframalpha.com/")};
	}
}
