package heapSort;

public class Sort1 {
	void Heapify(int A[],int i,int HeapSize)
	{
		int left=2*i, right=2*i+1;
		int largest;

		if ((left <= HeapSize) && 
				(A[left] > A[i]))
			largest = left;
		else
			largest = i;

		if ((right <= HeapSize) && 
				(A[right] > A[largest]))
			largest = right;

		if (largest != i) {
			Swap(&A[i],&A[largest]);
			Heapify(A,largest,HeapSize);
		}
	}

	void HeapSort(int A[], int n)
	{
		int i, HeapSize = n;

		for (i= HeapSize/2; i >= 1; i--)
			Heapify(A,i,HeapSize);

		for (i=n; i>=2; i--) {
			Swap(&A[i],&A[1]);
			HeapSize--;
			Heapify(A,1,HeapSize);
		}
	}
}
