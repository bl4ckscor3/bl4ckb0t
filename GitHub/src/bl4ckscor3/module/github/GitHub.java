package bl4ckscor3.module.github;

import java.io.IOException;
import java.net.URLClassLoader;

import bl4ckscor3.bot.bl4ckb0t.Module;
import bl4ckscor3.bot.bl4ckb0t.l10n.L10N;
import bl4ckscor3.bot.bl4ckb0t.modules.LinkManager;
import bl4ckscor3.bot.bl4ckb0t.util.LinkAction;

public class GitHub extends Module
{
	private L10N l10n;
	private LinkAction commit;
	private LinkAction issue;
	private LinkAction repo;
	
	public GitHub(String name)
	{
		super(name);
	}

	@Override
	public void onEnable(URLClassLoader loader) throws IOException
	{
		l10n = new L10N(this, loader);
		LinkManager.registerLinkAction(commit = new Commit(l10n));
		LinkManager.registerLinkAction(issue = new Issue(l10n));
		LinkManager.registerLinkAction(repo = new Repo(l10n));
	}
	
	@Override
	public void onDisable()
	{
		LinkManager.removeLinkAction(commit);
		LinkManager.removeLinkAction(issue);
		LinkManager.removeLinkAction(repo);
	}
	
	@Override
	public String[] getUsage(String channel)
	{
		return new String[]{
				l10n.translate("explanation", channel)
		};
	}
}