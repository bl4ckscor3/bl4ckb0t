package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Event;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;

public interface ICommand<E extends Event<Bot>>
{
	/**What happens when the command gets executed*/
	public void exe(E event) throws IncorrectCommandExecutionException, MalformedURLException, IOException, InterruptedException, IrcException;

	/**The command exclusive the prefix*/
	public String getAlias();

	/**How the command can be used. Gets shown in the help menu.*/
	public String getSyntax();
	
	/**Explanation of the command. Gets shown in the help menu.*/
	public String[] getUsage();
	
	/**Anything special the user needs to know about the command. Gets shown in the help menu.*/
	public String getNotes();
	
	/**Which user can issue this command? 3 = Only valid users | 2 = Valid and allowed users | 1 = Everybody*/
	public int getPermissionLevel();
}
