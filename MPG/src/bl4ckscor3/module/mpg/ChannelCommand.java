package bl4ckscor3.module.mpg;

import org.pircbotx.hooks.events.MessageEvent;

import bl4ckscor3.bot.bl4ckb0t.commands.BaseChannelCommand;
import bl4ckscor3.bot.bl4ckb0t.util.Utilities;

public class ChannelCommand extends BaseChannelCommand {

	@Override
	public String[] getAliases() {
		return new String[]{"mpg"};
	}

	@Override
	public String getSyntax(String channel) {
		// TODO Auto-generated method stub
		return null;
	}

	public void exe(MessageEvent event, String cmdName, String[] args) throws Exception {
		String channel = event.getChannel().getName();
		String result = "";
		float calc = 1.0f;
		float calc2 = 2.0f;
		if(args.length == 2) {
			if(args[0].equals("us")) {
				calc = 235/(Float.parseFloat(args[1]));
				result = String.format("%s mpg (US) sind %f l/100km", args[1], calc);
				Utilities.sendMessage(channel, result);
			} else if(args[0].equals("imp")) {
				calc = 282/(Float.parseFloat(args[1]));
				result = String.format("%s mpg (Imperial) sind %f l/100km", args[1], calc);
				Utilities.sendMessage(channel, result);
			}
		} else if(args.length == 1) {
			calc = 235/(Float.parseFloat(args[0]));
			calc2 = 282/(Float.parseFloat(args[0]));
			result = String.format("%s l/100km sind %f mpg (US) und %f mpg (Imperial) ", args[0], calc, calc2);
			Utilities.sendMessage(channel, result);
		}
		
	}

}
