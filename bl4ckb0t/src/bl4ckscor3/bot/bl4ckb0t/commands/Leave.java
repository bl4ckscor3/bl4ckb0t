package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Core;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Leave implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception
	{
		if(Utilities.isValidUser(event))
		{
			String[] args = Utilities.toArgs(event.getMessage());	

			if(args.length == 1)
				Core.bot.sendRaw().rawLine("PART " + event.getChannel().getName() + " :" + L10N.strings.getString("leave.reason"));
			else if(args.length == 2)
			{
				if(args[1].equals("d"))
				{
					Utilities.chanMsg(event, L10N.strings.getString("leave.success.default"));
					
					for(String s : Utilities.getDefaultChans())
					{	
						Utilities.leaveChannel(s);
					}
				}
				
				if(!args[1].startsWith("#"))
					args[1] = "#" + args[1];
				
				if(Utilities.hasJoinedChannel(args))
				{
					Utilities.chanMsg(event, L10N.strings.getString("leave.success.normal") + " " + Colors.BOLD + args[1] + Colors.BOLD + "!");
					Utilities.leaveChannel(args[1]);
				}
				else
					Utilities.chanMsg(event, L10N.strings.getString("leave.notJoined"));
			}
			else
				Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
		}
		else
			Utilities.sorry(event);
	}

	@Override
	public String getAlias()
	{
		return "leave";
	}

	@Override
	public String getSyntax()
	{
		return "-leave [" + L10N.strings.getString("cmd.help.channel") + "]";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-leave || " + L10N.strings.getString("leave.explanation.1"),
				"-leave <" + L10N.strings.getString("cmd.help.channel") + "> || " + L10N.strings.getString("leave.explanation.2")
				};
	}

	@Override
	public String getNotes()
	{
		return L10N.strings.getString("notes.onlyOp");
	}
}
