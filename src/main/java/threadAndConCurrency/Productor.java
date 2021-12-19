package threadAndConCurrency;

public class Productor implements Runnable {
    private Saleman saleman;
    public Productor(Saleman saleman) {
        this.saleman = saleman;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            saleman.addProduct(new Product());
        }
    }
}
