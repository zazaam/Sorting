package sorting;

import java.util.Comparator;

public class QuickSort {

	public static void sort(Double[] values, int low, int high, Comparator<Double> comparator) {

		int storeIndex;
		if(low < high)
		{
			storeIndex = partition(values, low, high, comparator);
			sort(values, low, storeIndex - 1, comparator);
			sort(values, storeIndex + 1, high, comparator);
			
		}

	}

	private static int partition(Double[] values, int low, int high, Comparator<Double> comparator) {
	
		int range = (low+high)/2;
		int pivot = range;
		Double pivotValue = values[pivot];
		
		swap(values, high, pivot);
		
		int storeIndex = low;
		
		for(int i = low; i <= high-1; i++)
		{
			if(comparator.compare(values[i], pivotValue) < 0)
			{
				swap(values, i, storeIndex);
				storeIndex += 1;
			}
			
		}
		swap(values, storeIndex, high);
		return storeIndex;
	}

	private static void swap(Double[] values, int v1, int v2) {
		Double temp = values[v2];
		values[v2] = values[v1];
		values[v1] = temp;
	}

}
