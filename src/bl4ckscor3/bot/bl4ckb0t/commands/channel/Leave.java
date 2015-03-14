package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Leave implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws MalformedURLException, IOException, IncorrectCommandExecutionException
	{
		if(Utilities.isValidUser(event))
		{
			String[] args = Utilities.toArgs(event.getMessage());	

			if(args.length == 1)
				Utilities.leaveChannel(event.getChannel().getName());
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
				throw new IncorrectCommandExecutionException(getAlias());
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
