package quickSort;

public class Sort1 {
	int FindPivot(int A[],int l, int r)
	{
		switch (choice) {
		case 1: return l;
		case 2: return pivot2(A,l,h);
		case 3: return l+random(r-l);
		}
	}

	int partition(int A[], int l, int r)
	{
		int i,pivot, pivotpos;

		pivotpos = FindPivot(A,l,r);
		swap(&A[l],&A[pivotpos]);
		pivotpos = l;
		pivot = A[pivotpos];

		for (i = l+1; i <= r; i++) {
			if (A[i] < pivot) {
				pivotpos++;
				swap(&A[pivotpos],&A[i]);
			}
		}

		swap(&A[l],&A[pivotpos]);
		return pivotpos;
	}

	void QuickSort(int A[], int l, int r, int threshold) {
		int i, pivot;

		if (r - l > threshold) {
			delay(CompareDelay);
			pivot = partition(A, l, r);

			QuickSort(A, l, pivot - 1, threshold);
			QuickSort(A, pivot + 1, r, threshold);
		}
	}

	int pivot2(int A[], int l, int r) {
		int i = (r + l) / 2;

		if ((A[l] <= A[i]) && (A[i] <= A[r]))
			return i;
		if ((A[r] <= A[i]) && (A[i] <= A[l]))
			return i;
		if ((A[r] <= A[l]) && (A[l] <= A[i]))
			return l;
		if ((A[i] <= A[l]) && (A[l] <= A[r]))
			return l;
		if ((A[l] <= A[r]) && (A[r] <= A[i]))
			return r;
		if ((A[i] <= A[r]) && (A[r] <= A[l]))
			return r;
	}
}
