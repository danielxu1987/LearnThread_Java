package org.circularbufferthreading;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SharedCell_Buffer extends JFrame
{
	public SharedCell_Buffer()
	{
		super("Demonstrating Thread Synchronisation");
		JTextArea output = new JTextArea(20, 30);
		
		getContentPane().add(new JScrollPane(output));
		setSize(600, 500);
		setVisible(true);
		
		// set up threads and start threads
		HoldIntegerSyncBuffer h = new HoldIntegerSyncBuffer(output);
		ProduceIntegerBuffer pHold = new ProduceIntegerBuffer(h, output);
		ConsumeIntegerBuffer cHold = new ConsumeIntegerBuffer(h, output);
		
		pHold.start();
		cHold.start();
				
		output.append("--Main function completes");
	}
}
