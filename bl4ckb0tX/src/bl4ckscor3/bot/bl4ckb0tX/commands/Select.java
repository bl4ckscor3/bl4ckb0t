package bl4ckscor3.bot.bl4ckb0tX.commands;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0tX.util.Utilities;

public class Select implements ICommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event) 
	{
		Random r = new Random();
		String[] args = event.getMessage().substring(8).split(",");
		int i = r.nextInt(args.length);
		
		Utilities.respond(event, args[i], false);
	}

	@Override
	public String getAlias() 
	{
		return "select";
	}

	@Override
	public String getSyntax()
	{
		return "-select <options>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-select <options> || Selects an option for you."};
	}

	@Override
	public String getNotes()
	{
		return "Seperate multiple options with a comma. (e.g. -select 1,2,3,4,5)";
	}
}
