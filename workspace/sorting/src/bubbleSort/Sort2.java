package bubbleSort;

public class Sort2 {
	public int[] bubbleSort(int[] data) {
		int lenD = data.length;
		int tmp = 0;
		for (int i = 0; i < lenD; i++) {
			for (int j = (lenD - 1); j >= (i + 1); j--) {
				if (data[j] < data[j - 1]) {
					tmp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = tmp;
				}
			}
		}
		return data;
	}
}
