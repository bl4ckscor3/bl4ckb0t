package bl4ckscor3.module.unleet;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Unleet extends Module
{
	private L10N l10n;
	
	public Unleet(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command(this));
		l10n = new L10N(this, loader);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	public class Command extends BaseChannelCommand
	{
		private Module module;
		
		public Command(Module m)
		{
			module = m;
		}
		
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String channel = event.getChannel().getName();
			
			if(args.length >= 1)
			{
				char[] chars = event.getMessage().substring(8).toCharArray();
				String result = "";

				for(int i = 0; i < chars.length; i++)
				{
					switch(chars[i])
					{
						case '4': result += "a"; break;
						case '3': result += "e"; break;
						case '6': result += "g"; break;
						case '1': result += "l"; break;
						case '0': result += "o"; break;
						case '5': result += "s"; break;
						case '7': result += "t"; break;
						case '2': result += "z"; break;
						default: result += chars[i];
					}
				}
				
				Utilities.sendMessage(channel, result);
			}
			else
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"unleet"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}