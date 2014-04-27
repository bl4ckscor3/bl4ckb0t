package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Filter implements Command<MessageEvent>
{

	@Override
	public void exe(MessageEvent event) throws Exception 
	{
		String[] args = Utilities.toArgs(event.getMessage());
		StringBuilder builder = new StringBuilder();
		char[][] chars = new char[args.length][];
		int total = 0;
		int counter = 0; 

		for(int i = 2; i < args.length; i++)
		{
			chars[i] = args[i].toCharArray();
		}
		
		for(int i = 2; i < args.length; i++)
		{
		    total = total + chars[i].length;
		}

		char[] allChars = new char[total];
		
		for (int i = 2; i < chars.length; i++) 
		{ 
		    for (int j = 0; j < chars[i].length; j++) 
		    {
		    	allChars[counter++] = chars[i][j];
		    }
		}
		
		for(char c : allChars)
		{
			if(c == args[1].toCharArray()[0])
				builder.append("");
			else
				builder.append(c);
		}
		
		Utilities.chanMsg(event, builder.toString());
	}

	@Override
	public String getAlias() 
	{
		return "filter";
	}

}
