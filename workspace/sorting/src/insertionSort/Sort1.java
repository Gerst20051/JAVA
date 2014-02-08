package insertionSort;

public class Sort1 {
	void InsertionSort(LIST A) {
		int f, i;
		KEYTYPE temp;

		for (f = 1; f < MAXDIM; f++) {
			if (A[f] > A[f - 1])
				continue;
			temp = A[f];
			i = f - 1;
			while ((i >= 0) && (A[i] > temp)) {
				A[i + 1] = A[i];
				i--;
			}
			A[i + 1] = temp;
		}
	}
}
