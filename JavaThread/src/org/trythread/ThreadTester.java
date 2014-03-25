package org.trythread;

public class ThreadTester {

	public ThreadTester()
	{
		PrintThread thread1, thread2, thread3, thread4;		
		
		thread1 = new PrintThread("thread1");
		thread2 = new PrintThread("thread2");
		thread3 = new PrintThread("thread3");
		thread4 = new PrintThread("thread4");
		
		// why in the runtime thread4's message starts
		// always after system output "Starting threads"?
		System.out.println("\n---Starting threads");
		
		thread1.start();
		thread2.start();
		
		System.out.println("---Thread1 and Thread2 started\n");
		
		thread3.start();
		thread4.start();
		
//		System.out.println("Threads done sleeping.");
		System.out.println("---Thread3 and Thread4 started\n");
//		System.out.println("---Main function completed\n");
	}
	
	private class PrintThread extends Thread{
		
		private int sleepTime;
		
		// PrintThread constructor assigns name to
		// thread by calling Thread constructor
		public PrintThread(String name)
		{
			super(name);
			
			// sleep between 0 and 5 seconds
			sleepTime = (int) (Math.random() * 5000);
			
			System.out.println("Name: " + getName() + 
					"; sleep: " + sleepTime + " milliseconds");
		}
		
		//execute the thread
		public void run()
		{
			// put thread to sleep for a random interval
			try
			{
				System.out.println(getName() + " going to sleep ");
				Thread.sleep(sleepTime);
			}
			catch(InterruptedException ex)
			{
				System.out.println(ex.toString());
			}
			
			//print thread name
			System.out.println(getName() + " done sleeping");
		}
	}
}
