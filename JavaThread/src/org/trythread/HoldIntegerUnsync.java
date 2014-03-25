package org.trythread;

public class HoldIntegerUnsync {
	
	// a single shared memory cell
	private int sharedInt = -1;
	
	public void setSharedInt(int value) {
		System.out.println(Thread.currentThread().getName() + 
				" setting sharedInt to " + value);
		
		sharedInt = value;
	}
	
	public int getSharedInt() {
		System.out.println(Thread.currentThread().getName() +
				" retrieving sharedInt value " + sharedInt);
		
		return sharedInt;
	}
}
