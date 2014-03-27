package org.circularbufferthreading;

import javax.swing.JTextArea;

public class ConsumeIntegerBuffer extends Thread
{
	private HoldIntegerSyncBuffer cHold;
	private JTextArea outputTextArea;

	public ConsumeIntegerBuffer(HoldIntegerSyncBuffer h, JTextArea out)
	{
		super("ConsumeInteger");
		cHold = h;
		outputTextArea = out;
	}
	
	@Override
	public void run()
	{
		int value, sum = 0;
		
		do
		{
			// sleep for a random interval
			try
			{
				// sleep relatively longer than producer to wait
				// for the value producing
				Thread.sleep((int) (Math.random() * 3000));
			}
			catch (InterruptedException ex)
			{
				System.out.println(ex.toString());
			}
			
			value = cHold.getSharedInt();
			sum += value;
		} while (value != 10);
		
		double cStopMoment = System.currentTimeMillis();
		
		double totalTime = (cStopMoment - 
				ProduceIntegerBuffer.pStartMoment) / 1000.0;
		
		outputTextArea.append("\n" + getName() + 
				" retrieved values totaling: " + sum + 
				"\nTerminating " + getName() + "\n");
		
		outputTextArea.append("Total time for producing and consuming is " + 
				totalTime + " seconds.\n");
	}
}
