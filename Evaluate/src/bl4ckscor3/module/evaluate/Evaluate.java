package bl4ckscor3.module.evaluate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Evaluate extends Module
{
	private L10N l10n;

	public Evaluate(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command(this));
		l10n = new L10N(this, loader);
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}

	public class Command extends BaseChannelCommand
	{
		public Module module;

		public Command(Module m)
		{
			module = m;
		}

		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String input = "";
			
			for(int i = 0; i < args.length; i++)
			{
				input += args[i] + " ";
			}
			
			evaluate(event, input.trim());
		}
		
		/**
		 * Queries WolframAlpha
		 * @param event The MessageEvent the command that triggered this got sent in
		 * @param input The query for WolframAlpha (text to send)
		 */
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
						Utilities.sendMessage(channel, "appid: " + l10n.translate("fail", channel));
						Logging.severe("Appid of WolframAlpha is missing. Something went horribly wrong.");
						return;
					}
					else if(line.contains("success='false'"))
					{
						Utilities.sendMessage(channel, l10n.translate("apiFail", channel));
						reader.close();
						return;
					}
				}
			}
			catch(NullPointerException e)
			{
				Utilities.sendMessage(channel, "title: " + l10n.translate("fail", channel));
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
				Utilities.sendMessage(channel, "plaintext: " + l10n.translate("fail", channel));
				Logging.warn("-evaluate: Actual result not found.");
				reader.close();
				return;
			}
			
			reader.close();
			
			String result;
			
			try
			{
				result = line.split(">")[1].split("<")[0];
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				Utilities.sendMessage(channel, l10n.translate("apiFail", channel));
				return;
			}
			
			if(result.matches("[0-9]+/[0-9]+.*"))
			{
				evaluate(event, input + " in decimal");
				return;
			}
			
			Utilities.sendMessage(channel, result);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"evaluate", "eval", "calc", "calculate"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}