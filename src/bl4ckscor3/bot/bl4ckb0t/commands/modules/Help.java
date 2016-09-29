package bl4ckscor3.bot.bl4ckb0t.commands.modules;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.Modules;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Help extends Module
{
	public Help(String name)
	{
		super(name);
	}

	public void setup()
	{
		getBuilder().registerChannelCommand(this, new Command());
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"Help module explanation", "Line 1", "Line 2"}; //TODO: L10N
	}

	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String nick = event.getUser().getNick();

			if(args.length == 0)
			{
				String msg = "";
				int i = 0;

				Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + "----------Available modules----------"); //TODO: L10N

				for(Module m : Modules.modules)
				{
					if(Utilities.hasPermission(nick, m.getPermissionLevel()))
					{
						msg += (m.getPermissionLevel() == 3 ? Colors.RED : (m.getPermissionLevel() == 2 ? Colors.GREEN : "")) + m.getName() + Colors.NORMAL + " | ";
						i++;

						if(i % 10 == 0)
						{
							Utilities.sendMessage(nick, msg.trim());
							msg = "";
						}
					}
				}

				Utilities.sendMessage(nick, msg.substring(0, msg.lastIndexOf(" | ")));
				Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + "----------------------------------------------------------");
				Utilities.sendMessage(nick, "Write -help <module> to get more specific help on that module"); //TODO: L10N
				Thread.sleep(2000);
				Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + "----------Credits----------"); //TODO: L10N
				Utilities.sendMessage(nick, Colors.TEAL + "Made by bl4ckscor3!"); //TODO: L10N
				Utilities.sendMessage(nick, Colors.TEAL + "Initial help from Lord_Ralex and inspiration from Vauff, Geforce and akino_germany!"); //TODO: L10N
				Utilities.sendMessage(nick, Colors.TEAL + "Thanks Stackoverflow - It saved me many, many times!"); //TODO: L10N
				Utilities.sendMessage(nick, Colors.TEAL + "Compiled in Java 8 using PircBotX 2.1 (https://github.com/thelq/pircbotx) and its dependencies."); //TODO: L10N
				Utilities.sendMessage(nick, Colors.TEAL + "Suggestions are much appreciated! Please create a card on the Trello board if you want to submit one :) (-trello)"); //TODO: L10N
			}
			else if(args.length == 1)
			{
				for(Module m : Modules.modules)
				{
					if(m.getName().equalsIgnoreCase(args[0]))
					{
						if(Utilities.hasPermission(nick, m.getPermissionLevel()))
						{
							Utilities.sendMessage(nick, Colors.BOLD + Colors.OLIVE + "----------Help for: " + m.getName() + "----------"); //TODO: L10N
							Utilities.sendMessage(nick, "Channel commands: " ); //TODO: L10N

							if(m.getChannelCommands() != null)
							{
								for(BaseChannelCommand cmd : m.getChannelCommands())
								{
									String result = cmd.getSyntax();

									if(cmd.getAliases().length > 1) //there are aliases
									{
										result += " (Aliases: ";
										
										for(String s : cmd.getAliases())
										{
											if(s.equals(cmd.getMainAlias()))
												continue;

											result += s + ", ";
										}

										Utilities.sendMessage(nick, "		" + result.substring(0, result.lastIndexOf(",")) + ")");
									}
									
									Utilities.sendMessage(nick, "		" + result);
								}
							}
							else
								Utilities.sendMessage(nick, "		None."); //TODO: L10N

							Utilities.sendMessage(nick, "Private commands: " ); //TODO: L10N

							if(m.getPrivateCommands() != null)
							{
								for(BasePrivateCommand cmd : m.getPrivateCommands())
								{
									Utilities.sendMessage(nick, "		" + cmd.getSyntax());
								}
							}
							else
								Utilities.sendMessage(nick, "		None."); //TODO: L10N

							Utilities.sendMessage(nick, "Usage: ");

							for(String s : m.getUsage())
							{
								Utilities.sendMessage(nick, "		" + s);
							}

							Utilities.sendMessage(nick, "Notes: ");
							Utilities.sendMessage(nick, "		" + m.getNotes());
						}
						else
						{
							Utilities.sendMessage(nick, "No permission!"); //TODO: L10N
						}
					}
				}
			}
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"help"};
		}

		@Override
		public String getSyntax()
		{
			return Core.bot.getCmdPrefix() + "help <modulename>";
		}
	}
}
