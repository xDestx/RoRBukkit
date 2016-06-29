package me.xDest.ror;

public class Constants {

	public static int basehp = 1000;
	public static int basespeed = 9;
	public static int basestr = 49;
	public static int creepermulti = 75;
	public static int skelmulti = 25;
	public static int spiderdur = 120;
	
	public static void reset()
	{
		basehp = 1000;
		basespeed = 9;
		basestr = 49;
		creepermulti = 75;
		skelmulti = 25;
		spiderdur = 120;
	}
	
	public static int get(String str)
	{
		if (str.equals("spiderm"))
		{
			return spiderdur;
		} else if (str.equals("skelm"))
		{
			return skelmulti;
		} else if (str.equals("creeperm"))
		{
			return creepermulti;
		} else if (str.equals("basestr"))
		{
			return basestr;
		} else if (str.equals("basespeed"))
		{
			return basespeed;
		} else if (str.equals("basehp"))
		{
			return basehp;
		}
		return -1;
	}
	
	
	
}
