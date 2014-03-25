package org.unsyncthreading;


// show multiple threads modifying shared object
public class SharedCell {
	
	public SharedCell()
	{
		HoldIntegerUnsync holder = new HoldIntegerUnsync();
		
		ProduceInteger producer = new ProduceInteger(holder);
		ConsumeInteger consumer = new ConsumeInteger(holder);
		
		producer.start();
		consumer.start();
		
		System.out.println("\n--Main function completes");
	}
}