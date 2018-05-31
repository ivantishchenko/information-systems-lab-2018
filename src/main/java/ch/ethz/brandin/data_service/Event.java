package ch.ethz.brandin.data_service;

public class Event {
	private volatile boolean isPassed;
	private Object lock;

	public Event() {
		lock = new Object();
	}

	public void activate() {
		isPassed = true;
		synchronized (lock) {
			lock.notifyAll();
		}
	}

	public void await() {
		if (!isPassed) {
			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isPassed() {
		return isPassed;
	}

	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}

	public Object getLock() {
		return lock;
	}

	public void setLock(Object lock) {
		this.lock = lock;
	}
}
