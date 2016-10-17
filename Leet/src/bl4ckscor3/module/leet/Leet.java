package bl4ckscor3.module.leet;

import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Leet extends Module
{
	private L10N l10n;
	
	public Leet(String name)
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
				char[] chars = event.getMessage().substring(6).toCharArray();
				String result = "";

				for(int i = 0; i < chars.length; i++)
				{
					switch(chars[i])
					{
						case 'A': case 'a': result += "4"; break;
						case 'E': case 'e': result += "3"; break;
						case 'G': case 'g': result += "6"; break;
						case 'L': case 'l': result += "1"; break;
						case 'O': case 'o': result += "0"; break;
						case 'S': case 's': result += "5"; break;
						case 'T': case 't': result += "7"; break;
						case 'Z': case 'z': result += "2"; break;
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
			return new String[]{"leet", "1337"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}