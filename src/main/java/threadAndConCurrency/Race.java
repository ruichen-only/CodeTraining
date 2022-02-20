package threadAndConCurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** @author CRR */
public class Race implements Runnable {
  private String winner;

  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    for (int i = 0; i < 100; i++) {
      if ("pool-1-thread-2".equals(name)) {
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      if (gameOver(name, i)) {
        break;
      }
    }
    winner = name;
  }

  private boolean gameOver(String name, int steps) {
    if (winner != null) {
      return true;
    }
    if (steps >= 99) {
      System.out.println(name + " is winner");
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Race race = new Race();

    ExecutorService service = Executors.newFixedThreadPool(2);
    service.execute(race);
    service.execute(race);
    //    Thread rabbit = new Thread(race, "兔子");
    //    Thread tortoise = new Thread(race, "乌龟");
    //    rabbit.start();
    //    tortoise.start();
    service.shutdown();
  }
}
