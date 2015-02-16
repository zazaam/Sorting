package sorting;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	private final static int FRAME_WIDTH = 300;
	private final static int FRAME_HEIGHT = 300;
	private final static int LENGTH = 30;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Gui window = new Gui();
		frame.add(window, BorderLayout.CENTER);
		
		JButton nextButton = new JButton("Next");
		final JButton runButton = new JButton("Run");
		
		JPanel buttons = new JPanel();
		buttons.add(nextButton);
		buttons.add(runButton);
		
		frame.add(buttons, BorderLayout.NORTH);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		
		Double[] values = new Double[LENGTH];
		for (int i = 0; i < values.length; i++) {
			values[i] = Math.random() * window.getHeight();
		}
		
		final BlockingQueue<String> commands = new LinkedBlockingQueue<String>();
		commands.add("Next");
		
		Sorter r = new Sorter(values, window, commands);
		
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				commands.add("Next");
				runButton.setEnabled(true);
			}
		});
		
		runButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				runButton.setEnabled(false);
				commands.add("Run");
				
			}
		});

		Thread t = new Thread(r);
		t.start();
		
	}

}
