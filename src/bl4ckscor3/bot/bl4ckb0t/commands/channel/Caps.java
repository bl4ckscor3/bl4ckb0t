package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.misc.SpellingCorrection;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Caps implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws Exception
	{
		String lastMessage = "";
		
		for(String s : SpellingCorrection.storage.get(event.getChannel().getName()))
		{
			if(s.split("#")[0].equals(event.getUser().getNick()))
				lastMessage = s.split("#")[1];
		}
		
		if(lastMessage.equals(""))
			return;
	
		StringBuilder builder = new StringBuilder(lastMessage);
		
		for(int i = 0; i < builder.length(); i++)
		{
			char c = builder.charAt(i);
			
			if(Character.isLowerCase(c))
				builder.setCharAt(i, Character.toUpperCase(c));
			else if(Character.isUpperCase(c))
				builder.setCharAt(i, Character.toLowerCase(c));
		}
		
		Utilities.chanMsg(event, builder.toString());
		SpellingCorrection.updateLatestMessage(event.getChannel().getName(), builder.toString(), event.getUser().getNick());
	}

	@Override
	public String getAlias()
	{
		return "caps";
	}

	@Override
	public String getSyntax()
	{
		return "-caps <" + L10N.getString("cmd.help.sentence") + ">";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"-caps <" + L10N.getString("cmd.help.sentence") + "> || " + L10N.getString("caps.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}

	@Override
	public int getPermissionLevel()
	{
		return 1;
	}
}
