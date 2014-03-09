package bl4ckb0tX.commands.normal;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckb0tX.util.Stuffz;

public class McfUser
{
	@SuppressWarnings("rawtypes")
	public static void exe(MessageEvent event)
	{
		String splitUserTaskFromCommand = Stuffz.getMessage(event).substring(2);
		String correctSplitFromCommand = splitUserTaskFromCommand.substring(1);
		String[] splitUserFromTask = correctSplitFromCommand.split(" ");
		String user = splitUserFromTask[0];
		String task = splitUserFromTask[1];
		
		event.respond("http://u.mcf.li/" + user + "/" + task);
	}
}

//pull request test comment
