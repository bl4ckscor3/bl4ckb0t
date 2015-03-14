package bl4ckscor3.bot.bl4ckb0t.exception;

public class IncorrectCommandExecutionException extends Exception
{
	public IncorrectCommandExecutionException(String cmdName)
	{
		super("Incorrect command syntax for command: " + cmdName);
	}
}
