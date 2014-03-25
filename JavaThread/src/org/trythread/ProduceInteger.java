package org.trythread;

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
			// sleep for a random interval
			try {
				Thread.sleep((int) (Math.random() * 3000));
				
			} 
			catch (Exception ex) {
				System.out.println(ex.toString());
			}
			
			producerHold.setSharedInt(count);
		}
		
		System.out.println(getName() + " finished producing values" + 
				"\nTerminating " + getName());
	}
}
