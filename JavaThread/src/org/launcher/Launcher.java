package org.launcher;

//import org.unsyncthreading.SharedCell;
import org.circularbufferthreading.SharedCell_Buffer;
import org.syncthreading.SharedCell_Sync;
import org.unsyncthreading.SharedCell_UnSync;

// the programme entry launching target classes
public class Launcher {
	
	public static void main(String args[]) {
//		ThreadTester threadTestApp = new ThreadTester();
//		SharedCell_UnSync unsyncSharedCellApp = new SharedCell_UnSync();
		SharedCell_Sync syncSharedCellApp = new SharedCell_Sync();
//		SharedCell_Buffer bufferSharedCellApp = new SharedCell_Buffer();
		
	}
	
}
