public class CommonMethod 
{
	public static int getNouveauDelai(int from, int to)
	{ 
		return (from + (int) ( Math.random()*(to - from) ));
	}
}
