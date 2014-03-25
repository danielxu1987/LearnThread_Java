package org.syncthreading;

// Uses thread synchronisation to ensure that both threads
// access shared memory-sharedInt at the proper times.
public class HoldIntegerSync {
	
	// a single shared memory cell
	private int sharedInt = -1;
	
	// condition variable
	private boolean writeable = true;
	
	
	public synchronized void setSharedInt(int value) 
	{
		// sharedInt isn't currently under writing
		while (!writeable) 
		{
			try 
			{
				wait();
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getName() + 
				" setting sharedInt to " + value);
		System.out.flush();
		
		sharedInt = value;
		
		writeable = false;
		
		// tell a waiting thread to become ready
		notify();
	}
	
	public synchronized int getSharedInt() 
	{
		// not the consumer's turn
		while (writeable)
		{
			try
			{
				wait();
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}
		
		writeable = true;
		// tell a waiting thread to become ready
		notify();
		
		System.out.println(Thread.currentThread().getName() +
				" retrieving sharedInt value " + sharedInt);
		System.out.flush();
		
		return sharedInt;
	}
}
