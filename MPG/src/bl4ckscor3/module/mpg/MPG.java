package bl4ckscor3.module.mpg;

import java.net.URLClassLoader;

import bl4ckscor3.bot.bl4ckb0t.Module;



public class MPG extends Module {

	public MPG(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEnable(URLClassLoader loader) throws Exception {
		// TODO Auto-generated method stub
		getBuilder().registerChannelCommand(this, new ChannelCommand());
	}

	@Override
	public String[] getUsage(String channel) {
		// TODO Auto-generated method stub
		return null;
	}

}
