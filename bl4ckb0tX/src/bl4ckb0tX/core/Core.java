package bl4ckb0tX.core;

import java.io.IOException;
import java.net.InetAddress;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Core
{	
	public static PircBotX bot;
	
	public static void main(String args[]) throws Exception
	{
		Configuration config = new Configuration.Builder()	
		.setName("bl4ckb0t")
		.setVersion("1.0")
		.setServerHostname("aperture.esper.net")
		.setServerPort(6667)
		.setLogin("bl4ckb0t")		
//		.setLocalAddress(InetAddress.getByName("bots.tehkitti.pw"))
		.addAutoJoinChannel("#bl4ckscor3")
		.setNickservPassword("xxx")
		.setAutoNickChange(true)
		.setCapEnabled(true)
		.addListener(new Listener())
		.setMessageDelay(0)
		.buildConfiguration();
		
		bot = new PircBotX(config);
		bot.startBot();
	}
	
	public static void main2() throws IOException, IrcException
	{
		Configuration config = new Configuration.Builder()	
		.setName("bl4ckb0t")
		.setVersion("1.0")
		.setServerHostname("aperture.esper.net")
		.setServerPort(6667)
		.setLogin("bl4ckb0t")
//		.setLocalAddress(InetAddress.getByName("bots.tehkitti.pw"))
		.addAutoJoinChannel("#bl4ckscor3")
		.setNickservPassword("xxx")
		.setAutoNickChange(true)
		.setCapEnabled(true)
		.addListener(new Listener())
		.setMessageDelay(0)
		.buildConfiguration();
		
		bot = new PircBotX(config);
		bot.startBot();
	}
}
