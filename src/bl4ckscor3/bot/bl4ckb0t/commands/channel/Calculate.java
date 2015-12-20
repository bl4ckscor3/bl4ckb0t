package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Calculate implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IncorrectCommandExecutionException
	{
		Utilities.chanMsg(event, L10N.getString("calc.disabled", event));
//		String[] args = Utilities.toArgs(event.getMessage());
//		
//		if(args.length == 1 || args.length == 2 || args.length == 3 || !validateInput(args))
//			throw new IncorrectCommandExecutionException(getAlias());
	}

//	private static boolean validateInput(String[] args)
//	{
//		if(!isCalcOperator(args[args.length - 1]))
//		{
//			for(int i = 1; i < args.length; i++)
//			{
//				if(i % 2 == 0) //if the number is even we expect an operation symbol (+, -, *)
//				{
//					if(isCalcOperator(args[i]))
//						continue;
//				}
//				else //if the number is odd we expect a number
//				{
//					if(!Double.isNaN(Double.parseDouble(args[i])))
//						continue;
//				}
//
//				return false;
//			}
//			
//			return true;
//		}
//		else
//			return false;
//	}
//	
//	private static boolean isCalcOperator(String s)
//	{
//		if(s.equals("+") || s.equals("-") || s.equals("*"))
//			return true;
//		return false;
//	}

	@Override
	public String getAlias()
	{
		return "calc";
	}

	@Override
	public String getSyntax(MessageEvent<Bot> event) 
	{
		return "";
	}

	@Override
	public String[] getUsage(MessageEvent<Bot> event) 
	{
		return new String[]{};
	}

	@Override
	public String getNotes(MessageEvent<Bot> event) 
	{
		return null;
	}
}
