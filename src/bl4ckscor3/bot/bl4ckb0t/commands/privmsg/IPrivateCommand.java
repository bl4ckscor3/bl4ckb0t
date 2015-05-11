package bl4ckscor3.bot.bl4ckb0t.commands.privmsg;

import java.io.IOException;
import java.net.MalformedURLException;

import org.pircbotx.hooks.Event;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;

public interface IPrivateCommand<E extends Event<Bot>>
{
	public void exe(E event) throws MalformedURLException, IOException;
	
	public String getAlias();
}
