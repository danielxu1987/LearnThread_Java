package org.circularbufferthreading;

import java.text.DecimalFormat;

import javax.swing.JTextArea;

public class HoldIntegerSyncBuffer
{
	// 5 element array providing buffer for write/read
	private int sharedInt[] = {0, 0, 0, 0, 0};
	private boolean writable = true;
	private boolean readable = false;
	private int readLocation = 0, writeLocation = 0;
	private JTextArea outputText;
	
	
	public HoldIntegerSyncBuffer(JTextArea textArea)
	{
		outputText = textArea;
	}
	
	public synchronized void setSharedInt(int value)
	{
		while(!writable)
		{
			try
			{
				outputText.append("--Waiting to produce " + value);
				wait();
			}
			catch (InterruptedException ex)
			{
				System.out.println(ex.toString());
			}
		}
		
		sharedInt[writeLocation] = value;
		// once an element contains newly produced value the consumer can read
		readable = true;
		
		outputText.append("\nProduced " + value + " into cell " + 
				writeLocation);
		
		// restrict the range of writeLocation within [0, 4]
		writeLocation = (writeLocation + 1) % 5;
		
		outputText.append("\twrite to " + writeLocation + "\tread from " + 
				readLocation);
		
		displayBuffer(outputText, sharedInt);
		
		// this means the producer has completed all 5 elements
		// and come back to the element whose value was filled first
		if(writeLocation == readLocation)
		{
			writable = false;
			outputText.append("\nBuffer Full");
		}
		
		// notify is used to awake other threads relating to object 
		// that calls this thread
		notify();
	}
	
	public synchronized int getSharedInt()
	{
		int value;
		
		// if the shared cells are not readable then wait
		while(!readable)
		{
			try
			{
				outputText.append(" Waiting to consume ");
				wait();
			}
			catch (InterruptedException ex)
			{
				System.out.println(ex.toString());
			}
		}
		
		value = sharedInt[readLocation];

		writable = true;

		outputText.append("\nConsumed " + value + " from cell " + 
				readLocation);
		
		// make sure readLocation's range is [0, 4]
		readLocation = (readLocation + 1) % 5;
		
		outputText.append("\twrite to " + writeLocation + "\tread from " + 
				readLocation);
		
		displayBuffer(outputText, sharedInt);
		
		// this means the consumer has completed all 5 elements
		// and come back to the element whose value was consumed first
		if(readLocation == writeLocation)
		{
			readable = false;
			outputText.append("\nBuffer Empty");
		}
		
		notify();
		
		return value;
	}

	private void displayBuffer(JTextArea out, int[] buf)
	{
		DecimalFormat formatNumber = new DecimalFormat(" #;-#");
		outputText.append("\tbuffer: ");
		
		for(int i = 0; i< buf.length; i++)
			out.append(" " + formatNumber.format(buf[i]));
	}
	
}
