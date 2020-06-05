
public class Test {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread();
		
		thread.start();
		System.out.println(thread.isAlive());
		thread.sleep(2000);
		System.out.println(thread.isAlive());
		thread.sleep(1000);
		System.out.println(thread.isAlive());
		
		
	}
}
