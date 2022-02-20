package threadAndConCurrency;

/** @author CRR */
public class TestProducerAndConsumer {
  public static void main(String[] args) {
    SynContainer synContainer = new SynContainer();
    new Producer(synContainer).start();
    new Consumer(synContainer).start();
  }
}

class Producer extends Thread {
  SynContainer synContainer;
  private static final int TOTAL_COUNT = 100;

  public Producer(SynContainer synContainer) {
    this.synContainer = synContainer;
  }

  @Override
  public void run() {
    for (int i = 0; i < TOTAL_COUNT; i++) {
      synContainer.push(new Chicken(i));
      System.out.println("厨房正在制作第" + i + "只鸡");
    }
  }
}

class Consumer extends Thread {
  SynContainer synContainer;
  private static final int TOTAL_COUNT = 100;

  public Consumer(SynContainer synContainer) {
    this.synContainer = synContainer;
  }

  @Override
  public void run() {
    for (int i = 0; i < TOTAL_COUNT; i++) {
      Chicken chicken = synContainer.pop();
      System.out.println("顾客正在享用第" + chicken.id + "只鸡");
    }
  }
}

class Chicken {
  int id;

  public Chicken(int id) {
    this.id = id;
  }
}

class SynContainer {
  Chicken[] chickens = new Chicken[10];
  int count = 0;

  public synchronized void push(Chicken chicken) {
    if (chickens.length == count) {
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    chickens[count] = chicken;
    count++;
    this.notifyAll();
  }

  public synchronized Chicken pop() {
    if (count <= 0) {
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    count--;
    this.notifyAll();
    return chickens[count];
  }
}
