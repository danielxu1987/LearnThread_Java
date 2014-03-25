package org.unsyncthreading;


public class ProduceInteger extends Thread{
	
	private HoldIntegerUnsync producerHold;
	
	public ProduceInteger(HoldIntegerUnsync h)
	{
		super("ProduceInteger");
		producerHold = h;
	}
	
	@Override
	public void run() 
	{
		for(int count = 1; count <= 10; count++)
		{
			int interval = 0;
			// sleep for a random interval
			try {
				interval = (int) (Math.random() * 3000);
				Thread.sleep(interval);
			} 
			catch (Exception ex) {
				System.out.println(ex.toString());
			}
		
			System.out.println("--Producer thread was put to sleep for " + 
					interval + " milliseconds");
		
			producerHold.setSharedInt(count);
		}
		
		System.out.println("--" + getName() + " finished producing values" + 
				"\nTerminating " + getName());
		System.out.flush();
	}
}
