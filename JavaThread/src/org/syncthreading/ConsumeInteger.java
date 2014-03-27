package org.syncthreading;

import org.circularbufferthreading.ProduceIntegerBuffer;


public class ConsumeInteger extends Thread{
	private HoldIntegerSync consumerHold;
	
	public ConsumeInteger(HoldIntegerSync h)
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
		
		// count total time (in seconds) of producing and consuming
		double cStopMoment = System.currentTimeMillis();
		
		double totalTime = (cStopMoment - 
				ProduceInteger.pStartMoment) / 1000.0;
		
		System.out.println("--" + getName() + " retrieved values totaling: " + 
				sum + "\n--Terminating " + getName());
		
		System.out.println("Total time for producing and consuming is " + 
						totalTime + " seconds.");
				
		
		System.out.flush();
	}
}
