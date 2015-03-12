package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ListChans implements ICommand<MessageEvent<Bot>>
{
	public static int secretChannelAmount = 0;
	
	@Override
	public void exe(MessageEvent<Bot> event) throws Exception 
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] chans = Utilities.getJoinedChannels();
		
		if(args.length == 1)
		{
			StringBuilder builder = new StringBuilder();
			
			for(String s : chans)
			{
				if(s != null)
					builder.append(s + " | ");
			}
			
			builder.deleteCharAt(builder.length() - 1);
			builder.deleteCharAt(builder.length() - 1);
			builder.deleteCharAt(builder.length() - 1);
			
			if(secretChannelAmount != 0)
				builder.append(" + " + secretChannelAmount + " " + L10N.strings.getString("listchans.secret"));
			
			Utilities.chanMsg(event, L10N.strings.getString("listchans.list") + ": " + builder.toString());
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
		
		secretChannelAmount = 0;
	}
	
	@Override
	public String getAlias()
	{
		return "listchans";
	}

	@Override
	public String getSyntax()
	{
		return "-listchans";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-listchans || " + L10N.strings.getString("listchans.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
