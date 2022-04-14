package julya.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProcessPool<E> {
  private Queue<E> queue;
  private List<ProcessThread<E>> threads;

  public ProcessPool(int size, Process<E> process) {
    this.queue = new ConcurrentLinkedQueue<E>();
    this.threads = new ArrayList<ProcessThread<E>>();
    while (size-- > 0) {
      this.threads.add(new ProcessThread<E>(this.queue, process));
    }
  }

  public void addToQueue(E item) {
    while (!this.queue.offer(item)) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    synchronized (this.queue) {
      this.queue.notify();
    }
  }

  public void shutdown() {
    for (ProcessThread<E> thread : this.threads) {
      thread.shutdown();
    }
  }

  public void start() {
    for (ProcessThread<E> thread : this.threads) {
      thread.start();
    }
  }
}
