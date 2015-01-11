package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ListChans implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception 
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
			Utilities.chanMsg(event, "I joined these channels: " + builder.toString());
		}
		else
			Utilities.sendHelp(event.getUser().getNick(), getSyntax(), getUsage(), getNotes());
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
		return new String[]{"-listchans || Lists the channels the bot is currently in."};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
