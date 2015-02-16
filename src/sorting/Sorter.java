package sorting;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;

public class Sorter implements Runnable {
	
	private Double[] values;
	private Gui window;
	private BlockingQueue<String> commands;
	private static final int DELAY = 10;
	
	public Sorter(Double[] values, Gui window, BlockingQueue<String> queue)	{
		this.values = values;
		this.window = window;
		this.commands = queue;
	}
	
	@Override
	public void run() {
		
		Comparator<Double> comparator = new Comparator<Double>() {

			
			@Override
			public int compare(Double o1, Double o2) {
				
				
				try
				{
					String command = commands.take();
					
					if(command.equals("Run"))
					{
						Thread.sleep(DELAY);
						if(!("Next".equals(commands.peek())))
							commands.add("Run");
					}
				}
				catch(InterruptedException i)
				{
					System.out.println("Interrupted");
					Thread.currentThread().interrupt();
				}
				window.setValues(values, o1, o2);
				return o1.compareTo(o2);
				
			}
			
		};
		RegularSort.sort(values, comparator);
		window.setValues(values, null, null);

	}
}
