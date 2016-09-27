package bl4ckscor3.bot.bl4ckb0t;

import org.pircbotx.Configuration;

public abstract class Module
{
	/**
	 * Gets called to setup the module. Should be used to add any Listeners or commands
	 */
	public abstract void setup();
	
	/**
	 * Gets the Builder so any Listeners can be added. DO NOT CALL THIS METHOD WHILE THE BOT IS RUNNING
	 * @return The builder
	 */
	public final Configuration.Builder getBuilder()
	{
		return Core.modules.builder;
	}
}
