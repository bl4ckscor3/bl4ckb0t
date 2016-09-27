package bl4ckscor3.bot.bl4ckb0t;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

import org.pircbotx.Configuration;

import bl4ckscor3.bot.bl4ckb0t.logging.Logging;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class Modules
{
	public Configuration.Builder builder;
	
	public Modules(){}
	
	public Modules(Configuration.Builder c)
	{
		builder = c;
	}
	
	/**
	 * Loads all installed modules
	 */
	public Configuration.Builder init() throws URISyntaxException
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
					
					moduleClass.newInstance().setup();
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
}
