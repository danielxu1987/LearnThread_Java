package org.trythread;

import javax.swing.plaf.metal.MetalBorders.Flush3DBorder;

public class ConsumeInteger extends Thread{
	private HoldIntegerUnsync consumerHold;
	
	public ConsumeInteger(HoldIntegerUnsync h)
	{
		super("ConsumerInteger");
		consumerHold = h;
	}
	
	public void run()
	{
		int value, interval = 0, sum = 0;
		
		do {
			// sleep for a random interval
			try 
			{
				interval = (int)(Math.random() * 3000);
				Thread.sleep((interval));
			}
			catch (Exception ex) 
			{
				System.out.println(ex.toString());
			}
			
			System.out.println("--Consumer Thread was put to sleep for " + 
					interval + " milliseconds");
			
			value = consumerHold.getSharedInt();
			sum += value;
		} while (value != 10);
		
		System.out.println("--" + getName() + " retrieved values totaling: " + 
				sum + "\n--Terminating " + getName());
		System.out.flush();
	}
}
