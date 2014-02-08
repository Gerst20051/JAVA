package shellSort;

public class Sort1 {
	void ShellSort(int A[]) {
		int i, j, h = 1, v;

		do
			h = 3 * h + 1;
		while (h <= MAXSIZE);

		do {
			h /= 3;
			for (i = h + 1; i <= MAXSIZE; i++) {
				v = A[i];
				j = i;
				while ((j > h) && (A[j - h] > v)) {
					A[j] = A[j - h];
					j -= h;
				}
				A[j] = v;
			}
		} while (h > 1);
	}
}
