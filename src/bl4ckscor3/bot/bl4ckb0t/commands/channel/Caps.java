package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.misc.SpellingCorrection;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Caps extends BaseChannelCommand<MessageEvent>
{
	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		String lastMessage = "";

		for(String s : SpellingCorrection.storage.get(event.getChannel().getName()))
		{
			if(s.split("#")[0].equals(event.getUser().getNick()))
				lastMessage = s.split("#")[1];
		}

		if(lastMessage.equals(""))
			return;

		StringBuilder builder = new StringBuilder();

		if(args.length == 1)
		{
			builder.append(lastMessage);

			for(int i = 0; i < builder.length(); i++)
			{
				char c = builder.charAt(i);

				if(Character.isLowerCase(c))
					builder.setCharAt(i, Character.toUpperCase(c));
				else if(Character.isUpperCase(c))
					builder.setCharAt(i, Character.toLowerCase(c));
			}
		}
		else
		{
			if(args[1].equals("up"))
				builder.append(lastMessage.toUpperCase());
			else if(args[1].equals("low"))
				builder.append(lastMessage.toLowerCase());
			else
				throw new IncorrectCommandExecutionException(getMainAlias());
		}

		Utilities.chanMsg(event, builder.toString());
		SpellingCorrection.updateLatestMessage(event.getChannel().getName(), builder.toString(), event.getUser().getNick());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"caps"};
	}

	@Override
	public String getSyntax(MessageEvent event)
	{
		return "-caps [up|low]";
	}

	@Override
	public String[] getUsage(MessageEvent event)
	{
		return new String[]{
				"-caps || " + L10N.getString("caps.explanation.1", event),
				"-caps up || " + L10N.getString("caps.explanation.2", event),
				"-caps low || " + L10N.getString("caps.explanation.3", event)
		};
	}
}
