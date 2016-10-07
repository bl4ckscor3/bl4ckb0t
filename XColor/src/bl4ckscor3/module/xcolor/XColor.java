package bl4ckscor3.module.xcolor;

import java.net.URLClassLoader;

import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class XColor extends Module
{
	private L10N l10n;
	
	public XColor(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new Command());
		l10n = new L10N(this, loader);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	@Override
	public String getNotes(String channel)
	{
		return l10n.translate("notes", channel);
	}
	
	public class Command extends BaseChannelCommand
	{
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String[] colors = {
					"Grey",
					"Black",
					"Blue",
					"Dark Green",
					"Red",
					"Red",
					"Purple",
					"Brown",
					"Yellow",
					"Light Green",
					"Dark Aqua",
					"Lighter Aqua",
					"Pink"
			};
			User user = event.getUser();

			for(int i = 0; i < 14; i++)
			{
				if(i < 4)
					Utilities.notice(user, "%C" + i + ": " + colors[i]);
				else if(i > 4 && i < 12)
					Utilities.notice(user, "%C" + i + ": " + colors[i]);
				else if(i == 13)
					Utilities.notice(user, "%C" + i + ": " + colors[i - 1]);
			}
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"xcolor", "xc"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}