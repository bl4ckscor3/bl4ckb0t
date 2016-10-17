package bl4ckscor3.module.remind;

import java.net.URLClassLoader;
import java.util.ArrayList;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.TimeParser;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Remind extends Module
{
	public static final ArrayList<Reminder> reminders = new ArrayList<Reminder>();
	private L10N l10n;
	
	public Remind(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader) throws Exception
	{
		getBuilder().registerChannelCommand(this, new Command(this));
		l10n = new L10N(this, loader);
		Reminder.loadReminders(l10n);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation.1", channel),
				l10n.translate("explanation.2", channel),
				l10n.translate("explanation.3", channel)
		};
	}
	
	public class Command extends BaseChannelCommand
	{
		private Module module;		
		public Command(Module m)
		{
			module = m;
		}
		
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
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
					Utilities.sendMessage(channel, l10n.translate("amount", channel).replace("#amount", "" + reminders.size()).replace("#ids", ids));
				}
				else
					Utilities.sendMessage(channel, l10n.translate("none", channel));
				
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
										Utilities.sendMessage(channel, l10n.translate("stopped", channel).replace("#id", "" + r.getId()));
									}
									else
										Utilities.sendMessage(channel, l10n.translate("wrongChannel", channel));
								}
								else
									Utilities.sendMessage(channel, l10n.translate("notOwned", channel));
								
								return;
							}
							
							if(event.getUser().getNick().equals(r.getIssuedUser()))
							{
								if(channel.equals(r.getIssuedChannel()))
									Utilities.sendMessage(channel, l10n.translate("timeLeft", channel).replace("#event", r.getEvent()).replace("#timeLeft", TimeParser.lts(r.getRemainingTime(), "%sd %sh %sm %ss")));
								else
									Utilities.sendMessage(channel, l10n.translate("wrongChannel", channel));
							}
							else
								Utilities.sendMessage(channel, l10n.translate("notOwned", channel));
							return;
						}
					}
				}
				
				Utilities.sendMessage(channel, l10n.translate("noId", channel));
			}
			catch(NumberFormatException e)
			{
				if(args.length >= 2)
				{
					String s = "";

					for(int i = 1; i < args.length; i++)
					{
						s += args[i] + " ";
					}

					String ev = s.trim();
					long timeDue = 0;

					try
					{
						timeDue = TimeParser.stl(args[0]) * 1000L;
					}
					catch(NumberFormatException ex)
					{
						Utilities.sendHelp(module, event.getUser().getNick(), channel);
					}

					Reminder reminder = new Reminder(event.getUser().getNick(), channel, ev, timeDue, false);
					
					Utilities.sendMessage(channel, l10n.translate("success", channel).replace("#id", "" + reminder.getId()));
					reminders.add(reminder);
				}
				else
					Utilities.sendHelp(module, event.getUser().getNick(), channel);
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
			return l10n.translate("syntax", channel);
		}
	}
}