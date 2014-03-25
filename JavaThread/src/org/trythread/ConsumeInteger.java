package org.trythread;

public class ConsumeInteger extends Thread{
	private HoldIntegerUnsync consumerHold;
	
	public ConsumeInteger(HoldIntegerUnsync h)
	{
		super("ConsumerInteger");
		consumerHold = h;
	}
	
	public void run()
	{
		int value, sum = 0;
		
		do {
			// sleep for a random interval
			try 
			{
				Thread.sleep((int)(Math.random() * 3000));
			} 
			catch (Exception ex) 
			{
				System.out.println(ex.toString());
			}
			
			value = consumerHold.getSharedInt();
			sum += value;
		} while (value != 10);
		
		System.out.println(getName() + " retrieved values totaling: " + 
				sum + "\nTerminating " + getName());
	}
}
