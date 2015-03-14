package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Event;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;

public interface ICommand<E extends Event<Bot>>
{
	public void exe(E event) throws IncorrectCommandExecutionException, MalformedURLException, IOException, InterruptedException, IrcException;

	public String getAlias();

	public String getSyntax();
	
	public String[] getUsage();
	
	public String getNotes();
}
