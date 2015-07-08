package bl4ckscor3.bot.bl4ckb0t.exception;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;

public class IncorrectCommandExecutionException extends Exception
{
	private static final long serialVersionUID = 878983756719571661L;

	public IncorrectCommandExecutionException(String cmdName)
	{
		super("Incorrect command syntax for command: " + cmdName);
		
		Logging.severe("Command execution failed: " + cmdName);
	}
}
