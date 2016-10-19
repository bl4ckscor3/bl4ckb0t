package bl4ckscor3.bot.bl4ckb0t;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.modules.Changelog;
import bl4ckscor3.bot.bl4ckb0t.modules.Help;
import bl4ckscor3.bot.bl4ckb0t.modules.Info;
import bl4ckscor3.bot.bl4ckb0t.modules.LinkManager;
import bl4ckscor3.bot.bl4ckb0t.modules.ModuleManagement;
import bl4ckscor3.bot.bl4ckb0t.modules.Source;
import bl4ckscor3.bot.bl4ckb0t.modules.Stop;
import bl4ckscor3.bot.bl4ckb0t.modules.Update;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Modules
{
	public static final ArrayList<Module> modules = new ArrayList<Module>();
	public Module.Builder builder;

	public Modules(Module.Builder c)
	{
		builder = c;
	}

	/**
	 * Loads all installed modules
	 * @return The modified builder
	 */
	public Module.Builder initPublic() throws URISyntaxException, IOException
	{
		File folder = new File(Utilities.getJarLocation() + "/modules");

		if(!folder.exists())
			folder.mkdirs();

		for(File f : folder.listFiles())
		{
			if(f.isFile() && f.getName().endsWith(".jar")) //it's likely a module
				loadModule(f.toURI().toURL(), f.getName().split("\\.")[0]);
		}

		for(Module m : modules)
		{
			m.onFinish();
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
				new LinkManager("LinkManager"),
				new Source("Source"),
				new Stop("Stop"),
				new Update("Update")
		};

		for(Module m : privateModules)
		{
			try
			{
				m.onEnable(null); //no need for a class loader because the language files are already loaded
				modules.add(m);
				Logging.info("  Loaded module " + m.getName());
			}
			catch(Exception e)
			{
				Logging.warn(" Module " + m.getName() + " could not be loaded due to an error");
				Logging.stackTrace(e);
			}
		}

		return builder;
	}

	/**
	 * Loads a public module
	 * @param url The url to the file of the module (file.getURI().getURL())
	 * @param name The name of the file without the file type
	 * @return 1 if the module was loaded, 0 if it was already loaded, -1 if an error occured
	 */
	public int loadModule(URL url, String name) throws IOException
	{
		if(isLoaded(name))
			return 0;
		
		String main = "bl4ckscor3.module." + name.toLowerCase() + "." + name;
		URLClassLoader loader = null;
		
		try
		{
			loader = URLClassLoader.newInstance(new URL[]{url}, getClass().getClassLoader());
			
			Module module = Class.forName(main, true, loader).asSubclass(Module.class).getDeclaredConstructor(String.class).newInstance(name);
			
			module.onEnable(loader);

			if(modules.contains(module))
			{
				Logging.warn("Tried to load already loaded module.");
				return 0;
			}

			modules.add(module);
			Logging.info("  Loaded module " + name);
			return 1;
		}
		catch(ClassCastException e)
		{
			Logging.warn("  " + name + ": Main class does not extend bl4ckscor3.bot.bl4ckb0t.Module");
			return -1;
		}
		catch(ClassNotFoundException e)
		{
			Logging.warn("  " + name + ": Couldn't find main class " + main);
			return -1;
		}
		catch(Exception e)
		{
			Logging.warn("  Module " + name + " could not be loaded due to an error. Is it even a module?");
			Logging.stackTrace(e);
			return -1;
		}
		finally
		{
			if(loader != null)
				loader.close();
		}
	}

	/**
	 * Checks wether a module with the given name is loaded
	 * @param name The module name to check
	 * @return true if the module is loaded, false otherwise
	 */
	public static boolean isLoaded(String name)
	{
		for(Module m : modules)
		{
			if(m.getName().equalsIgnoreCase(name))
				return true;
		}

		return false;
	}
}
