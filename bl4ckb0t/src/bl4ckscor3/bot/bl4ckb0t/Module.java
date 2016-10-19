package bl4ckscor3.bot.bl4ckb0t;

import java.net.URLClassLoader;
import java.util.ArrayList;

import org.pircbotx.Configuration;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.commands.BasePrivateCommand;
import bl4ckscor3.bot.bl4ckb0t.util.android.ArrayMap;

public abstract class Module
{
	private static final ArrayMap<Module, ArrayList<BaseChannelCommand>> channelCmds = new ArrayMap<Module, ArrayList<BaseChannelCommand>>();
	private static final ArrayMap<Module, ArrayList<BasePrivateCommand>> privateCmds = new ArrayMap<Module, ArrayList<BasePrivateCommand>>();
	/**The name of the module. For public modules it's the file name, private modules have their name predefined*/
	private String name;
	
	/**
	 * Constructor
	 * @param n The name of the module
	 */
	public Module(String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets called when the module gets enabled. Should be used to add any Listeners or commands
	 * @param loader The loader with which to load the language files
	 */
	public abstract void onEnable(URLClassLoader loader) throws Exception;
	
	/**
	 * Gets called when the module gets disabled. Should be used to remove any Listeners
	 */
	public void onDisable(){}
	
	/**
	 * Gets called when all modules have been loaded
	 */
	public void onFinish(){}
	
	/**
	 * Gets called when the -update command gets executed
	 */
	public void onUpdate(){}
	
	/**
	 * Explanation of the module, gets shown in the help menu.
	 * @param event The channel the help command was used in
	 */
	public abstract String[] getUsage(String channel);
	
	/**
	 * Anything special the user needs to know about the module, gets shown in the help menu
	 * @param event The channel the help command was used in
	 */
	public String getNotes(String channel)
	{
		return Core.l10n.translate("help.none", channel).trim();
	}
	
	/**
	 * Which users can use this module?
	 * @return 3 = Only valid users
	 * 			2 = Valid and allowed users
	 * 			1 = Everybody (Default)
	 */
	public int getPermissionLevel()
	{
		return 1;
	}
	
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
	
	/**
	 * Gets the name of this module
	 * @return The name of this module
	 */
	public String getName()
	{
		return name;
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
		
		/**
		 * Shorthand method to remove a listener, without needing to go through PircBotX' method
		 * Removes a listener
		 * @param listener The listener to remove
		 */
		public void removeListener(org.pircbotx.hooks.Listener listener)
		{
			getListenerManager().removeListener(listener);
		}
	}
}
