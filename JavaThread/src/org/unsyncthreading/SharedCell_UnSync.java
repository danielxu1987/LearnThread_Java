package org.unsyncthreading;


// show multiple threads modifying shared object
public class SharedCell_UnSync {
	
	public SharedCell_UnSync()
	{
		HoldIntegerUnsync holder = new HoldIntegerUnsync();
		
		ProduceInteger producer = new ProduceInteger(holder);
		ConsumeInteger consumer = new ConsumeInteger(holder);
		
		producer.start();
		consumer.start();
		
		System.out.println("\n--Main function completes");
	}
}
