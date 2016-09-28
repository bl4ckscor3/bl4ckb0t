package bl4ckscor3.bot.bl4ckb0t;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import bl4ckscor3.bot.bl4ckb0t.commands.modules.Changelog;
import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Modules
{
	public static final ArrayList<Module> modules = new ArrayList<Module>();
	public Module.Builder builder;
	
	public Modules(){}
	
	public Modules(Module.Builder c)
	{
		builder = c;
	}
	
	/**
	 * Loads all installed modules
	 * @return The modified builder
	 */
	public Module.Builder initPublic() throws URISyntaxException
	{
		File folder = new File(Utilities.getJarLocation() + "/modules");
		
		if(!folder.exists())
			folder.mkdirs();
		
		for(File f : folder.listFiles())
		{
			if(f.isFile() && f.getName().endsWith(".jar")) //it's likely a module
			{
				String main = "bl4ckscor3.module." + f.getName().split("\\.")[0].toLowerCase() + "." + f.getName().split("\\.")[0];
				
				try
				{
					ClassLoader loader = URLClassLoader.newInstance(new URL[]{f.toURI().toURL()}, getClass().getClassLoader());
					Class<?> jarClass = Class.forName(main, true, loader);
					Class<? extends Module> moduleClass = jarClass.asSubclass(Module.class);
					Module module = moduleClass.newInstance();
					
					module.setup();
					modules.add(module);
					Logging.info("Loaded module " + f.getName().split("\\.")[0]);
				}
				catch(ClassCastException e)
				{
					Logging.warn("Main class does not extend bl4ckscor3.bot.bl4ckb0t.Module.class");
					Logging.stackTrace(e);
					continue;
				}
				catch(ClassNotFoundException e)
				{
					Logging.warn("Couldn't find main class " + main);
					Logging.stackTrace(e);
					continue;
				}
				catch(Exception e)
				{
					Logging.warn("Module " + f.getName() + " could not be loaded due to an error. Is it even a module?");
					Logging.stackTrace(e);
					continue;
				}
			}
		}
		
		return builder;
	}
	
	/**
	 * Loads all modules that are getting shipped with the bot
	 * @return The modified builder
	 */
	public Module.Builder initPrivate()
	{
		try
		{
			Changelog module = new Changelog();
			
			module.setup();
			modules.add(module);
			Logging.info("Loaded module Changelog");
		}
		catch(Exception e)
		{
			Logging.warn("Module Changelog could not be loaded due to an error. Is it even a module?");
			Logging.stackTrace(e);
		}
		
		return builder;
	}
}
