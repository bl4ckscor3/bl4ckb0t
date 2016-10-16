package bl4ckscor3.bot.bl4ckb0t;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.modules.LinkManager;
import bl4ckscor3.bot.bl4ckb0t.util.Lists;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Listener extends ListenerAdapter
{
	@Override
	public void onMessage(MessageEvent event) throws Exception
	{
		String cmdName = event.getMessage().split(" ")[0];

		if(!cmdName.startsWith(Core.bot.getCmdPrefix()))
			return;
		
		if(Lists.isIgnored(event.getUser().getNick()))
		{
			Logging.warn("Ignoring user " + event.getUser().getNick());
			return;
		}
		
		cmdName = cmdName.replace(Core.bot.getCmdPrefix(), "");
		
		for(Module m : Modules.modules)
		{
			if(m.hasChannelCommand())
			{
				for(BaseChannelCommand cmd : m.getChannelCommands())
				{
					if(cmd.isValidAlias(cmdName))
					{
						try
						{
							if(Utilities.hasPermission(event.getUser().getNick(), m.getPermissionLevel()))
								cmd.exe(event, cmdName, Utilities.toArgs(event.getMessage()));
						}
						catch(Exception e)
						{
							Logging.stackTrace(e);
						}
						
						return;
					}
				}
			}
		}
	}
	
	@Override
	public void onAction(ActionEvent event) throws Exception
	{
		if(Lists.isIgnored(event.getUser().getNick()))
		{
			Logging.warn("Ignoring user " + event.getUser().getNick());
			return;
		}
		
		LinkManager.handleLink(event.getMessage(), event.getChannel().getName(), event.getUser().getNick());
	}
	
	@Override
	public void onPrivateMessage(PrivateMessageEvent event) throws Exception
	{
		if(!Lists.isLvl3User(event.getUser().getNick()))
			return;
		
		String cmdName = event.getMessage().split(" ")[0];

		for(Module m : Modules.modules)
		{
			if(m.hasPrivateCommand())
			{
				for(BasePrivateCommand cmd : m.getPrivateCommands())
				{
					if(cmdName.equals(cmd.getMainAlias()))
					{
						try
						{
							cmd.exe(event, cmdName, Utilities.toArgs(event.getMessage()));
						}
						catch(Exception e)
						{
							Logging.stackTrace(e);
						}
						
						return;
					}
				}
			}
		}
	}
	
	@Override
	public void onConnect(ConnectEvent event)
	{
		try
		{
			Logging.info("Getting default channels");
			Startup.setDefaultChans();
			Logging.info("Joining default channels");
			Core.bot.joinDefaults();
			Logging.info("Connected to server");
		}
		catch(Exception e)
		{
			Logging.stackTrace(e);
		}
	}
}
