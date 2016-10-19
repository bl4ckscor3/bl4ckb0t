package bl4ckscor3.module.csgonotification;

import java.net.URLClassLoader;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.github.sheigutn.pushbullet.Pushbullet;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Passwords;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class CSGONotification extends Module
{
	private L10N l10n;
	private Listener listener;
	
	public CSGONotification(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().addListener(listener = new Listener());
		l10n = new L10N(this, loader);
	}
	
	@Override
	public void onDisable()
	{
		getBuilder().removeListener(listener);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	public class Listener extends ListenerAdapter
	{
		@Override
		public void onMessage(MessageEvent event)
		{
			if(event.getUser().getNick().equals("Maunz") && event.getMessage().toLowerCase().contains("was pushed to the steam client!"))
			{
				new Pushbullet(Passwords.PUSHBULLETAPIKEY.getPassword()).pushNote("New CS:GO update!", event.getMessage().toLowerCase().contains("beta") ? "Beta" : "Release");
				Utilities.sendMessage(event.getChannel().getName(), "bl4ckscor3, Vauff, ^");
				return;
			}
		}
	}
}