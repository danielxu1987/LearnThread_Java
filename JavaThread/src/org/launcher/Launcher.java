package org.launcher;

//import org.unsyncthreading.SharedCell;
import org.syncthreading.SharedCell;

// the programme entry launching target classes
public class Launcher {
	
	public static void main(String args[]) {
//		ThreadTester threadTestApp = new ThreadTester();
//		SharedCell unsyncSharedCellApp = new SharedCell();
		SharedCell syncSharedCellApp = new SharedCell();
	}
	
}
