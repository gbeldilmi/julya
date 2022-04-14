package julya.pool;

import java.util.Queue;

public class ProcessThread<E> extends Thread {
  private Queue<E> queue;
  private Process<E> process;
  private boolean running;

  public ProcessThread(Queue<E> queue, Process<E> process) {
    this.queue = queue;
    this.process = process;
  }

  public void run() {
    E item;
    this.running = true;
    while (this.running) {
      while ((item = this.queue.poll()) == null) {
        try {
          synchronized (this.queue) {
            this.queue.wait();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      try {
        this.process.process(item);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  void shutdown() {
    this.running = false;
  }
}
