package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Remind extends BaseCommand<MessageEvent<Bot>>
{	
	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws Exception
	{
		if(args.length >= 3)
		{
			String e = "";

			for(int i = 2; i < args.length; i++)
			{
				e += args[i] + " ";
			}

			String ev = e.trim();
			long timeDue = 0;
			
			try
			{
				timeDue = new TimeParser().get(args[1]) * 1000L;
			}
			catch(NumberFormatException ex1)
			{
				throw new IncorrectCommandExecutionException(getMainAlias());
			}
			catch(IncorrectCommandExecutionException ex2)
			{
				return;
			}
			
			Utilities.chanMsg(event, L10N.getString("remind.success", event));
			Executors.newSingleThreadScheduledExecutor().schedule(() -> {
				Utilities.chanMsg(event, L10N.getString("remind.reminder", event).replace("#user", event.getUser().getNick()).replace("#event", ev));
			}, timeDue, TimeUnit.MILLISECONDS);
		}
		else
			throw new IncorrectCommandExecutionException(getMainAlias());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"remind"};
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event)
	{
		return "-remind <" + L10N.getString("cmd.help.time", event) +"> <" + L10N.getString("cmd.help.sentence", event) + ">";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event)
	{
		return new String[]{"-remind 1d1h1m1s abc|| " + L10N.getString("remind.explanation", event)};
	}

	private class TimeParser
	{
		public long get(String s) throws IncorrectCommandExecutionException
		{
			if(s.contains("d"))
				return d(s);
			else if(s.contains("h"))
				return h(s);
			else if(s.contains("m"))
				return m(s);
			else if(s.contains("s"))
				return s(s);
			else
				throw new IncorrectCommandExecutionException(getMainAlias());
		}

		private long d(String s)
		{
			long value = Long.parseLong(s.split("d")[0]);

			value *= 24 * 60 * 60;

			if(s.contains("h"))
				return value + h(s);
			else if(s.contains("m"))
				return value + m(s);
			else if(s.contains("s"))
				return value + s(s);
			else
				return value;
		}

		private long h(String s)
		{
			long value = 0;

			if(s.contains("d"))
				value = Long.parseLong(s.split("d")[1].split("h")[0]);
			else
				value = Long.parseLong(s.split("h")[0]);

			value *= 60 * 60;

			if(s.contains("m"))
				return value + m(s);
			else if(s.contains("s"))
				return value + s(s);
			else
				return value;
		}

		private long m(String s)
		{
			long value = 0;

			if(s.contains("d"))
			{
				if(s.contains("h"))
					value = Long.parseLong(s.split("d")[1].split("h")[1].split("m")[0]);
				else
					value = Long.parseLong(s.split("d")[1].split("m")[0]);
			}
			else if(s.contains("h"))
				value = Long.parseLong(s.split("h")[1].split("m")[0]);
			else
				value = Long.parseLong(s.split("m")[0]);

			value *= 60;

			if(s.contains("s"))
				return value + s(s);
			else
				return value;
		}

		private long s(String s)
		{
			long value = 0;

			if(s.contains("d"))
			{
				if(s.contains("h"))
				{
					if(s.contains("m"))
						value = Long.parseLong(s.split("d")[1].split("h")[1].split("m")[1].split("s")[0]);
					else
						value = Long.parseLong(s.split("d")[1].split("h")[1].split("s")[0]);
				}
				else
				{
					if(s.contains("m"))
						value = Long.parseLong(s.split("d")[1].split("m")[1].split("s")[0]);
					else
						value = Long.parseLong(s.split("d")[1].split("s")[0]);
				}
			}
			else if(s.contains("h"))
			{
				if(s.contains("m"))
					value = Long.parseLong(s.split("h")[1].split("m")[1].split("s")[0]);
				else
					value = Long.parseLong(s.split("h")[1].split("s")[0]);
			}
			else if(s.contains("m"))
				value = Long.parseLong(s.split("m")[1].split("s")[0]);

			return value;
		}
	}
}
