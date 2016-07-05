package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.util.ArrayList;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Reminder;
import bl4ckscor3.bot.bl4ckb0t.util.TimeParser;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Remind extends BaseChannelCommand
{
	public static final ArrayList<Reminder> reminders = new ArrayList<Reminder>();

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		String channel = event.getChannel().getName();
		
		if(args.length == 1 && args[0].equals("list"))
		{
			if(reminders.size() > 0)
			{
				String ids = "";
				
				for(Reminder r : reminders)
				{
					ids += "" + r.getId() + ", ";
				}
				
				ids = ids.substring(0, ids.lastIndexOf(','));
				Utilities.sendMessage(channel, L10N.getString("remind.amount", channel).replace("#amount", "" + reminders.size()).replace("#ids", ids));
			}
			else
				Utilities.sendMessage(channel, L10N.getString("remind.none", channel));
			
			return;
		}
		
		try
		{
			int id = Integer.parseInt(args[0]); //if this doesn't fail we know that the user is probably asking for a specific id

			if(!(id > Reminder.latestId))
			{
				for(Reminder r : reminders)
				{
					if(r.getId() == id)
					{
						if(args.length >= 2 && args[1].equals("stop"))
						{
							if(event.getUser().getNick().equals(r.getIssuedUser()))
							{
								if(channel.equals(r.getIssuedChannel()))
								{
									r.stop();
									Utilities.sendMessage(channel, L10N.getString("remind.stopped", channel).replace("#id", "" + r.getId()));
								}
								else
									Utilities.sendMessage(channel, L10N.getString("remind.wrongChannel", channel));
							}
							else
								Utilities.sendMessage(channel, L10N.getString("remind.notOwned", channel));
							
							return;
						}
						
						if(event.getUser().getNick().equals(r.getIssuedUser()))
						{
							if(channel.equals(r.getIssuedChannel()))
								Utilities.sendMessage(channel, L10N.getString("remind.timeLeft", channel).replace("#event", r.getEvent()).replace("#timeLeft", new TimeParser(getMainAlias()).lts(r.getRemainingTime(), "%sd %sh %sm %ss")));
							else
								Utilities.sendMessage(channel, L10N.getString("remind.wrongChannel", channel));
						}
						else
							Utilities.sendMessage(channel, L10N.getString("remind.notOwned", channel));
						return;
					}
				}
			}
			
			Utilities.sendMessage(channel, L10N.getString("remind.noId", channel));
		}
		catch(NumberFormatException ex)
		{
			if(args.length >= 2)
			{
				String e = "";

				for(int i = 1; i < args.length; i++)
				{
					e += args[i] + " ";
				}

				String ev = e.trim();
				long timeDue = 0;

				try
				{
					timeDue = new TimeParser(getMainAlias()).stl(args[0]) * 1000L;
				}
				catch(NumberFormatException ex1)
				{
					throw new IncorrectCommandExecutionException(getMainAlias());
				}
				catch(IncorrectCommandExecutionException ex2)
				{
					return;
				}

				Reminder reminder = new Reminder(event.getUser().getNick(), channel, ev, timeDue, false);
				
				Utilities.sendMessage(channel, L10N.getString("remind.success", channel).replace("#id", "" + reminder.getId()));
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
	public String getSyntax(String channel)
	{
		return "-remind <" + L10N.getString("cmd.help.time", channel) +"> <" + L10N.getString("cmd.help.sentence", channel) + ">";
	}

	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				"-remind 1d1h1m1s abc || " + L10N.getString("remind.explanation.1", channel),
				"-remind 5 || " + L10N.getString("remind.explanation.2", channel)
		};
	}
}
