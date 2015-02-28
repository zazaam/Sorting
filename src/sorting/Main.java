package sorting;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

	private final static int FRAME_WIDTH = 300;
	private final static int FRAME_HEIGHT = 300;
	private final static int LENGTH = 30;
	private final static String[] data = {"Regular", "QuickSort", "MergeSort"};
	
	private static JComboBox<String> algorithmList;
	private static JButton nextButton;
	private static JButton runButton;
	private static JButton executeButton;
	private static JFrame frame;
	
	private static Gui window;
	
	private static Double[] values;
	private static BlockingQueue<String> commands;
	private static String selected;
	private static Sorter r;
	private static Thread t;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				init();				
			}
		});
		
		
	}

	private static void init() {
		
		setLayout();
		setSorterParameters();
		r = new Sorter(values, window, commands, selected);
		t = new Thread();
		setListeners();
				
		
	}

	private static void setListeners() {
		
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
		
		//TODO: change values, commands so that each thread has its own
		executeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				runButton.setEnabled(true);
				nextButton.setEnabled(true);
				setSorterParameters();
				
								
				r = new Sorter(values, window, commands, selected);
				t = new Thread(r);
				t.start();

			}
		});
		
		algorithmList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JComboBox<String> J = (JComboBox<String>)e.getSource();
				r.changeSorter(J.getSelectedItem().toString());
			}
		});
	}

	private static void setSorterParameters() {
		values = new Double[LENGTH];
		for (int i = 0; i < values.length; i++) {
			values[i] = Math.random() * window.getHeight();
		}
		
		commands = new LinkedBlockingQueue<String>();
		commands.add("Next");
		
		selected = String.valueOf(algorithmList.getSelectedItem());
	}

	private static void setLayout() {
		frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		
		algorithmList = new JComboBox<String>(data);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 1;
		c.weightx = 1;
		algorithmList.setVisible(true);
		buttonPanel.add(algorithmList, c);
		
		executeButton = new JButton("Execute");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 1;
		c.weightx = 1;
		buttonPanel.add(executeButton, c);
		
		nextButton = new JButton("Next");
		nextButton.setEnabled(false);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 1;
		c.weightx = 1;
		buttonPanel.add(nextButton, c);
		
		runButton = new JButton("Run");
		runButton.setEnabled(false);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 1;
		c.weightx = 1;
		buttonPanel.add(runButton, c);
		
		
		window = new Gui();
		panel.add(window);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.9;
		mainPanel.add(panel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.1;
		mainPanel.add(buttonPanel, c);
		
		frame.add(mainPanel);
		frame.setVisible(true);
	}

}
