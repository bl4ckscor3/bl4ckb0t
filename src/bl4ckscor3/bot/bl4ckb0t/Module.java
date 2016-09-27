package bl4ckscor3.bot.bl4ckb0t;

import java.util.ArrayList;

import org.pircbotx.Configuration;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public abstract class Module
{
	private static final ArrayMap<Module, ArrayList<BaseChannelCommand>> channelCmds = new ArrayMap<Module, ArrayList<BaseChannelCommand>>();
	private static final ArrayMap<Module, ArrayList<BasePrivateCommand>> privateCmds = new ArrayMap<Module, ArrayList<BasePrivateCommand>>();
	
	/**
	 * Gets called to setup the module. Should be used to add any Listeners or commands
	 */
	public abstract void setup();
	
	/**
	 * Gets the Builder so any Listeners and commands can be added. DO NOT CALL THIS METHOD WHILE THE BOT IS RUNNING
	 * @return The builder
	 */
	public final Builder getBuilder()
	{
		return Core.modules.builder;
	}
	
	/**
	 * Checks wether this module has registered channel commands or not
	 * @return true if the module registered channel commands, false otherwise
	 */
	public final boolean hasChannelCommand()
	{
		return channelCmds.containsKey(this);
	}

	/**
	 * Gets all registered channel commands
	 * @return An ArrayList of all registered channel commands, null if none have been registered
	 */
	public final ArrayList<BaseChannelCommand> getChannelCommands()
	{
		return hasChannelCommand() ? channelCmds.get(this) : null;
	}
	
	/**
	 * Checks wether this module has registered private commands or not
	 * @return true if the module registered private commands, false otherwise
	 */
	public final boolean hasPrivateCommand()
	{
		return privateCmds.containsKey(this);
	}
	
	/**
	 * Gets all registered private commands
	 * @return An ArrayList of all registered private commands, null if none have been registered
	 */
	public final ArrayList<BasePrivateCommand> getPrivateCommands()
	{
		return hasPrivateCommand() ? privateCmds.get(this) : null;
	}
	
	public static class Builder extends Configuration.Builder
	{
		/**
		 * Registers a channel command to the specified module
		 * @param module The module to associate the command with
		 * @param cmd The command to register
		 */
		public void registerChannelCommand(Module module, BaseChannelCommand cmd)
		{
			ArrayList<BaseChannelCommand> list;
			
			if(channelCmds.containsKey(module))
				list = channelCmds.get(module);
			else
				list = new ArrayList<BaseChannelCommand>();
			
			list.add(cmd);
			channelCmds.put(module, list);
		}
		
		/**
		 * Registers a private command to the specified module
		 * @param module The module to associate the command with
		 * @param cmd The command to register
		 */
		public void registerPrivateCommand(Module module, BasePrivateCommand cmd)
		{
			ArrayList<BasePrivateCommand> list;
			
			if(privateCmds.containsKey(module))
				list = privateCmds.get(module);
			else
				list = new ArrayList<BasePrivateCommand>();
			
			list.add(cmd);
			privateCmds.put(module, list);
		}
	}
}
