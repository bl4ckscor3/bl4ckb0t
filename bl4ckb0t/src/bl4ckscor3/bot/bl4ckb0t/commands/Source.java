package bl4ckscor3.bot.bl4ckb0t.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Source implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event)
	{
		Utilities.chanMsg(event, "Here it is: https://github.com/bl4ckscor3/bl4ckb0tX");
	}
	
	@Override
	public String getAlias()
	{
		return "source";
	}

	@Override
	public String getSyntax()
	{
		return "-source";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-source || Gives you the link to the sourcecode of the bot."};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
