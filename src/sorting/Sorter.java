package sorting;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;

public class Sorter implements Runnable {
	
	private Double[] values;
	private Gui window;
	private BlockingQueue<String> commands;
	private String sorterType;
	private static final int DELAY = 10;
	
	public Sorter(Double[] values, Gui window, BlockingQueue<String> queue, String selected)	{
		this.values = values;
		this.window = window;
		this.commands = queue;
		this.sorterType = selected;
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
		//RegularSort.sort(values, comparator);
		//QuickSort.sort(values, 0, values.length-1, comparator);
		//chooseSorter(comparator);
		MergeSort.sort(values, comparator);
		window.setValues(values, null, null);
		

	}

	private void chooseSorter(Comparator<Double> comparator) {
		System.out.println(sorterType);
		if(sorterType.equals("QuickSort"))
		{
			QuickSort.sort(values, 0, values.length-1, comparator);
		}
		else if(sorterType.equals("Regular"))
		{
			RegularSort.sort(values, comparator);
		}
		else if(sorterType.equals("MergeSort"))
		{
			MergeSort.sort(values, comparator);
		}

	}
	
	public void changeSorter(String s)
	{
		this.sorterType = s;
	}
}
