package threadAndConCurrency;

public class Customer implements Runnable {
    private Saleman saleman;

    public Customer(Saleman saleman) {
        this.saleman = saleman;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            saleman.removeProduct();
        }
    }
}
