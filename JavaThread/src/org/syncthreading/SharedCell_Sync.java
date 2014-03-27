package org.syncthreading;


// show multiple threads modifying shared object
public class SharedCell_Sync 
{	
	public SharedCell_Sync()
	{
		HoldIntegerSync holder = new HoldIntegerSync();
		
		ProduceInteger producer = new ProduceInteger(holder);
		ConsumeInteger consumer = new ConsumeInteger(holder);
		
		producer.start();
		consumer.start();
		
		System.out.println("\n--Main function completes");
	}
}
