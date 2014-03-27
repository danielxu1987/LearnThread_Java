package org.circularbufferthreading;

import javax.swing.JTextArea;

public class ProduceIntegerBuffer extends Thread
{
	private HoldIntegerSyncBuffer pHold;
	private JTextArea outputTextArea;
//	private StopWatch stopWatch;
	public static double pStartMoment;
	
	public ProduceIntegerBuffer(HoldIntegerSyncBuffer h, JTextArea out)
	{
		super("ProduceInteger");
		pHold = h;
		outputTextArea = out;
	}
	
	@Override
	public void run()
	{
		pStartMoment = System.currentTimeMillis();
		
		for(int count = 1; count <= 10; count++)
		{
			// sleep for a random interval
			// note: interval shortened purposely to speed up 
			// buffer filling. Consumer's sleep interval should be larger
			try
			{
				Thread.sleep((int) (Math.random() * 500));
			}
			catch (InterruptedException ex)
			{
				System.out.println(ex.toString());
			}
			
			pHold.setSharedInt(count);
		}
		
		outputTextArea.append("\n" + getName() + " finished producing values" + 
				"\nTerminating " + getName() + "\n");
	}
	
}
