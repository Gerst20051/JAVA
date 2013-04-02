package vipQueueArray;

public class vipQueue extends PQueueArray {
	public vipQueue(int i) {
		super(i);
	}

	public void Enqueue(int i) {
		insert(i);
	}

	public void EnqueueVip(int i) {
		insertVip(i);
	}

	public int Dequeue() {
		return popFirst();
	}
}