package bl4ckscor3.module.shrugs;

import java.net.URLClassLoader;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Shrugs extends Module
{
	private L10N l10n;
	private Listener listener;
	
	public Shrugs(String name)
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
		public void onAction(ActionEvent event) throws Exception
		{
			if(event.getAction().startsWith("shrugs"))
				Utilities.sendMessage(event.getChannel().getName(), "¯\\_(ツ)_/¯");
		}
	}
}