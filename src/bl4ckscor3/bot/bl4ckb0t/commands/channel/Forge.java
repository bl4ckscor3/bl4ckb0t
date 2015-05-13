package bl4ckscor3.bot.bl4ckb0t.commands.channel;

import java.io.IOException;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.core.Bot;
import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;
import bl4ckscor3.bot.bl4ckb0t.localization.L10N;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Forge implements ICommand<MessageEvent<Bot>>
{
	@Override
	public void exe(MessageEvent<Bot> event) throws IOException, IncorrectCommandExecutionException
	{
		Utilities.chanMsg(event, L10N.getString("forge.disabled"));
//		String[] args = Utilities.toArgs(event.getMessage());
//
//		if(args.length == 3)
//		{
//			if(args[1].equalsIgnoreCase("latest") || args[1].equalsIgnoreCase("latets"))
//			{
//				if(args[2].equalsIgnoreCase("version"))
//				{
//					String result = createResult("<td>1.8-Latest</td>", "Version");
//
//					if(result.equalsIgnoreCase(createResult("<td>1.8-Recommended</td>", "Version")))
//						Utilities.chanMsg(event, result + " " + L10N.getString("forge.latest.recommended"));
//					else
//						Utilities.chanMsg(event, result);
//				}
//				else if(args[2].equalsIgnoreCase("changelog"))
//					Utilities.chanMsg(event, createResult("<td>1.8-Latest</td>", "Changelog"));
//				else if(args[2].equalsIgnoreCase("dlmain"))
//					Utilities.chanMsg(event, createResult("<td>1.8-Latest</td>", "Installer"));
//				else if(args[2].equalsIgnoreCase("dlsrc"))
//					Utilities.chanMsg(event, createResult("<td>1.8-Latest</td>", "Src"));
//				else
//					throw new IncorrectCommandExecutionException(getAlias());
//			}
//			else if(args[1].equalsIgnoreCase("rec") || args[1].equalsIgnoreCase("recommended"))
//			{
//				if(args[2].equalsIgnoreCase("version"))
//				{
//					String result = createResult("<td>1.8-Recommended</td>", "Version");
//
//					if(result.equalsIgnoreCase(createResult("<td>Latest-1.8</td>", "Version")))
//						Utilities.chanMsg(event, result + " " + L10N.getString("forge.recommended.latest"));
//					else
//						Utilities.chanMsg(event, result);
//				}
//				else if(args[2].equalsIgnoreCase("changelog"))
//					Utilities.chanMsg(event, createResult("<td>1.8-Recommended</td>", "Changelog"));
//				else if(args[2].equalsIgnoreCase("dlmain"))
//					Utilities.chanMsg(event, createResult("<td>1.8-Recommended</td>", "Installer"));
//				else if(args[2].equalsIgnoreCase("dlsrc"))
//					Utilities.chanMsg(event, createResult("<td>1.8-Recommended</td>", "Src"));
//				else
//					throw new IncorrectCommandExecutionException(getAlias());
//			}
//			else
//				throw new IncorrectCommandExecutionException(getAlias());
//		}
//		else
//			throw new IncorrectCommandExecutionException(getAlias());
	}

//	private String createResult(String type, String action) throws IOException
//	{
//		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://files.minecraftforge.net").openStream()));
//		String line;
//
//		if(action.equalsIgnoreCase("Version"))
//		{
//			while((line = reader.readLine()) != null)
//			{
//				if(line.contains(type))
//				{
//					line = reader.readLine();
//
//					String mcVersion = (reader.readLine()).split(">")[1].split("<")[0];
//
//					return line.split(">")[1].split("<")[0] + " (" + L10N.getString("forge.forVersion") + " " + mcVersion + ")";
//				}
//			}
//		}
//		else if(action.equalsIgnoreCase("Changelog"))
//		{
//			while((line = reader.readLine()) != null)
//			{
//				if(line.contains(type))
//				{
//					while((line = reader.readLine()) != null)
//					{
//						if(line.contains(action))
//							return line.split("\"")[1];
//					}
//				}
//			}
//		}
//		else
//		{
//			while((line = reader.readLine()) != null)
//			{
//				if(line.contains(type))
//				{
//					while((line = reader.readLine()) != null)
//					{
//						if(line.contains(action))
//						{
//							line = reader.readLine();
//							return line.split("\"")[1];
//						}
//					}
//				}
//			}
//		}
//		return L10N.getString("forge.fail");
//	}

	@Override
	public String getAlias()
	{
		return "forge";
	}

	@Override
	public String getSyntax()
	{
		return "-forge <latest|rec|recommended> <version|changelog|dlmain|dlsrc>";
	}

	@Override
	public String[] getUsage()
	{
		return new String[]
				{
				"-forge latest version || " + L10N.getString("forge.explanation.1"),
				"-forge latest changelog || " + L10N.getString("forge.explanation.2"),
				"-forge latest dlmain || " + L10N.getString("forge.explanation.3"),
				"-forge latest dlsrc || " + L10N.getString("forge.explanation.4"),
				"-forge <rec|recommended> version || " + L10N.getString("forge.explanation.5"),
				"-forge <rec|recommended> changelog || " + L10N.getString("forge.explanation.6"),
				"-forge <rec|recommended> dlmain || " + L10N.getString("forge.explanation.7"),
				"-forge <rec|recommended> dlsrc || " + L10N.getString("forge.explanation.8")
				};
	}

	@Override
	public String getNotes()
	{
		return null;
	}
}
