package sorting;

import java.util.Comparator;

public class Sorter implements Runnable {
	
	private Double[] values;
	private Gui window;
	private static final int DELAY = 10;
	
	public Sorter(Double[] values, Gui window)	{
		this.values = values;
		this.window = window;
	}
	
	@Override
	public void run() {
		
		Comparator<Double> comparator = new Comparator<Double>() {

			
			@Override
			public int compare(Double o1, Double o2) {
				
				window.setValues(values, o1, o2);
				try
				{
					Thread.sleep(DELAY);
				}
				catch(InterruptedException i)
				{
					System.out.println("Interrupted");
					Thread.currentThread().interrupt();
				}
				return o1.compareTo(o2);
				
			}
			
		};
		RegularSort.sort(values, comparator);
		window.setValues(values, null, null);

	}
}
