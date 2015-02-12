package sorting;

import java.util.Comparator;

public class RegularSort {

	public static void sort(Double[] values, Comparator<Double> comparator) {

		for (int j = 0; j < values.length; j++) {
			for (int i = 0; i < values.length; i++) {
				if (comparator.compare(values[j], values[i]) > 0) {
					System.out.println(values[j] + " changes with " + values[i]);
					Double oldValue = values[i];
					values[i] = values[j];
					values[j] = oldValue;
				}
			}
		}

	}
}
