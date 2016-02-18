package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.ArrayList;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Reminder;
import bl4ckscor3.bot.bl4ckb0t.util.TimeParser;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Remind extends BaseCommand<MessageEvent<Bot>>
{
	public static final ArrayList<Reminder> reminders = new ArrayList<Reminder>();

	@Override
	public void exe(MessageEvent<Bot> event, String[] args) throws Exception
	{
		if(args.length == 2 && args[1].equals("list"))
		{
			if(reminders.size() > 0)
			{
				String ids = "";
				
				for(Reminder r : reminders)
				{
					ids += "" + r.getId() + ", ";
				}
				
				ids = ids.substring(0, ids.lastIndexOf(','));
				Utilities.chanMsg(event, L10N.getString("remind.amount", event).replace("#amount", "" + reminders.size()).replace("#ids", ids));
			}
			else
				Utilities.chanMsg(event, L10N.getString("remind.none", event));
			
			return;
		}
		
		try
		{
			int id = Integer.parseInt(args[1]); //if this doesn't fail we know that the user is probably asking for a specific id

			if(!(id > Reminder.latestId))
			{
				for(Reminder r : reminders)
				{
					if(r.getId() == id)
					{
						if(args.length >= 3 && args[2].equals("stop"))
						{
							if(event.getUser().getNick().equals(r.getIssuedUser()))
							{
								if(event.getChannel().getName().equals(r.getIssuedChannel()))
								{
									r.stop();
									Utilities.chanMsg(event, L10N.getString("remind.stopped", event).replace("#id", "" + r.getId()));
								}
								else
									Utilities.chanMsg(event, L10N.getString("remind.wrongChannel", event));
							}
							else
								Utilities.chanMsg(event, L10N.getString("remind.notOwned", event));
							
							return;
						}
						
						if(event.getUser().getNick().equals(r.getIssuedUser()))
						{
							if(event.getChannel().getName().equals(r.getIssuedChannel()))
								Utilities.chanMsg(event, L10N.getString("remind.timeLeft", event).replace("#event", r.getEvent()).replace("#timeLeft", new TimeParser(getMainAlias()).lts(r.getRemainingTime(), "%sd%sh%sm%ss")));
							else
								Utilities.chanMsg(event, L10N.getString("remind.wrongChannel", event));
						}
						else
							Utilities.chanMsg(event, L10N.getString("remind.notOwned", event));
						return;
					}
				}
			}
			
			Utilities.chanMsg(event, L10N.getString("remind.noId", event));
		}
		catch(NumberFormatException ex)
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
					timeDue = new TimeParser(getMainAlias()).stl(args[1]) * 1000L;
				}
				catch(NumberFormatException ex1)
				{
					throw new IncorrectCommandExecutionException(getMainAlias());
				}
				catch(IncorrectCommandExecutionException ex2)
				{
					return;
				}

				Reminder reminder = new Reminder(event, ev, timeDue);
				
				Utilities.chanMsg(event, L10N.getString("remind.success", event).replace("#id", "" + reminder.getId()));
				reminders.add(reminder);
			}
			else
				throw new IncorrectCommandExecutionException(getMainAlias());
		}
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
		return new String[]{
				"-remind 1d1h1m1s abc || " + L10N.getString("remind.explanation.1", event),
				"-remind 5 || " + L10N.getString("remind.explanation.2", event)
		};
	}
}
