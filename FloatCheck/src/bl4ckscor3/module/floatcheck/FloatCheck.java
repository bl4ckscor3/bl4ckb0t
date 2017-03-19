package bl4ckscor3.module.floatcheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import org.pircbotx.hooks.events.MessageEvent;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class FloatCheck extends Module
{
	private L10N l10n;
	
	public FloatCheck(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader)
	{
		getBuilder().registerChannelCommand(this, new ChannelCommand(this));
		l10n = new L10N(this, loader);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
	
	public class ChannelCommand extends BaseChannelCommand
	{
		private Module module;
		
		public ChannelCommand(Module m)
		{
			module = m;
		}
		
		@Override
		public void exe(MessageEvent event, String cmdName, String[] args) throws Exception
		{
			String channel = event.getChannel().getName();
			
			if(args.length != 1)
			{
				Utilities.sendHelp(module, event.getUser().getNick(), channel);
				return;
			}
			else if(!args[0].startsWith("steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20"))
			{
				Utilities.sendMessage(channel, l10n.translate("invalidUrl", channel));
				return;
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://api.csgofloat.com:1738/?url=" + args[0]).openStream()));
			JsonObject json = new JsonParser().parse(reader).getAsJsonObject();
			
			reader.close();
			
			if(json.has("error"))
				Utilities.sendMessage(channel, "" + json.get("error"));
			else
			{
				JsonObject info = json.get("iteminfo").getAsJsonObject();
				String stattraktm = !info.get("killeatervalue").toString().equals("null") ? "StatTrak™ " : "";
				
				Utilities.sendStarMsg(channel,
						l10n.translate("name", channel).replace("#name", stattraktm + info.get("weapon_type").toString() + " | " + info.get("item_name").toString()).replace("\"", ""),
						l10n.translate("floatvalue", channel).replace("#floatvalue", info.get("floatvalue").toString()),
						l10n.translate("credits", channel).replace("#link", "https://csgofloat.com"));
			}
		}

		@Override
		public String[] getAliases()
		{
			return new String[]{"float"};
		}

		@Override
		public String getSyntax(String channel)
		{
			return l10n.translate("syntax", channel);
		}
	}
}