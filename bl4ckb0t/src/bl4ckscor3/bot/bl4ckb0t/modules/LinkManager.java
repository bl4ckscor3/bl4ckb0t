package bl4ckscor3.bot.bl4ckb0t.modules;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Core;
import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.LinkAction;

public class LinkManager extends Module
{
	private Listener listener;
	private static final List<LinkAction> linkActions = new ArrayList<LinkAction>();
	
	public LinkManager(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader) throws MalformedURLException, IOException
	{
		getBuilder().addListener(listener = new Listener());
	}
	
	@Override
	public void onDisable()
	{
		getBuilder().removeListener(listener);
	}
	
	@Override
	public void onFinish()
	{
		linkActions.sort(new LinkAction.LinkActionComparator());
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				Core.l10n.translate("linkmanager.explanation.1", channel),
				Core.l10n.translate("linkmanager.explanation.2", channel)
		};
	}
	
	/**
	 * Registers a LinkAction to the LinkManager
	 * Make sure to call removeLinkAction() once the LinkAction is no longer needed (for instance when the module gets disabled)
	 * @param la The LinkAction to register
	 */
	public static void registerLinkAction(LinkAction la)
	{
		linkActions.add(la);
	}
	
	/**
	 * Removes a LinkAction from the LinkManager
	 * @param la The LinkAction to remove
	 */
	public static void removeLinkAction(LinkAction la)
	{
		linkActions.remove(la);
	}
	
	/**
	 * Takes actions when specific links are sent
	 * @param message The message containing links
	 * @param channel The channel the message got sent in
	 * @param user The user the message got sent by
	 */
	public static void handleLink(String message, String channel, String user) throws Exception
	{
		outer:
		for(String s : message.split(" "))
		{
			s = Colors.removeFormattingAndColors(s);

			if(s.contains("www.") || s.contains("http://") || s.contains("https://"))
			{
				for(LinkAction la : linkActions)
				{
					if(la.isValid(channel, s))
					{
						la.handle(channel, user, s);
						continue outer;
					}
				}
			}
		}
	}
	
	public class Listener extends ListenerAdapter
	{
		@Override
		public void onMessage(MessageEvent event) throws Exception
		{
			if(event.getMessage().startsWith(Core.bot.getCmdPrefix()))
				return;
			
			try
			{
				handleLink(event.getMessage(), event.getChannel().getName(), event.getUser().getNick());
			}
			catch(Exception e)
			{
				Logging.stackTrace(e);
			}
		}
	}
}