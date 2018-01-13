package bl4ckscor3.module.sctools;

public class SecurityCraftUpdate
{
	private String version;
	private String betaVersion;
	private String betaVersionOf;
	private String betaDownloadLink;

	public SecurityCraftUpdate(String v, String bv, String bvo, String bdl)
	{
		version = v;
		betaVersion = bv;
		betaVersionOf = bvo;
		betaDownloadLink = bdl;
	}

	public String getVersion()
	{
		return version;
	}

	public String getBetaVersion()
	{
		return betaVersion;
	}

	public String getBetaVersionOf()
	{
		return betaVersionOf;
	}

	public String getBetaDownloadLink()
	{
		return betaDownloadLink;
	}

}