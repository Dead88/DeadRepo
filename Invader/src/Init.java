public class Init 
{
	public static void main(String[] args) 
	{
		MainThread mT = new MainThread(130);
		Thread t = new Thread(mT);
		t.start();
	}
}
