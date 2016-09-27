package bl4ckscor3.bot.bl4ckb0t;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;

public class Listener extends ListenerAdapter
{
	@Override
	public void onMessage(MessageEvent event) throws Exception
	{
		for(Module m : Modules.modules)
		{
			if(m.hasChannelCommand())
			{
				for(BaseChannelCommand cmd : m.getChannelCommands())
				{
					if((Core.bot.getCmdPrefix() + cmd.getMainAlias()).equals(event.getMessage().split(" ")[0]))
						cmd.exe(event, event.getMessage().split(" "));
				}
			}
		}
	}
}
