package threadAndConCurrency;

public class ThreadDemo {
    public static void main(String[] args) {
        class StoppableThread extends Thread {
            private volatile boolean stopped;

            @Override
            public void run() {
                while (!stopped) {
                    System.out.println("Running");
                }
            }
            void stopThread() {
                stopped = true;
            }
        }
        StoppableThread thd = new StoppableThread();
        thd.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        thd.stopThread();
    }
}
