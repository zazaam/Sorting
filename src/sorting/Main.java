package sorting;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {

	private final static int FRAME_WIDTH = 300;
	private final static int FRAME_HEIGHT = 300;
	private final static int LENGTH = 30;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Gui window = new Gui();
		frame.add(window, BorderLayout.CENTER);
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		
		Double[] values = new Double[LENGTH];
		for (int i = 0; i < values.length; i++) {
			values[i] = Math.random() * window.getHeight();
		}
		
		Runnable r = new Sorter(values, window);
		Thread t = new Thread(r);
		t.start();
		System.out.println("Stopped");
	}

}
