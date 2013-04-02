package vipQueueMain;

//import vipQueueArray.vipQueue; // test array implementation
import vipQueueLL.vipQueue; // test LL implementation

public class Test {
	public static void main(String[] args) {
		vipQueue Q = new vipQueue(10);
		for (int i=0; i<10; i++) {
			if (!Q.isFull()) Q.Enqueue(i);
			if (!Q.isFull()) Q.EnqueueVip(i*i);
		}
		while (!Q.isEmpty()) System.out.printf("->%d", Q.Dequeue());
	}
}