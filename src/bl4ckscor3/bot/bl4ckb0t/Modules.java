package bl4ckscor3.bot.bl4ckb0t;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.modules.Changelog;
import bl4ckscor3.bot.bl4ckb0t.modules.Help;
import bl4ckscor3.bot.bl4ckb0t.modules.Info;
import bl4ckscor3.bot.bl4ckb0t.modules.ModuleManagement;
import bl4ckscor3.bot.bl4ckb0t.modules.Update;
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
	public Module.Builder initPublic() throws URISyntaxException, MalformedURLException
	{
		File folder = new File(Utilities.getJarLocation() + "/modules");
		
		if(!folder.exists())
			folder.mkdirs();
		
		for(File f : folder.listFiles())
		{
			if(f.isFile() && f.getName().endsWith(".jar")) //it's likely a module
				loadModule(f.toURI().toURL(), f.getName().split("\\.")[0]);
		}
		
		return builder;
	}
	/**
	 * Loads all modules that are getting shipped with the bot
	 * @return The modified builder
	 */
	public Module.Builder initPrivate()
	{
		Module[] privateModules = {
				new Changelog("Changelog"),
				new Help("Help"),
				new ModuleManagement("ModuleManagement"),
				new Info("Info"),
				new Update("Update")
		};
		
		for(Module m : privateModules)
		{
			try
			{
				m.setup();
				modules.add(m);
				Logging.info("	Loaded module " + m.getName());
			}
			catch(Exception e)
			{
				Logging.warn("	Module " + m.getName() + " could not be loaded due to an error");
				Logging.stackTrace(e);
			}
		}
		
		return builder;
	}

	/**
	 * Loads a public module
	 * @param url The url to the file of the module (file.getURI().getURL())
	 * @param name The name of the file without the file type
	 * @return true if the module was loaded, false otherwise
	 */
	public boolean loadModule(URL url, String name)
	{
		String main = "bl4ckscor3.module." + name.toLowerCase() + "." + name;

		try
		{
			URLClassLoader loader = URLClassLoader.newInstance(new URL[]{url}, getClass().getClassLoader());
			Class<?> jarClass = Class.forName(main, true, loader);
			Class<? extends Module> moduleClass = jarClass.asSubclass(Module.class);
			Module module = moduleClass.getDeclaredConstructor(String.class).newInstance(name);
			
			loader.close();
			module.setup();
			
			if(modules.contains(module))
			{
				Logging.warn("Tried to load already loaded module.");
				return false;
			}
			
			modules.add(module);
			Logging.info("	Loaded module " + name);
			return true;
		}
		catch(ClassCastException e)
		{
			Logging.warn("	" + name + ": Main class does not extend bl4ckscor3.bot.bl4ckb0t.Module");
			Logging.stackTrace(e);
			return false;
		}
		catch(ClassNotFoundException e)
		{
			Logging.warn("	" + name + ": Couldn't find main class " + main);
			Logging.stackTrace(e);
			return false;
		}
		catch(Exception e)
		{
			Logging.warn("	Module " + name + " could not be loaded due to an error. Is it even a module?");
			Logging.stackTrace(e);
			return false;
		}
	}
}
