package bl4ckscor3.bot.bl4ckb0t.util;

import bl4ckscor3.bot.bl4ckb0t.exception.IncorrectCommandExecutionException;

public class TimeParser
{
	private String cmdName;

	/**
	 * Initializes a TimeParser
	 */
	public TimeParser()
	{
		this("None.");
	}
	
	/**
	 * Initializes a TimeParser with a command alias that is used to send help if something goes wrong
	 * @param cmdN
	 */
	public TimeParser(String cmdN)
	{
		cmdName = cmdN;
	}
	
	//----------------CHANGE STRING TO LONG----------------\\

	/**
	 * Changes a String of the format xdxhxmxs (where x is a positive number) to a timestamp
	 * @param s The String to convert
	 * @return The converted String in milliseconds
	 */
	public long stl(String s) throws IncorrectCommandExecutionException
	{
		if(s.contains("d"))
			return d(s);
		else if(s.contains("h"))
			return h(s);
		else if(s.contains("m"))
			return m(s);
		else if(s.contains("s"))
			return s(s);
		else
			throw new IncorrectCommandExecutionException(cmdName);
	}

	/**
	 * Sub-method of stl() handling days
	 * @param s The String to convert
	 * @return The converted String
	 */
	private long d(String s)
	{
		long value = Long.parseLong(s.split("d")[0]);

		value *= 24 * 60 * 60;

		if(s.contains("h"))
			return value + h(s);
		else if(s.contains("m"))
			return value + m(s);
		else if(s.contains("s"))
			return value + s(s);
		else
			return value;
	}
	
	/**
	 * Sub-method of stl() handling hours
	 * @param s The String to convert
	 * @return The converted String
	 */
	private long h(String s)
	{
		long value = 0;

		if(s.contains("d"))
			value = Long.parseLong(s.split("d")[1].split("h")[0]);
		else
			value = Long.parseLong(s.split("h")[0]);

		value *= 60 * 60;

		if(s.contains("m"))
			return value + m(s);
		else if(s.contains("s"))
			return value + s(s);
		else
			return value;
	}
	
	/**
	 * Sub-method of stl() handling minutes
	 * @param s The String to convert
	 * @return The converted String
	 */
	private long m(String s)
	{
		long value = 0;

		if(s.contains("d"))
		{
			if(s.contains("h"))
				value = Long.parseLong(s.split("d")[1].split("h")[1].split("m")[0]);
			else
				value = Long.parseLong(s.split("d")[1].split("m")[0]);
		}
		else if(s.contains("h"))
			value = Long.parseLong(s.split("h")[1].split("m")[0]);
		else
			value = Long.parseLong(s.split("m")[0]);

		value *= 60;

		if(s.contains("s"))
			return value + s(s);
		else
			return value;
	}
	
	/**
	 * Sub-method of stl() handling seconds
	 * @param s The String to convert
	 * @return The converted String
	 */
	private long s(String s)
	{
		long value = 0;

		if(s.contains("d"))
		{
			if(s.contains("h"))
			{
				if(s.contains("m"))
					value = Long.parseLong(s.split("d")[1].split("h")[1].split("m")[1].split("s")[0]);
				else
					value = Long.parseLong(s.split("d")[1].split("h")[1].split("s")[0]);
			}
			else
			{
				if(s.contains("m"))
					value = Long.parseLong(s.split("d")[1].split("m")[1].split("s")[0]);
				else
					value = Long.parseLong(s.split("d")[1].split("s")[0]);
			}
		}
		else if(s.contains("h"))
		{
			if(s.contains("m"))
				value = Long.parseLong(s.split("h")[1].split("m")[1].split("s")[0]);
			else
				value = Long.parseLong(s.split("h")[1].split("s")[0]);
		}
		else if(s.contains("m"))
			value = Long.parseLong(s.split("m")[1].split("s")[0]);
		else
			value = Long.parseLong(s.split("s")[0]);
				
		return value;
	}

	//----------------CHANGE LONG TO STRING----------------\\
	
	/**
	 * Changes a timestamp into a String with the given format
	 * @param l The timestamp in milliseconds to convert
	 * @param format The format the String will have. Use %s as a placeholder for days/hours/minutes/seconds
	 * @return The converted timestamp as a String
	 */
	public String lts(long l, String format)
	{
		long s = l / 1000;
		long m = s / 60;
		long h = m / 60;
		long d = h / 24;

		s -= 60 * m;
		m -= 60 * h;
		h -= 24 * d;
		
		return String.format(format, (d < 10 ? "0" + d : d), (h < 10 ? "0" + h : h), (m < 10 ? "0" + m : m), (s < 10 ? "0" + s : s));
	}
}