package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {

	public static void sort(Double[] values, Comparator<Double> comparator) {

		values = mergeSort(values, comparator);

	}

	private static Double[] mergeSort(Double[] values,
			Comparator<Double> comparator) {

		Double[] first, second;
		int length = values.length;
		int mid = length / 2;

		if (length <= 1) {
			return values;
		}
		first = new Double[mid]; // Split array into two
		second = new Double[length - first.length]; // halves, a and b
		for (int i = 0; i < length; i++) {
			if (i < first.length)
				first[i] = values[i];
			else
				second[i - first.length] = values[i];
		}

		first = mergeSort(first, comparator);
		second = mergeSort(second, comparator);
		merge(first, second, values, comparator);

		return values;
	}

	private static void merge(Double[] first, Double[] second,
			Double[] values, Comparator<Double> comparator) {

	
		int firstCounter = 0, secondCounter = 0;
		
		while(firstCounter+secondCounter < values.length)
		{
			if(firstCounter < first.length)
			{
				if(secondCounter < second.length)
				{
					int comp = comparator.compare(first[firstCounter], second[secondCounter]);
					
					if(comp < 0)
					{
						values[firstCounter+secondCounter] = first[firstCounter];
						firstCounter++;
					}
					else
					{
						values[firstCounter+secondCounter] = second[secondCounter];
						secondCounter++;
					}
				}
				else
				{
					values[firstCounter+secondCounter] = first[firstCounter];
					firstCounter++;
				}
			}
			else
			{
				if(firstCounter < first.length)
				{
					int comp = comparator.compare(first[firstCounter], second[secondCounter]);
					
					if(comp < 0)
					{
						values[firstCounter+secondCounter] = first[firstCounter];
						firstCounter++;
					}
					else
					{
						values[firstCounter+secondCounter] = second[secondCounter];
						secondCounter++;
					}
				}
				else
				{
					values[firstCounter+secondCounter] = second[secondCounter];
					secondCounter++;
				}
			}
		}
		

	}

}
