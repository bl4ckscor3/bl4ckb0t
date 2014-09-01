package bl4ckscor3.bot.bl4ckb0tX.commands;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

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
}
