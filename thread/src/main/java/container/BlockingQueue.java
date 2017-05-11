package container;

import java.util.LinkedList;
import java.util.List;

/**
 * 2014年7月1日 10:41:46
 * 
 * 使用wait()和notify()实现线程安全阻塞队列
 */
public class BlockingQueue {

	private List<Object> queue = new LinkedList<Object>();
	
	private int limit = 10;
	
	public BlockingQueue(int limit) {
		this.limit = limit;
	}
	
	public synchronized void enqueue(Object obj, long ms) 
	    throws InterruptedException{
		while(this.queue.size() == this.limit) {
			try {
				wait(ms);
				if(this.queue.size() == this.limit) {
					throw new InterruptedException();
				}
			} catch (InterruptedException e) {
				throw e;
			}
		}
		if(this.queue.size() == 0) {
			notifyAll(); // 这里需要叫醒全部，因为不知道wait是enqueue还是dequeue
		}
		this.queue.add(obj);
	}
	
	public synchronized Object dequeue(long ms) throws InterruptedException {
		while(this.queue.size() == 0){
			try {
				wait(ms);
				if(this.queue.size() == 0) {
					throw new InterruptedException();
				}
			} catch (InterruptedException e) {
				throw e;
			}
		}
		if(this.queue.size() == this.limit){
			notifyAll(); // 拿了一個之後就沒有達到上限了
		}
		return this.queue.remove(0);
	}
	
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue blockingQueue = new BlockingQueue(10);
		blockingQueue.dequeue(1000);
	}
	
}
