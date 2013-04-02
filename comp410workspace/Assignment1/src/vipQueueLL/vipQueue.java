package vipQueueLL;

public class vipQueue extends PQueueLL {
	public vipQueue(int i) {
		super(i);
	}

	public void Enqueue(int i) {
		addItem(i);
	}

	public void EnqueueVip(int i) {
		addItemVip(i);
	}

	public int Dequeue() {
		return removeFirstItem();
	}
}