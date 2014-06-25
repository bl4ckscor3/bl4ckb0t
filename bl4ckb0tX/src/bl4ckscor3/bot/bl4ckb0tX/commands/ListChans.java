package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class ListChans implements Command<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) throws Exception 
	{
		String[] args = Utilities.toArgs(event.getMessage());
		String[] chans = Utilities.getJoinedChannels();
		
		if(args.length > 1)
			Utilities.chanMsg(event, "No need for additional arguments :)");
		else
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
	}
	
	@Override
	public String getAlias()
	{
		return "listchans";
	}
}
