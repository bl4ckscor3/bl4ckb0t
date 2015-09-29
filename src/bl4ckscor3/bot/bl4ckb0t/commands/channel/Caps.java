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
	
		char[] chars = lastMessage.toCharArray();
		String s = "";
		
		for(char c : chars)
		{
			switch(c)
			{
				case 'A': s += "a"; break;
				case 'B': s += "b"; break;
				case 'C': s += "c"; break;
				case 'D': s += "d"; break;
				case 'E': s += "e"; break;
				case 'F': s += "f"; break;
				case 'G': s += "g"; break;
				case 'H': s += "h"; break;
				case 'I': s += "i"; break;
				case 'J': s += "j"; break;
				case 'K': s += "k"; break;
				case 'L': s += "l"; break;
				case 'M': s += "m"; break;
				case 'N': s += "n"; break;
				case 'O': s += "o"; break;
				case 'P': s += "p"; break;
				case 'Q': s += "q"; break;
				case 'R': s += "r"; break;
				case 'S': s += "s"; break;
				case 'T': s += "t"; break;
				case 'U': s += "u"; break;
				case 'V': s += "v"; break;
				case 'W': s += "w"; break;
				case 'X': s += "x"; break;
				case 'Y': s += "y"; break;
				case 'Z': s += "z"; break;
				case 'a': s += "A"; break;
				case 'b': s += "B"; break;
				case 'c': s += "C"; break;
				case 'd': s += "D"; break;
				case 'e': s += "E"; break;
				case 'f': s += "F"; break;
				case 'g': s += "G"; break;
				case 'h': s += "H"; break;
				case 'i': s += "I"; break;
				case 'j': s += "J"; break;
				case 'k': s += "K"; break;
				case 'l': s += "L"; break;
				case 'm': s += "M"; break;
				case 'n': s += "N"; break;
				case 'o': s += "O"; break;
				case 'p': s += "P"; break;
				case 'q': s += "Q"; break;
				case 'r': s += "R"; break;
				case 's': s += "S"; break;
				case 't': s += "T"; break;
				case 'u': s += "U"; break;
				case 'v': s += "V"; break;
				case 'w': s += "W"; break;
				case 'x': s += "X"; break;
				case 'y': s += "Y"; break;
				case 'z': s += "Z"; break;
				default: s += c;
			}
		}
		
		Utilities.chanMsg(event, s);
		SpellingCorrection.updateLatestMessage(event.getChannel().getName(), s, event.getUser().getNick());
	}

	@Override
	public String getAlias()
	{
		return "caps";
	}

	@Override
	public String getSyntax()
	{
		return "caps";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]{"caps || " + L10N.getString("caps.explanation")};
	}

	@Override
	public String getNotes()
	{
		return null;
	}

	@Override
	public int getPermissionLevel()
	{
		return 0;
	}
}
