package bl4ckscor3.bot.bl4ckb0t;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Listener extends ListenerAdapter
{
	@Override
	public void onMessage(MessageEvent event) throws Exception
	{
		String cmdName = event.getMessage().split(" ")[0];

		if(!cmdName.startsWith(Core.bot.getCmdPrefix()))
			return;
		
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
							cmd.exe(event, cmdName, Utilities.toArgs(event.getMessage()));
						}
						catch(Exception e)
						{
							Logging.stackTrace(e);
						}
					}
				}
			}
		}
	}
}
